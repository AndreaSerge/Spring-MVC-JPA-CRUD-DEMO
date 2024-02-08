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

		// Primero : Creamos departamentos

		Departamento dpt1 = Departamento.builder()
				.nombre("RRHH")
				.build();
		
		Departamento dpt2 = Departamento.builder()
				.nombre("INFORMATICA")
				.build();

		Departamento dpt3 = Departamento.builder()
				.nombre("CONTABILIDAD")
				.build();

		// departamentoService.persitirDpto(dpt1);		
		// departamentoService.persitirDpto(dpt2);
		// departamentoService.persitirDpto(dpt3);


		// Empleados

		Empleado emp1 = Empleado.builder()
			.nombre("Aurora")
			.primerApellido("Duque")
			.fechaAlta(LocalDate.of(2000, Month.JANUARY, 12))
			.salario(2500.50)
			.departamento(dpt1)
			.genero(Genero.OTRO)
			.build();

		Empleado emp2 = Empleado.builder()
			.nombre("Rosa Aurora")
			.primerApellido("Duque")
			.fechaAlta(LocalDate.of(2000, Month.JANUARY, 12))
			.salario(2540.50)
			.departamento(dpt2)
			.genero(Genero.MUJER)
			.build();

		// Telefonos 
		
		List<Telefono> telefonosEmpleado1 = new ArrayList<>(); 

		Telefono telefono1Empleado1 = Telefono.builder()
			.telefono("96123456")
			.empleado(emp1)
			.build();

		Telefono telefono2Empleado1 = Telefono.builder()
			.telefono("97123456") 
			.empleado(emp1)
			.build();

		telefonosEmpleado1.add(telefono1Empleado1);
		telefonosEmpleado1.add(telefono2Empleado1);	

		emp1.setTelefonos(telefonosEmpleado1);

		List<Telefono> telefonosEmpleado2 = new ArrayList<>(); 

		Telefono telefono1Empleado2 = Telefono.builder()
			.telefono("98123456")
			.empleado(emp2)
			.build();

		Telefono telefono2Empleado2 = Telefono.builder()
			.telefono("99123456") 
			.empleado(emp2)
			.build();

		telefonosEmpleado2.add(telefono1Empleado2);
		telefonosEmpleado2.add(telefono2Empleado2);

		emp2.setTelefonos(telefonosEmpleado2);

		// Correos 

		List<Correo> correosEmpleado1 = new ArrayList<>();

		Correo correo1Empleado1 = Correo.builder()
			.correo("aaaa@gmail.com")
			.empleado(emp1)
			.build();

		Correo correo2Empleado1 = Correo.builder()
			.correo("bbbb@gmail.com")
			.empleado(emp1)
			.build();

		correosEmpleado1.add(correo1Empleado1);
		correosEmpleado1.add(correo2Empleado1);

		emp1.setCorreos(correosEmpleado1);

		List<Correo> correosEmpleado2 = new ArrayList<>();

		Correo correo1Empleado2 = Correo.builder()
			.correo("ccccc@gmail.com")
			.empleado(emp2)
			.build();

		Correo correo2Empleado2 = Correo.builder()
			.correo("dddd@gmail.com")
			.empleado(emp2)
			.build();

		correosEmpleado2.add(correo1Empleado2);
		correosEmpleado2.add(correo2Empleado2);

		emp2.setCorreos(correosEmpleado2);

		empleadoService.persistirEmpleado(emp1);
		empleadoService.persistirEmpleado(emp2);
	}

}
