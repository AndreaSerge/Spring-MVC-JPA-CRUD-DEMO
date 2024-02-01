package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Empleado;
import com.example.dao.EmpleadoDao;

@Service // tiene que tener 
public class EmpleadoServiceImpl implements EmpleadoService {

    // Crear una variable de objeto empleadoDao
    // Necesitamos inyectarla como una dependencia, para usamos autowire
    // El va a buscar un bean de tipo empleado, y lo inyecta aquí
    @Autowired
    private EmpleadoDao empleadoDao;

    @Override
    public List<Empleado> dameTodosLosEmpleados() {
        // TODO Auto-generated method stub
        return empleadoDao.findAll();
    }

    @Override
    public Empleado dameUnEmpleado(int idEmpleado) {
        // TODO Auto-generated method stub
        return empleadoDao.findById(idEmpleado).get();
    }

    @Override
    public void eliminarEmpleado(int idEmpleado) {
        // TODO Auto-generated method stub
        empleadoDao.deleteById(idEmpleado);
    }

    @Override
    public void persistirEmpleado(Empleado empleado) {
        // TODO Auto-generated method stub
        empleadoDao.save(empleado);
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        // TODO Auto-generated method stub
        empleadoDao.save(empleado);
    }

}
