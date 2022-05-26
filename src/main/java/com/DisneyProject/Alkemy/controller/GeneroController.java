package com.DisneyProject.Alkemy.controller;

import com.DisneyProject.Alkemy.dto.GeneroDto;
import com.DisneyProject.Alkemy.services.GeneroService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genre")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<GeneroDto> guardarGenero(@Valid @RequestBody GeneroDto generoDto) {
        return new ResponseEntity<>(generoService.crearGenero(generoDto), HttpStatus.CREATED);
    }
}
