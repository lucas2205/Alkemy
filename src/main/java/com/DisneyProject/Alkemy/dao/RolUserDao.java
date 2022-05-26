

package com.DisneyProject.Alkemy.dao;


import com.DisneyProject.Alkemy.entity.RolUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface RolUserDao extends JpaRepository<RolUser, Long>{

	public Optional<RolUser> findByNombre(String nombre);
	
}