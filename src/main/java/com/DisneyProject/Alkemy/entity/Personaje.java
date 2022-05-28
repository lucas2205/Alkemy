package com.DisneyProject.Alkemy.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Personaje")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nombre;
    int edad;
    int peso;
    
    @Column(columnDefinition="varchar(1000)")
    String historia;
    String pathImagen;
    

    @JsonManagedReference
    @ManyToMany(mappedBy = "personajes")
    private Set<Multimedia> multimedia;

    
    public Personaje(Long id, String nombre, int edad, int peso, String historia, Set<Multimedia> multimedia) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.multimedia = multimedia;
    }

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    } 
    
    public Personaje() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}
