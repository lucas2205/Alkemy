package com.DisneyProject.Alkemy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "multimedia")
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String fechaCreacion;
    private byte calificacion;
    String pathImagen;

    @JsonBackReference
    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "asociados",
            joinColumns = {@JoinColumn(name = "personaje_id")},
            inverseJoinColumns = {@JoinColumn(name = "multimedia_id")})
    private Set<Personaje> personajes;

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }
    

    

    public void addPersonaje(Personaje personaje){
        this.personajes.add(personaje);
        personaje.getMultimedia().add(this);
    }
    
    public void removePersonaje(long personajeId) {
    Personaje personaje = this.personajes.stream().filter(t -> t.getId() == personajeId).findFirst().orElse(null);
    if (personaje != null) {
      this.personajes.remove(personaje);
      personaje.getMultimedia().remove(this);
    }
  }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(Set<Personaje> personajes) {
        this.personajes = personajes;
    }

    public Multimedia() {
    }
    
     public Multimedia(Long id, String titulo, String fechaCreacion, byte calificacion, Set<Personaje> personajes) {
        this.id = id;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.personajes = personajes;
    }

}
