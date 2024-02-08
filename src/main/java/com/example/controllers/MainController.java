package com.example.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;


@Controller // Para que está clase sepa que es un controller
@RequestMapping("/") // Annotation for mapping web requests onto methods in request-handling classes with flexible method signatures.
@RequiredArgsConstructor

// Qué va a hacer cuando le llegue una petición?
// El Main Controller enrutara la petición al HandleMapping para que la resuelva
public class MainController {
    
    // Método que va a devolver en la vista todos los empleados en una tabla
    // COnstruir ensamblar la vista: renderizar
    // Este método se corresponde con el ModelAndView del diagrama de Spring MVC
    // A este método le vamos a enviar una petición por GET (hacer una petición http por la URL)
    // Eso llega al servidor que se está ejecutando en el puerto ejecutandose por GET
    // Lo espero también por GET, usando @GetMapping, que debe indicar como termina
    // URL: http//blablabla/all

    private final EmpleadoService empleadoService;

    private final DepartamentoService departamentoService;

    private final Logger LOG = Logger.getLogger("MainController");

    
/* /*     @GetMapping("/all")
/*     public ModelAndView dameEmpleados() {

/*         ModelAndView modelo = new ModelAndView("views/listadoEmpleados");
        List<Empleado> empleados = empleadoService.dameTodosLosEmpleados();
        
        // Este listado tiene que viajar hacia la lista en un atributo
        modelo.addObject("empleados", empleados); // Antiguo
        return modelo;
 */ 

    @GetMapping("/all")
    public String dameEmpleados(Model model){
        model.addAttribute("empleados",
        empleadoService.dameTodosLosEmpleados());
        return "views/listadoEmpleados";
    }

    // Cuando se recibe un parametro conjuntamente con la request
    // Se utiliza tambien actualmnete, pero menos que enviar una variable en la ruta
    // @GetMapping("/detalles")
    // public String detallesEmpleado(@RequestParam(name= "id", required = false) int idEmpleado,
    //                                 Model model) {
    //     LOG.info("ID Empleado Recibido" + idEmpleado);
    //     return "views/empleadoDetalles";
    // }

    // En la forma nueva, tengo un numero que guardaré en la URL
    @GetMapping("/detalles/{id}")
    public String detallesEmpleado(
        @PathVariable(name= "id") int idEmpleado, Model model) {
        LOG.info("ID Empleado Recibido" + idEmpleado);
        model.addAttribute("empleado",
            empleadoService.dameUnEmpleado(idEmpleado));
        return "views/empleadoDetalles";
    }

    @GetMapping("/frmAltaModificacion")
    public String formularioAltaModificacionEmpleado(Model model) {

    //Le paso al modelo un obejto vacio
    Empleado empleado = new Empleado();

    model.addAttribute("empleado", empleado);

    //También para los departamentos
    model.addAttribute("departamentos",
        departamentoService.dameDepartamentos());
    
    return "views/frmAltaModificacionEmpleado";

    }

    @PostMapping("/persistir")
    @Transactional // todas las que necesitan modificación en la base de datos, es el autocommit
    public String persistirEmpleado(@ModelAttribute(name="empleado") Empleado empleado,
    @RequestParam(name ="numerosTel", required = false) String telefonosRecibidos,
    @RequestParam(name = "direccionesCorreo", required = false) String correosRecibidos,
    @RequestParam(name = "file", required= false) MultipartFile imagen) {

        // COmprobamos si hemos recibido un archivo de imagen
        if(!imagen.isEmpty()) {

            // Vamos a trabajar con NIO.2

            // Recuperar la ruta (path) relativa de la carpeta donde quedará almacenado el archivo
            Path imageFolder = Path.of("src/main/resources/static/images");
            
            // Crear la ruta absoluta
            Path rutaAbsoluta = imageFolder.toAbsolutePath();

            // También necesitamos la ruta completa (rutaAbsoluta + nombre del archivo recibido)
            Path rutaCompleta = Path.of(rutaAbsoluta + "/" +imagen.getOriginalFilename());

            try {

                byte[] bytesImage =imagen.getBytes(); // Me devuelve el array de bytes que ha recibido del servidor
                Files.write(rutaCompleta, bytesImage);

                // Lo que resta es estblecer la propiedas foto del empleado a el nombre original del arciho recibido
                empleado.setFoto(imagen.getOriginalFilename());
                           
            } catch (Exception e) {
                // TODO: handle exception
            }


        }

        // Procesar los telefonos
        if(telefonosRecibidos != null) {
            String[] arrayTelefonos = telefonosRecibidos.split(";");
            List<String> numerosTelefonos = Arrays.asList(arrayTelefonos);

            List<Telefono> telefonos = new ArrayList<>();

            numerosTelefonos.stream()
                .forEach(numeroTelefono -> {
                    telefonos.add(
                    Telefono.builder()
                    .telefono(numeroTelefono)
                    .empleado(empleado)
                    .build());
                });
                empleado.setTelefonos(telefonos);
        }

        // Procesar los telefonos
        if(correosRecibidos != null) {
            String[] arrayCorreos = correosRecibidos.split(";");
            List<String> direccionesDeCorreo = Arrays.asList(arrayCorreos);

            List<Correo> correos = new ArrayList<>();

            direccionesDeCorreo.stream()
                .forEach(direccionDeCorreo -> {
                    correos.add(Correo.builder()
                    .correo(direccionDeCorreo)
                    .empleado(empleado)
                    .build());
                });
                empleado.setCorreos(correos);
        }

        empleadoService.persistirEmpleado(empleado);

       return "redirect:/all";

    }

    // Este metodo va a retornar lo mismo que el método que persiste
    @GetMapping("/actualizar/{id}") //ese {id} es un path variable
    @Transactional
    public String actualizarEmpleado(@PathVariable(name = "id", required = true) int idEmpleado,
                                    Model model) {
    // Recupera el empleado cuyo id se recibe como paramétro
    Empleado empleado = empleadoService.dameUnEmpleado(idEmpleado);
    model.addAttribute("empleado", empleado);

    // Recupera los departamentos
    List<Departamento> departamentos = departamentoService.dameDepartamentos();
    model.addAttribute("departamentos", departamentos);

    // Construir los numeros de telefono, a partir de los telefonos recibidos conjuntamente con el empleado
    if(empleado.getTelefonos() != null) {
    String numerosTelefono = empleado.getTelefonos().stream()
                                    .map(Telefono::getTelefono)
                                    .collect(Collectors.joining(";"));
    model.addAttribute("numerosTelefono", numerosTelefono);
    }

    // Construir los correos, a partir de los correos recibidos conjuntamente con el empleado
    if(empleado.getCorreos() != null) {
        String direccionesDeCorreo = empleado.getCorreos().stream()
                                        .map(Correo::getCorreo)
                                        .collect(Collectors.joining(";"));
    model.addAttribute("direccionesDeCorreo", direccionesDeCorreo);
    }

        return "views/frmAltaModificacionEmpleado";
    }

    // Eliminar un empleado
    @GetMapping("/eliminar/{id}") //ese {id} es un path variable
    @Transactional
    public String eliminarEmpleado(@PathVariable(name = "id", required = true)
                                    int idEmpleado) {
    // Recupera el empleado cuyo id se recibe como paramétro y eliminarlo
    empleadoService.eliminarEmpleado(idEmpleado);
        return "redirect:/all";
    }


}
