package com.example.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Esta clase me va a ayudar a crear una tabla donde cada campo corresponde a un atributo

@Entity // para que la calse se convierta en una entidad de Hibernate
@Table(name= "empleados") // anotación para crear una tabla
// Necesitamos crearle los geter and setter, y los constructores, asì que llamamos a Lombok
// su objetivo es evitar la escritura de código técnico repetitivo
@Data // 
@Builder
@NoArgsConstructor
@AllArgsConstructor

// Implementa serializable porque esta se va a convertir en un flujo
// Serializar: convertir de objeto a un flujo. Para Serializarla hace falta un numero, para
// que se pueda usar ese numero de serie cuando toque deserialziar

public class Empleado implements Serializable { 

    private static final long serialVersionUID = 1L; // el serializado

    @Id // esta es la primaryKey
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Hago que el ID sea autoincremental
    private int id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;

    // Tengo que espeficifar cómo quiero que guarde los campos de tipo LocalDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAlta;
    private double salario;

    // Tengo que espeficifar como quiero que guarde los campos de tipo enum
    // Prefiero llamar en String que en ordinal
    @Enumerated(EnumType.STRING)
    private Genero genero;


    // Nombre de la foto porque el contenido
    private String foto;

    // Quiero que el empleado tenga un departamento en el que trabaja. relacion Empleado-Departamento
    // Pero tengo que decirle cómo se relacionan
    // Quiero que si se hace una búsqueda me la haga cuando yo se la pida
    // cascade: como quiero que el cambio se propague
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Departamento departamento;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empleado")
    private List<Correo> correos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empleado")
    private List<Telefono> telefonos;


}
