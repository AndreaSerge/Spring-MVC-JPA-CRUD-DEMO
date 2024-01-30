package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Empleado;
import java.util.List;


// esta interface es generica <para quien quiere generar esos, tipo de dato objeto>
// objeto porque (la genericidad no trabaja con datos primitivos)

@Repository // el me va a permitir indicar donde se va a crear el bean.
// Es una de las anotaciones que se llama stereotype. Me va a generar todos los metodos del DAO
// Para todas las entidades que yo le indique. Genera todo el DAO para la clase empleado
// Jpa hereda de repository. Muy buen ejemplo de genericidad goToDefinition
// Si no te basta con los métodos ya existentes en el crud, puedes inicializarlo, y te lo genera

public interface EmpleadoDao extends JpaRepository<Empleado, Integer> {
    
    List<Empleado> findByNombre(String nombre);



}
