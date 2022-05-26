

package com.DisneyProject.Alkemy.security;

import com.DisneyProject.Alkemy.dao.UsuarioDao;
import com.DisneyProject.Alkemy.entity.Usuario;
import com.DisneyProject.Alkemy.entity.RolUser;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{
    
    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
       
        Usuario usuario = usuarioDao.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email"));
        
        return new User(usuario.getEmail(),usuario.getPassword(),mapearRoles(usuario.getRoles()));
    
    }
    
    private Collection<? extends GrantedAuthority> mapearRoles(Set<RolUser> roles){
        
        return roles.stream().map(rol-> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
    }

}
