package com.example.services;

import java.util.List;

import com.example.entities.Correo;

public interface CorreoService {

    public List<Correo> dameCorreos(int idEmpleado);

    public void eliminaCorreos(int idEmpleado);

    public void persistirCorreo(int idEmpleado, Correo correo);

}
