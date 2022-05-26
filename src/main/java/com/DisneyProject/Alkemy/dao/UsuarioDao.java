
package com.DisneyProject.Alkemy.dao;


import com.DisneyProject.Alkemy.entity.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioDao extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail(String email);
	
	public Optional<Usuario> findByUsernameOrEmail(String username,String email);
	
	public Optional<Usuario> findByUsername(String username);
	
	public Boolean existsByUsername(String username);
	
	public Boolean existsByEmail(String email);
	
}