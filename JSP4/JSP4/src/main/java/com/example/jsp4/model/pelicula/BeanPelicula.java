package com.example.jsp4.model.pelicula;
import com.example.jsp4.model.pelicula.BeanPelicula;

public class BeanPelicula {
    private long id;
    private String titulo;
    private String director;
    private String productor;
    private String escritor;
    private int estreno;
    private double calificacion;
    private int id_categoria;
    private String categoria;

    public BeanPelicula() {

    }

    public BeanPelicula(long id, String titulo, String director, String productor, String escritor, int estreno, double calificacion, int id_categoria, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.productor = productor;
        this.escritor = escritor;
        this.estreno = estreno;
        this.calificacion = calificacion;
        this.id_categoria = id_categoria;
        this.categoria = categoria;
    }

    public long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDirector() { return director; }
    public String getProductor() { return productor; }
    public String getEscritor() { return escritor; }
    public int getEstreno() { return estreno; }
    public double getCalificacion() { return calificacion; }
    public int getId_categoria() { return id_categoria; }
    public String getCategoria() { return categoria; }

    public void setId(long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDirector(String director) { this.director = director; }
    public void setProductor(String productor) { this.productor = productor; }
    public void setEscritor(String escritor) { this.escritor = escritor; }
    public void setEstreno(int estreno) { this.estreno = estreno; }
    public void setCalificacion(double calificacion) { this.calificacion = calificacion; }
    public void setId_categoria(int id_categoria) { this.id_categoria = id_categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
}
