package com.DisneyProject.Alkemy.services;

import com.DisneyProject.Alkemy.dao.PersonajeDao;
import com.DisneyProject.Alkemy.dto.PersonajeDto;
import com.DisneyProject.Alkemy.dto.PersonajeResponse;
import com.DisneyProject.Alkemy.entity.Personaje;
import com.DisneyProject.Alkemy.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServiceImplement implements PersonajeService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonajeDao personajeDao;
    

    @Override
    public PersonajeDto crearPersonaje(PersonajeDto personajeDto) {

        Personaje personaje = mapearEntidad(personajeDto);

        Personaje newPersonaje = personajeDao.save(personaje);

        PersonajeDto personajeResponse = mapearDto(newPersonaje);

        return personajeResponse;
    }
    
    @Override
    public PersonajeResponse getAllPersonajes(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(ordenarPor).ascending()
                : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

        Page<Personaje> personajes = personajeDao.findAll(pageable);

        List<Personaje> listaDePersonajes = personajes.getContent();

        List<PersonajeDto> contenido = listaDePersonajes.stream().map(personaje -> mapearDto(personaje)).collect(Collectors.toList());

        PersonajeResponse personajeRespuesta = new PersonajeResponse();
        personajeRespuesta.setContenido(contenido);
        personajeRespuesta.setNumeroPagina(personajes.getNumber());
        personajeRespuesta.setMedidaPagina(personajes.getSize());
        personajeRespuesta.setTotalElementos(personajes.getTotalElements());
        personajeRespuesta.setTotalPaginas(personajes.getTotalPages());
        personajeRespuesta.setUltima(personajes.isLast());

        return personajeRespuesta;
    }

    @Override
    public PersonajeResponse getByNombreOEdadOMovieId(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir, String nombre, Integer edad, long id) {
        
    
    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(ordenarPor).ascending()
                : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

        Page<Personaje> personajes = personajeDao.findByNombreOrEdadOrMultimedia_PersonajesId(nombre, edad, id, pageable);

        List<Personaje> listaDePersonajes = personajes.getContent();

        List<PersonajeDto> contenido = listaDePersonajes.stream().map(personaje -> mapearDto(personaje)).collect(Collectors.toList());

        PersonajeResponse personajeRespuesta = new PersonajeResponse();
        personajeRespuesta.setContenido(contenido);
        personajeRespuesta.setNumeroPagina(personajes.getNumber());
        personajeRespuesta.setMedidaPagina(personajes.getSize());
        personajeRespuesta.setTotalElementos(personajes.getTotalElements());
        personajeRespuesta.setTotalPaginas(personajes.getTotalPages());
        personajeRespuesta.setUltima(personajes.isLast());

        return personajeRespuesta;
    
    }
     
    @Override
    public PersonajeDto getById(Long id) {

        Personaje personaje = personajeDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "Id", id));

        return mapearDto(personaje);

    }

    @Override
    public PersonajeDto updatePersonaje(PersonajeDto personajeDto, long id) {

        Personaje personaje = personajeDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "Id", id));

        personaje.setNombre(personajeDto.getNombre());
        personaje.setEdad(personajeDto.getEdad());
        personaje.setPeso(personajeDto.getPeso());
        personaje.setHistoria(personajeDto.getHistoria());
        personaje.setPathImagen(personajeDto.getPathImagen());

        Personaje personajeUpdated = personajeDao.save(personaje);

        return mapearDto(personajeUpdated);
    }
    
    @Override
    public void deletePersonaje(long id) {

        Personaje personaje = personajeDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "Id", id));

        personajeDao.delete(personaje);
    }

    
    
    
   
    private PersonajeDto mapearDto(Personaje personaje) {

        PersonajeDto personajeDto = modelMapper.map(personaje, PersonajeDto.class);

        return personajeDto;
    }

    
    private Personaje mapearEntidad(PersonajeDto personajeDto) {

        Personaje personaje = modelMapper.map(personajeDto, Personaje.class);
        return personaje;
    }

    

}
