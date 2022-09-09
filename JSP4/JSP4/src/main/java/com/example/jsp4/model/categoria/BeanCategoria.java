package com.example.jsp4.model.categoria;

public class BeanCategoria {

    private int id;
    private String nombre;

    public BeanCategoria() {
    }

    public BeanCategoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
    
}
