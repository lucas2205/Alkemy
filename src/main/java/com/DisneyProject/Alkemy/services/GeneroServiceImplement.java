package com.DisneyProject.Alkemy.services;

import com.DisneyProject.Alkemy.dao.GeneroDao;
import com.DisneyProject.Alkemy.dto.GeneroDto;
import com.DisneyProject.Alkemy.entity.Genero;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GeneroServiceImplement implements GeneroService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GeneroDao generoDao;

    @Override
    public GeneroDto crearGenero(GeneroDto generoDto) {

        Genero genero = mapearEntidad(generoDto);

        Genero newGenero = generoDao.save(genero);

        GeneroDto generoResponse = mapearDto(newGenero);

        return generoResponse;

    }

    //Convierte Entidad a DTO
    private GeneroDto mapearDto(Genero genero) {

        GeneroDto generoDto = modelMapper.map(genero, GeneroDto.class);

        return generoDto;
    }

    //Convierte DTO a Entidad
    private Genero mapearEntidad(GeneroDto generoDto) {

        Genero genero = modelMapper.map(generoDto, Genero.class);
        return genero;
    }

}
