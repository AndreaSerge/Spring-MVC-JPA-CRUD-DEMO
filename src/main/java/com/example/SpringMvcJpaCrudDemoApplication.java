package com.example;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Genero;
import com.example.entities.Telefono;
import com.example.services.CorreoService;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import com.example.services.TelefonoService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor

// Las tablas que se crean a partir de las entidades están vacías
// vamos a meterle datos a las tablas, eso lo logramos con el método Run de la
// interfaz CommandLineRunner
public class SpringMvcJpaCrudDemoApplication implements CommandLineRunner {

	// Para que esto persista, se necesita de los servicios que guardan datos en las
	// bases de datos
	// Hay que inyectar esos servicios
	private final EmpleadoService empleadoService;
	private final DepartamentoService departamentoService;
	private final TelefonoService telefonoService;
	private final CorreoService correoService;

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcJpaCrudDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Primero: creamos los departamentos
		Departamento dpt1 = Departamento.builder()
				.nombre("RRHH")
				.build();

		Departamento dpt2 = Departamento.builder()
				.nombre("INFORMATICA")
				.build();

		Departamento dpt3 = Departamento.builder()
				.nombre("CONTABILIDAD")
				.build();

		// Persistir(guardar) un empleado: los necesito para meterles telefono y correos
		// He ido a departamentoService y creado el método persistirDpto
		// No olvidar implementarlo en DepartamentoServiceImpl
		departamentoService.persistirDpto(dpt1);
		departamentoService.persistirDpto(dpt2);
		departamentoService.persistirDpto(dpt3);

		Empleado emp1 = Empleado.builder()
				.nombre("Aurora")
				.primerApellido("Duque")
				.segundoApellido(null)
				.fechaAlta(LocalDate.of(2000, Month.JANUARY, 12))
				.salario(2500.5)
				.departamento(departamentoService.dameUnDepartamento(1))
				.genero(Genero.OTRO)
				.foto("AuroraB")
				.build();

		Empleado emp2 = Empleado.builder()
				.nombre("Vania")
				.primerApellido("Reina")
				.segundoApellido("Serge")
				.fechaAlta(LocalDate.of(2022, Month.OCTOBER, 26))
				.salario(3000)
				.departamento(departamentoService.dameUnDepartamento(1))
				.genero(Genero.MUJER)
				.foto("bebe")
				.build();

		empleadoService.persistirEmpleado(emp1);
		empleadoService.persistirEmpleado(emp2);

		// Meter los telefonos
		// List<Telefono> telefonosEmpleado1 = new ArrayList<>();

		Telefono telefono1Empleado1 = Telefono.builder()
				.telefono("675458")
				.empleado(empleadoService.dameUnEmpleado(1))
				.build();

		Telefono telefono2Empleado1 = Telefono.builder()
				.telefono("675433")
				.empleado(empleadoService.dameUnEmpleado(1))
				.build();

		Telefono telefono3Empleado1 = Telefono.builder()
				.telefono("75458")
				.empleado(empleadoService.dameUnEmpleado(1))
				.build();

		// telefonosEmpleado1.add(telefono1Empleado1);
		// telefonosEmpleado1.add(telefono2Empleado1);
		// telefonosEmpleado1.add(telefono3Empleado1);

		telefonoService.persistirTelefono(1, telefono1Empleado1);
		telefonoService.persistirTelefono(1, telefono2Empleado1);
		telefonoService.persistirTelefono(1, telefono3Empleado1);

		// List<Telefono> telefonosEmpleado2 = new ArrayList<>();
		Telefono telefono1Empleado2 = Telefono.builder()
				.telefono("75458")
				.empleado(empleadoService.dameUnEmpleado(2))
				.build();

		Telefono telefono2Empleado2 = Telefono.builder()
				.telefono("75458")
				.empleado(empleadoService.dameUnEmpleado(2))
				.build();

		Telefono telefono3Empleado2 = Telefono.builder()
				.telefono("75458")
				.empleado(empleadoService.dameUnEmpleado(2))
				.build();

		// telefonosEmpleado2.add(telefono1Empleado2);
		// telefonosEmpleado2.add(telefono2Empleado2);
		// telefonosEmpleado2.add(telefono3Empleado2);

		telefonoService.persistirTelefono(2, telefono1Empleado2);
		telefonoService.persistirTelefono(2, telefono2Empleado2);
		telefonoService.persistirTelefono(2, telefono3Empleado2);

		// List<Correo> correosEmpleado1 = new ArrayList<>();

		Correo correo1Empleado1 = Correo.builder()
				.correo("andrea@serge.com")
				.build();

		correoService.persistirCorreo(1, correo1Empleado1);

		// correosEmpleado1.add(correo1Empleado1);

		// List<Correo> correosEmpleado2 = new ArrayList<>();

		Correo correo1Empleado2 = Correo.builder()
				.correo("andrea@serge.com")
				.build();

		Correo correo2Empleado2 = Correo.builder()
				.correo("coty@arnau.com")
				.build();

		correoService.persistirCorreo(2, correo1Empleado2);
		correoService.persistirCorreo(2, correo2Empleado2);

		// correosEmpleado2.add(correo1Empleado2);
		// correosEmpleado2.add(correo2Empleado2);

	}

}
