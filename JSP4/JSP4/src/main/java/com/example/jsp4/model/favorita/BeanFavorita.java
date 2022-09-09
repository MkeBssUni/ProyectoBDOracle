package com.example.jsp4.model.favorita;

public class BeanFavorita {
    private long id;
    private String titulo;
    private double calificacion;

    public BeanFavorita() {

    }

    public BeanFavorita(long id, String titulo,  double calificacion) {
        this.id = id;
        this.titulo = titulo;
        this.calificacion = calificacion;
    }

    public long getId() { return id; }
    public String getTitulo() { return titulo; }
    public double getCalificacion() { return calificacion; }

    public void setId(long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setCalificacion(double calificacion) { this.calificacion = calificacion; }
    
}
