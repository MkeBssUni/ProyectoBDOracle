package com.example.jsp4.controler.peliculas;

import com.example.jsp4.model.categoria.BeanCategoria;
import com.example.jsp4.model.pelicula.BeanPelicula;
import com.example.jsp4.model.service.ServiceCategoria;
import com.example.jsp4.model.service.ServicePelicula;
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

@WebServlet(name = "ServletPelicula", urlPatterns = {
        "/get-peliculas",
        "/add-pelicula",
        "/create-pelicula",
        "/update-pelicula",
        "/get-pelicula",
        "/save-pelicula",
        "/get-detalles",
        "/delete-pelicula"
})

public class ServletPelicula extends HttpServlet {
    Logger logger = Logger.getLogger("ServletPelicula");
    String action;
    String urlRedirect = "/get-peliculas";
    ServicePelicula servicePelicula = new ServicePelicula();
    ServiceCategoria serviceCategoria = new ServiceCategoria();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        action = request.getServletPath();
        logger.log(Level.INFO, "Path-> " + action);
        switch (action) {
            case "/get-peliculas":
                List<BeanPelicula> peliculas = servicePelicula.getAll();
                System.out.println(peliculas.size());
                request.setAttribute("peliculas", peliculas);
                urlRedirect = "/views/index.jsp";
                break;
            case "/create-pelicula":
                List<BeanCategoria> categorias= serviceCategoria.getAll();
                request.setAttribute("categorias", categorias);
                urlRedirect = "/views/create.jsp";
                break;
            case "/get-pelicula":
                List<BeanCategoria> categorias2= serviceCategoria.getAll();
                request.setAttribute("categorias", categorias2);
                String id = request.getParameter("id");
                id = (id == null) ? "0" : id;
                try {
                    BeanPelicula pelicula = servicePelicula.getPelicula(Long.parseLong(id));
                    request.setAttribute("pelicula", pelicula);
                    urlRedirect = "/views/update.jsp";
                } catch (Exception e) {
                    urlRedirect = "/get-peliculas";
                }
                break;
            case "/get-detalles":
                String id2 = request.getParameter("id");
                id2 = (id2 == null) ? "0" : id2;
                try {
                    BeanPelicula pelicula = servicePelicula.getPelicula(Long.parseLong(id2));
                    request.setAttribute("pelicula", pelicula);
                    urlRedirect = "/views/detalles.jsp";
                } catch (Exception e) {
                    urlRedirect = "/get-peliculas";
                }
                break;
            case "/delete-pelicula":
                String id3 = request.getParameter("id");
                ResultAction result3 = servicePelicula.delete(id3);
                urlRedirect = "/get-peliculas?result=" +
                        result3.isResult() + "&message=" + result3.getMessage() + "&status=" + result3.getStatus();
                break;

            default:
                request.setAttribute("peliculas", servicePelicula.getAll());
                urlRedirect = "/get-peliculas";
                break;
        }
        request.getRequestDispatcher(urlRedirect).forward(request, response);
    }

    @Override
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

                BeanPelicula pelicula = new BeanPelicula();
                pelicula.setTitulo(titulo);
                pelicula.setDirector(director);
                pelicula.setProductor(productor);
                pelicula.setEscritor(escritor);
                pelicula.setEstreno(Integer.parseInt(estreno));
                pelicula.setCalificacion(Double.parseDouble(calificacion));
                pelicula.setId_categoria(Integer.parseInt(idCategoria));
                ResultAction result = servicePelicula.save(pelicula);
                urlRedirect = "/get-peliculas?result=" +
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
                BeanPelicula pelicula2 = new BeanPelicula();
                pelicula2.setTitulo(titulo2);
                pelicula2.setDirector(director2);
                pelicula2.setProductor(productor2);
                pelicula2.setEscritor(escritor2);
                pelicula2.setEstreno(Integer.parseInt(estreno2));
                pelicula2.setCalificacion(Double.parseDouble(calificacion2));
                pelicula2.setId_categoria(Integer.parseInt(idCategoria2));
                pelicula2.setId(Long.parseLong(id));
                ResultAction result2 = servicePelicula.update(pelicula2);
                urlRedirect = "/get-peliculas?result=" +
                        result2.isResult() + "&message=" + result2.getMessage() + "&status=" + result2.getStatus();
                break;
            default:
                urlRedirect = "/get-peliculas";
                break;
        }
        response.sendRedirect(request.getContextPath() + urlRedirect);
    }
}
