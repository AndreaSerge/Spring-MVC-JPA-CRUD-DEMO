package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Empleado;
import com.example.entities.Telefono;

@Repository
public interface TelefonoDao extends JpaRepository<Telefono,Integer>{

    // Son public abstrat, nunca podrán ser privados, si no les pongo los modificadores de acceso, no pasa nada

    // Creo este método para buscar telefono por id del empleado
    // Esto porque los necesito despues en telefonoServiceImpl
    List<Telefono> findByEmpleado(Empleado empleado);
    
    // Borrame por empleado
    void deleteByEmpleado(Empleado empleado);

}
