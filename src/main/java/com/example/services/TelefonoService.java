package com.example.services;

import java.util.List;

import com.example.entities.Empleado;
import com.example.entities.Telefono;

public interface TelefonoService {

    // Método que me devuelve todos los télefonos
    public List<Telefono> telefonos(int idEmpleado);

    // Método que borre los télefonos correspondientes a un empleado
    public void eliminarTelefonos(int idEmpleado);

    // Método que guarde(persistir) télefono. Necesita el empleado para el que quiero persistir
    public void persistirTelefono(int idEmpleado, Telefono telefono);




}
