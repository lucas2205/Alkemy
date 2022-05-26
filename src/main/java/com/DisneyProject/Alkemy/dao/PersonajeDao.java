
package com.DisneyProject.Alkemy.dao;

import com.DisneyProject.Alkemy.entity.Personaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonajeDao extends JpaRepository<Personaje, Long>{
     
     public Page<Personaje> findByNombreOrEdadOrMultimedia_PersonajesId(String nombre, int edad, long id, Pageable page);
      
    
}
