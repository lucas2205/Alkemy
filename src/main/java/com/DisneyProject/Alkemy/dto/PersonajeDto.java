package com.DisneyProject.Alkemy.dto;

import com.DisneyProject.Alkemy.entity.Multimedia;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonajeDto {

    private long id;

    @NotEmpty
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres...")
    String nombre;

    @NotEmpty
    @Size(min = 2, message = "La edad no debe estar vacia...")
    int edad;

    @NotEmpty
    @Size(min = 2, message = "El peso no debe estar vacio...")
    int peso;

    @NotEmpty
    @Size(min = 2, message = "La historia debe tener al menos 10 caracteres...")
    String historia;

    @NotEmpty
    @Size(min = 2, message = "Agregar directorio de imagen...")
    String pathImagen;

    Set<Multimedia> multimedia;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Set<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Set<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public PersonajeDto() {
        super();
    }

}
