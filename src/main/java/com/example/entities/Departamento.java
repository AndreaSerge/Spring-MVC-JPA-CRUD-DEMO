package com.example.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Departamento implements Serializable {
    
    private static final long serialVersionUID = 1L; // el serializado

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nombre;
    
    // Vamos a decirle al departamento, que puede tener muchos empleados
    // mappedby: la relacion la mapea un campo que he puesto en la otra entidad. La tabla departamento es la
    // que posee la relación
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "departamento")
    private List<Empleado> empleados;


}
