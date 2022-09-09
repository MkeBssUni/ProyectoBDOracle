package com.example.jsp4.controler.favorita;

import com.example.jsp4.model.categoria.BeanCategoria;
import com.example.jsp4.model.favorita.BeanFavorita;
import com.example.jsp4.model.favorita.*;
import com.example.jsp4.model.service.ServiceCategoria;
import com.example.jsp4.model.service.ServicePeliculaFav;
import com.example.jsp4.model.utils.ResultAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ServletFavorita", urlPatterns = {
        "/get-favoritas"
})

public class ServletFavorita extends HttpServlet {
    Logger logger = Logger.getLogger("ServletFavorita");
    String action;
    String urlRedirect = "/get-favoritas";
    ServicePeliculaFav servicePelicula = new ServicePeliculaFav();
    ServiceCategoria serviceCategoria = new ServiceCategoria();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        action = request.getServletPath();
        logger.log(Level.INFO, "Path-> " + action);
        switch (action) {
            case "/get-favoritas":
                List<BeanFavorita> peliculasFavoritas = servicePelicula.getAll();
                System.out.println(peliculasFavoritas.size());
                request.setAttribute("peliculas", peliculasFavoritas);
                urlRedirect = "views/favoritas.jsp";
                break;
        
            default:
                request.setAttribute("peliculasFavoritas", servicePelicula.getAll());
                urlRedirect = "/get-favoritas";
                break;
        }
        request.getRequestDispatcher(urlRedirect).forward(request, response);
    }

   /*  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        action = request.getServletPath();
        logger.log(Level.INFO, "Path-> " + action);
        switch (action) {
            case "/add-pelicula":
                System.out.println("Entra case");
                String titulo = request.getParameter("titulo");
                String director = request.getParameter("director");
                String productor = request.getParameter("productor");
                String escritor = request.getParameter("escritor");
                String estreno = request.getParameter("estreno");
                String calificacion = request.getParameter("calificacion");
                String idCategoria = request.getParameter("categoria");
                System.out.println(idCategoria);

                BeanFavorita pelicula = new BeanFavorita();
                pelicula.setTitulo(titulo);
                pelicula.setDirector(director);
                pelicula.setProductor(productor);
                pelicula.setEscritor(escritor);
                pelicula.setEstreno(Integer.parseInt(estreno));
                pelicula.setCalificacion(Double.parseDouble(calificacion));
                pelicula.setId_categoria(Integer.parseInt(idCategoria));
                ResultAction result = servicePelicula.save(pelicula);
                urlRedirect = "/get-peliculasFavoritas?result=" +
                        result.isResult() + "&message=" + result.getMessage() + "&status=" + result.getStatus();
                break;
            case "/save-pelicula":
                String titulo2 = request.getParameter("titulo");
                String director2 = request.getParameter("director");
                String productor2 = request.getParameter("productor");
                String escritor2 = request.getParameter("escritor");
                String estreno2 = request.getParameter("estreno");
                String calificacion2 = request.getParameter("calificacion");
                String idCategoria2 = request.getParameter("categoria");
                String id = request.getParameter("id");
                BeanFavorita pelicula2 = new BeanFavorita();
                pelicula2.setTitulo(titulo2);
                pelicula2.setDirector(director2);
                pelicula2.setProductor(productor2);
                pelicula2.setEscritor(escritor2);
                pelicula2.setEstreno(Integer.parseInt(estreno2));
                pelicula2.setCalificacion(Double.parseDouble(calificacion2));
                pelicula2.setId_categoria(Integer.parseInt(idCategoria2));
                pelicula2.setId(Long.parseLong(id));
                ResultAction result2 = servicePelicula.update(pelicula2);
                urlRedirect = "/get-peliculasFavoritas?result=" +
                        result2.isResult() + "&message=" + result2.getMessage() + "&status=" + result2.getStatus();
                break;
            default:
                urlRedirect = "/get-peliculasFavoritas";
                break;
        }
        response.sendRedirect(request.getContextPath() + urlRedirect);
    } */
}

