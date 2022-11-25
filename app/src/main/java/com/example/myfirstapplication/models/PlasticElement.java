package com.example.myfirstapplication.models;

import java.io.Serializable;
import java.util.HashMap;

public class PlasticElement implements Serializable {
    private String nombre;
    private String categoria;
    private HashMap<String, Float> medidas;

    public PlasticElement(
            String nombre,
            String categoria,
            HashMap<String, Float> medidas){
        this.nombre = nombre;
        this.categoria = categoria;
        this.medidas = medidas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<String, Float> getMedidas() {
        return medidas;
    }

    public void setMedidas(HashMap<String, Float> medidas) {
        this.medidas = medidas;
    }
}
