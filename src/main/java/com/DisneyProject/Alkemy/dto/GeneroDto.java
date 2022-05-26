package com.DisneyProject.Alkemy.dto;

import com.DisneyProject.Alkemy.entity.Multimedia;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class GeneroDto {

    private long id;

    @NotEmpty
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres...")
    String nombre;

    List<Multimedia> multimedia;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

}
