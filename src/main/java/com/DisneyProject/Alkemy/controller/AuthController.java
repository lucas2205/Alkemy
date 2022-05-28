

package com.DisneyProject.Alkemy.controller;

import com.DisneyProject.Alkemy.entity.Usuario;
import com.DisneyProject.Alkemy.entity.RolUser;
import com.DisneyProject.Alkemy.dao.UsuarioDao;
import com.DisneyProject.Alkemy.dao.RolUserDao;
import com.DisneyProject.Alkemy.dto.LoginDto;
import com.DisneyProject.Alkemy.dto.RegisterDto;
import com.DisneyProject.Alkemy.security.JwtTokenProvider ;
import com.DisneyProject.Alkemy.security.JwtAuthResponseDto;
import com.DisneyProject.Alkemy.services.MailService;
import com.DisneyProject.Alkemy.utilities.AppConstantes;
        
        
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private MailService mailService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioDao UsuarioDao;
    
    @Autowired
    private RolUserDao rolDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> authenticateUser(@RequestBody LoginDto loginDto) {
        
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));
    
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        
        String token = jwtTokenProvider.generateToken(authentication);
        
        return ResponseEntity.ok(new JwtAuthResponseDto(token));
        
    
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUsuario(@RequestBody RegisterDto registerDto){
        
        if(UsuarioDao.existsByUsername(registerDto.getUsername())){
            
            return new ResponseEntity<>("Ese usuario ya existe",HttpStatus.BAD_REQUEST);
        }
        
         if(UsuarioDao.existsByEmail(registerDto.getEmail())){
            
            return new ResponseEntity<>("Ese Email ya existe",HttpStatus.BAD_REQUEST);
        }
        
    Usuario usuario = new Usuario();
    usuario.setNombre(registerDto.getNombre());
    usuario.setUsername(registerDto.getUsername());
    usuario.setEmail(registerDto.getEmail());
    usuario.setPassword(passwordEncoder.encode(registerDto.getPassword()));
    
    if (rolDao.findByNombre("ROLE_ADMIN").isEmpty()) {
        
        RolUser role1 = new RolUser();
        RolUser role2 = new RolUser();
        
            role2.setNombre("ROLE_USER");
            rolDao.save(role2);
            role1.setNombre("ROLE_ADMIN");
            rolDao.save(role1);
        }
    
    RolUser roles = rolDao.findByNombre("ROLE_ADMIN").get();
    
    usuario.setRoles(Collections.singleton(roles));
    
    UsuarioDao.save(usuario);
    mailService.sendEmail(registerDto.getEmail());
    
    return new ResponseEntity<>("Usuario Registrado Correctamente",HttpStatus.OK);
    
    }
    

}
