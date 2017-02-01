package com.fundaciones.andrearodriguez.fundaciones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public class Eventos {

    @JsonIgnore
    private String id;

    @JsonIgnore
    private boolean publishedByMe;

    private String url;
    private String email;
    private String nombre;
    private String lugar;
    private String fecha;
    private String hora;
    private String tipoevento;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPublishedByMe() {
        return publishedByMe;
    }

    public void setPublishedByMe(boolean publishedByMe) {
        this.publishedByMe = publishedByMe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoevento() {
        return tipoevento;
    }

    public void setTipoevento(String tipoevento) {
        this.tipoevento = tipoevento;
    }
}
