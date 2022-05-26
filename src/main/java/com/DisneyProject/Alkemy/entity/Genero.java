package com.DisneyProject.Alkemy.entity;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "genero")
public class Genero {
    
    @Id
    private String nombre;
     
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id")
    private List<Multimedia> multimedia;
    
    //Imagen

   

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

    public Genero(String nombre, List<Multimedia> multimedia) {
        
        this.nombre = nombre;
        this.multimedia = multimedia;
    }

    public Genero() {
    }

}
