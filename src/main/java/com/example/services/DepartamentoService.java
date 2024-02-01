package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entities.Departamento;

// No necesita anotación porque son métodos vacíos, la anotación debe ir en la implementación

public interface DepartamentoService {
    // No voy ni a crear, ni actualizar ni cambiar departamento

    // Método que me devuelve una lista de departamentos
    public List<Departamento> dameDepartamentos();

    // Método que da un departamento según el id del empleado
    public Departamento dameUnDepartamento(int idDepartamento);


    //Método que
    public void persistirDpto(Departamento departamento);




}
