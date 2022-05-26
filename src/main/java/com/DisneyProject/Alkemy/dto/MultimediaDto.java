package com.DisneyProject.Alkemy.dto;

import com.DisneyProject.Alkemy.entity.Personaje;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MultimediaDto {

    private long id;

    @NotEmpty
    @Size(min = 2, message = "El titulo debe tener al menos 2 caracteres...")
    String titulo;

    @NotEmpty
    @Size(min = 2, message = "La fecha no debe estar vacia...")
    String fechaCreacion;

    @NotEmpty
    @Size(min = 2, message = "La calificacion no debe estar vacia...")
    byte calificacion;

    @NotEmpty
    @Size(min = 2, message = "Agregar directorio de imagen...")
    String pathImagen;

    List<Personaje> personajes;

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public byte getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(byte calificacion) {
        this.calificacion = calificacion;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

}
