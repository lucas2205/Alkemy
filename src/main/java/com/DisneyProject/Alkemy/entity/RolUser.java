package com.DisneyProject.Alkemy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity
@Table(name = "roles")
public class RolUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Column(columnDefinition = "varchar(50) default 'ROLE_ADMIN' 'ROLE_USER'")
    private String nombre;

    @PrePersist
    public void prePersist() {
        if (nombre == null || nombre.isEmpty()) {
            nombre = "ROLE_ADMIN";
        }
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

    public RolUser() {
        super();
    }

}
