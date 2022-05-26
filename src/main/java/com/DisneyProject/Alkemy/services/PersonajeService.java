package com.DisneyProject.Alkemy.services;

import com.DisneyProject.Alkemy.dto.PersonajeDto;
import com.DisneyProject.Alkemy.dto.PersonajeResponse;

public interface PersonajeService {

    public PersonajeDto crearPersonaje(PersonajeDto personajeDto);

    public PersonajeResponse getAllPersonajes(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

    public PersonajeResponse getByNombreOEdadOMovieId(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir, String nombre, Integer edad, long id);

    public PersonajeDto getById(Long id);

    public PersonajeDto updatePersonaje(PersonajeDto personajeDto, long id);

    public void deletePersonaje(long id);

}
