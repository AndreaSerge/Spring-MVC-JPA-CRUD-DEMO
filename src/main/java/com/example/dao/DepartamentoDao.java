package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Departamento;

@Repository // Para que el framework spring se entere de que hay bean, para que los pueda crear

public interface DepartamentoDao extends JpaRepository<Departamento,Integer> {

}
