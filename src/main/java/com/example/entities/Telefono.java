package com.example.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "telefonos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Telefono implements Serializable {

    private static final long serialVersionUID = 1L; // el serializado

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Empleado empleado;

}
