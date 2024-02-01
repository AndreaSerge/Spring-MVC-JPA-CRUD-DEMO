package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.DepartamentoDao;
import com.example.entities.Departamento;

import lombok.RequiredArgsConstructor;

@Service

// Para variar haremos inyección de dependencia (DI) por constructor (la que más se usa)
@RequiredArgsConstructor

public class DepartamentoServiceImpl implements DepartamentoService{

// Dame un departamento: se lo pido al dao
// Está inyectado
//departamentoDao está listo para el uso
private final DepartamentoDao departamentoDao;

    @Override
    public List<Departamento> dameDepartamentos() {
        return departamentoDao.findAll();
    }

    //el método findById devuelve un opcional<departamento>, y yo lo recupero con el get
    @Override
    public Departamento dameUnDepartamento(int idDepartamento) {
        return departamentoDao.findById(idDepartamento).get();
    }

    @Override
    public void persistirDpto(Departamento departamento) {
       departamentoDao.save(departamento);
    }

    

}
