package com.example.services;

import java.util.List;

import com.example.entities.Empleado;

public interface EmpleadoService {

    // Método que me devuelve todos los empleados
    public List<Empleado> dameTodosLosEmpleados();

    // Método que me devuelve un empleado por ID
    public Empleado dameUnEmpleado(int idEmpleado);

    // Método que elimine un empleado por ID y no devuelva nada
    public void eliminarEmpleado(int idEmpleado);

    // Método para guardar(persistir) un empleado
    public void persistirEmpleado(Empleado empleado);

    // Método para actualizar el empleado
    public void actualizarEmpleado(Empleado empleado);

}
