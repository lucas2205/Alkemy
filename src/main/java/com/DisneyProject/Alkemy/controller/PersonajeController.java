package com.DisneyProject.Alkemy.controller;

import com.DisneyProject.Alkemy.dto.PersonajeDto;
import com.DisneyProject.Alkemy.dto.PersonajeResponse;
import com.DisneyProject.Alkemy.services.PersonajeService;
import com.DisneyProject.Alkemy.utilities.AppConstantes;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public PersonajeResponse listarPersonajes(@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "order", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir,
            @RequestParam(value = "name", defaultValue = "", required = false) String nombre,
            @RequestParam(value = "age", defaultValue = "0", required = false) Integer edad,
            @RequestParam(value = "movies", defaultValue = "0", required = false) Long id) {

        if (!nombre.isEmpty() || edad != 0 || id != 0) {

            return personajeService.getByNombreOEdadOMovieId(numeroDePagina, medidaDePagina, ordenarPor, sortDir, nombre, edad, id);

        } else {

            return personajeService.getAllPersonajes(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDto> obtenerPorId(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(personajeService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PersonajeDto> guardarPersonaje(@Valid  @RequestBody PersonajeDto personajeDto) {
        return new ResponseEntity<>(personajeService.crearPersonaje(personajeDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDto> actualizarPorId(@Valid  @PathVariable(name = "id") long id,
            @RequestBody PersonajeDto personajeDto) {
        PersonajeDto personajeResponse = personajeService.updatePersonaje(personajeDto, id);

        return new ResponseEntity<>(personajeResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable(name = "id") long id) {

        personajeService.deletePersonaje(id);

        return new ResponseEntity<>("Personaje Eliminado...", HttpStatus.OK);
    }

}
