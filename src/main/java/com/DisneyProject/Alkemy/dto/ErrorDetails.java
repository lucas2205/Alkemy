

package com.DisneyProject.Alkemy.dto;

import java.util.Date;


public class ErrorDetails {
    
    private Date marcaDeTiempo;
    private String mensaje;
    private String Detalles;

    public ErrorDetails(Date marcaDeTiempo, String mensaje, String Detalles) {
        this.marcaDeTiempo = marcaDeTiempo;
        this.mensaje = mensaje;
        this.Detalles = Detalles;
    }

    public Date getMarcaDeTiempo() {
        return marcaDeTiempo;
    }

    public void setMarcaDeTiempo(Date marcaDeTiempo) {
        this.marcaDeTiempo = marcaDeTiempo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalles() {
        return Detalles;
    }

    public void setDetalles(String Detalles) {
        this.Detalles = Detalles;
    }

}
