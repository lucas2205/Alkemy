
package com.DisneyProject.Alkemy.dao;

import com.DisneyProject.Alkemy.entity.Multimedia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MultimediaDao extends JpaRepository<Multimedia, Long>{
    
     public Page<Multimedia> findById(Long id, Pageable page);
     
     
     @Query(
     value= "SELECT * FROM multimedia t where t.genero_id= ?1 or t.titulo= ?2",
     nativeQuery = true        
     )
     public Page<Multimedia> findByGeneroIdOrTitulo(String id, String titulo, Pageable page);
    
}
