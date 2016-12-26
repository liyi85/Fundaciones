package com.example.andrearodriguez.fundaciones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public class Paticas {

    @JsonIgnore
    private String id;

    @JsonIgnore
    private boolean publishedByMe;

    private String url;
    private String email;
    private String nombre;
    private String edad;
    private String tamaño;
    private String sexo;
    private String fundacion;
    private String vacunacion;
    private String esterilizacion;
    private String discapacitado;

    public String getDiscapacitado() {
        return discapacitado;
    }

    public void setDiscapacitado(String discapacitado) {
        this.discapacitado = discapacitado;
    }

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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFundacion() {
        return fundacion;
    }

    public void setFundacion(String fundacion) {
        this.fundacion = fundacion;
    }

    public String getVacunacion() {
        return vacunacion;
    }

    public void setVacunacion(String vacunacion) {
        this.vacunacion = vacunacion;
    }

    public String getEsterilizacion() {
        return esterilizacion;
    }

    public void setEsterilizacion(String esterilizacion) {
        this.esterilizacion = esterilizacion;
    }
}
