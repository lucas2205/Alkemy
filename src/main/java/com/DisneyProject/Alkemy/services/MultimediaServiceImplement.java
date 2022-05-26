package com.DisneyProject.Alkemy.services;

import com.DisneyProject.Alkemy.dao.MultimediaDao;
import com.DisneyProject.Alkemy.dao.PersonajeDao;
import com.DisneyProject.Alkemy.dto.MultimediaDto;
import com.DisneyProject.Alkemy.dto.MultimediaResponse;
import com.DisneyProject.Alkemy.entity.Multimedia;
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
public class MultimediaServiceImplement implements MultimediaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MultimediaDao multimediaDao;

    @Autowired
    private PersonajeDao personajeDao;

    @Override
    public MultimediaResponse getAllMultimedia(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(ordenarPor).ascending()
                : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

        Page<Multimedia> multimedia = multimediaDao.findAll(pageable);

        List<Multimedia> listaDePersonajes = multimedia.getContent();
        List<MultimediaDto> contenido = listaDePersonajes.stream().map(personaje -> mapearDto(personaje))
                .collect(Collectors.toList());

        MultimediaResponse multimediaRespuesta = new MultimediaResponse();
        multimediaRespuesta.setContenido(contenido);
        multimediaRespuesta.setNumeroPagina(multimedia.getNumber());
        multimediaRespuesta.setMedidaPagina(multimedia.getSize());
        multimediaRespuesta.setTotalElementos(multimedia.getTotalElements());
        multimediaRespuesta.setTotalPaginas(multimedia.getTotalPages());
        multimediaRespuesta.setUltima(multimedia.isLast());

        return multimediaRespuesta;
    }

    @Override
    public void deleteMultimedia(long id) {

        Multimedia multimedia = multimediaDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Multimedia", "Id", id));

        multimediaDao.delete(multimedia);
    }

    @Override
    public MultimediaDto updateMultimedia(MultimediaDto multimediaDto, long id) {

        Multimedia multimedia = multimediaDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Multimedia", "Id", id));

        multimedia.setTitulo(multimediaDto.getTitulo());
        multimedia.setFechaCreacion(multimediaDto.getFechaCreacion());
        multimedia.setCalificacion(multimediaDto.getCalificacion());
        multimedia.setPathImagen(multimediaDto.getPathImagen());

        Multimedia multimediaUpdated = multimediaDao.save(multimedia);

        return mapearDto(multimediaUpdated);

    }

    @Override
    public MultimediaDto crearMultimedia(MultimediaDto multimediaDto) {

        Multimedia multimedia = mapearEntidad(multimediaDto);

        Multimedia newMultimedia = multimediaDao.save(multimedia);

        MultimediaDto multimediaResponse = mapearDto(newMultimedia);

        return multimediaResponse;
    }

    @Override
    public void deletePersonajeMultimedia(long movieId, long charId) {

        Personaje personaje = personajeDao.findById(charId)
                .orElseThrow(() -> new ResourceNotFoundException("personaje", "Id", charId));

        Multimedia multimedias = multimediaDao.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("multimedia", "Id", movieId));

        multimedias.removePersonaje(personaje.getId());
        multimediaDao.save(multimedias);

    }

    @Override
    public void crearPersonajeMultimedia(long movieId, long charId) {

        Personaje personaje = personajeDao.findById(charId)
                .orElseThrow(() -> new ResourceNotFoundException("personaje", "Id", charId));

        Multimedia multimedias = multimediaDao.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("multimedia", "Id", movieId));

        multimedias.addPersonaje(personaje);
        multimediaDao.save(multimedias);

    }
    
    
    
    private MultimediaDto mapearDto(Multimedia multimedia) {

        MultimediaDto multimediaDto = modelMapper.map(multimedia, MultimediaDto.class);

        return multimediaDto;
    }

    private Multimedia mapearEntidad(MultimediaDto multimediaDto) {

        Multimedia multimedia = modelMapper.map(multimediaDto, Multimedia.class);
        return multimedia;
    }

    @Override
    public MultimediaResponse getByNombreOGenero(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir, String nombre, String id ) {
        
     Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(ordenarPor).ascending()
                : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

        Page<Multimedia> multimedia = multimediaDao.findByGeneroIdOrTitulo(id, nombre, pageable);

        List<Multimedia> listaDePersonajes = multimedia.getContent();
        List<MultimediaDto> contenido = listaDePersonajes.stream().map(personaje -> mapearDto(personaje))
                .collect(Collectors.toList());

        MultimediaResponse multimediaRespuesta = new MultimediaResponse();
        multimediaRespuesta.setContenido(contenido);
        multimediaRespuesta.setNumeroPagina(multimedia.getNumber());
        multimediaRespuesta.setMedidaPagina(multimedia.getSize());
        multimediaRespuesta.setTotalElementos(multimedia.getTotalElements());
        multimediaRespuesta.setTotalPaginas(multimedia.getTotalPages());
        multimediaRespuesta.setUltima(multimedia.isLast());

        return multimediaRespuesta;
    
    }

}
