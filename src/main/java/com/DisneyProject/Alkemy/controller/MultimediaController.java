package com.DisneyProject.Alkemy.controller;

import com.DisneyProject.Alkemy.dto.MultimediaDto;
import com.DisneyProject.Alkemy.dto.MultimediaResponse;
import com.DisneyProject.Alkemy.services.MultimediaService;
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
@RequestMapping("/movies")
public class MultimediaController {

    @Autowired
    private MultimediaService multimediaService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public MultimediaResponse listarMultimedia(@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "order", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir,
            @RequestParam(value = "name", defaultValue = "", required = false) String nombre,
            @RequestParam(value = "genre", defaultValue = "", required = false) String idGenero) {

        if (!nombre.isEmpty() || !idGenero.isEmpty()) {

            return multimediaService.getByNombreOGenero(numeroDePagina, medidaDePagina, ordenarPor, sortDir, nombre, idGenero);

        } else {

            return multimediaService.getAllMultimedia(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MultimediaDto> guardarMultimedia(@Valid @RequestBody MultimediaDto multimediaDto) {
        return new ResponseEntity<>(multimediaService.crearMultimedia(multimediaDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{movieId}/characters/{charId}")
    public ResponseEntity<String> guardarPersonajeEnMultimedia(@Valid @PathVariable(name = "movieId") long movieId,
            @PathVariable(name = "charId") long charId) {

        multimediaService.crearPersonajeMultimedia(movieId, charId);

        return new ResponseEntity<>("Personaje Creado ...", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MultimediaDto> actualizarPorId(@Valid @PathVariable(name = "id") long id,
            @RequestBody MultimediaDto multimediaDto) {
        MultimediaDto multimediaResponse = multimediaService.updateMultimedia(multimediaDto, id);

        return new ResponseEntity<>(multimediaResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable(name = "id") long id) {

        multimediaService.deleteMultimedia(id);

        return new ResponseEntity<>("Multimedia Eliminado...", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{movieId}/characters/{charId}")
    public ResponseEntity<String> deletePersonajeById(@PathVariable(name = "movieId") long movieId,
            @PathVariable(name = "charId") long charId) {

        multimediaService.deletePersonajeMultimedia(movieId, charId);

        return new ResponseEntity<>("Personaje Eliminado...", HttpStatus.OK);
    }

}
