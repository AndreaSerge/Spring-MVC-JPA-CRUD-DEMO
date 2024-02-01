package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Correo;
import com.example.entities.Empleado;

@Repository // Me genera los métodos que necesito para darle forma a esta interface

public interface CorreoDao extends JpaRepository<Correo, Integer>{

    List<Correo> findByEmpleado(Empleado empleado);
    void deleteByEmpleado(Empleado empleado);
    

}
