package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.EmpleadoDao;
import com.example.dao.TelefonoDao;
import com.example.entities.Telefono;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class TelefonoServiceImpl implements TelefonoService{
    
    // El TelefonoDao porque es allí donde cree el bean con repository
    private final TelefonoDao telefonoDao;

    private final EmpleadoDao empleadoDao; // la llamo porque necesito uno de sus métodos
    
    // Quiero pedirle al Dao que me devuelva todos los telefonos de un empleado, considerando el empleado, no el id de telefono
    // Puedo crear un método findByEmpleado en TelefonoDao y luego llamarlo aquí
    // Las consultas las prepara pensando en las entidades, no en las tablas -> ORM
    @Override
    public List<Telefono> telefonos(int idEmpleado) {
        return telefonoDao.findByEmpleado(empleadoDao.findById(idEmpleado).get());
    }

    @Override
    public void eliminarTelefonos(int idEmpleado) {
        telefonoDao.deleteByEmpleado(empleadoDao.findById(idEmpleado).get());
    }

    // persistir necesita ser guardado, pero debe establecersele el Id del empleado
    // Entonces vincular con set empleado
    @Override
    public void persistirTelefono(int idEmpleado, Telefono telefono) {
        telefono.setEmpleado(empleadoDao.findById(idEmpleado).get());
        telefonoDao.save(telefono);
    }

}
