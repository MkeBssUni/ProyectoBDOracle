package com.example.jsp4.model.wl;

public class BeanWl {
    private long id;
    private String titulo;

    public BeanWl() {

    }

    public BeanWl(long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public long getId() { return id; }
    public String getTitulo() { return titulo; }

    public void setId(long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
}
