package com.example.jsp4.controler.Wl;

import com.example.jsp4.model.categoria.BeanCategoria;
import com.example.jsp4.model.wl.*;
import com.example.jsp4.model.utils.Conexion;
import com.example.jsp4.model.service.ServicePelicula;
import com.example.jsp4.model.service.ServiceWl;
import com.example.jsp4.model.utils.ResultAction;
import com.example.jsp4.model.wl.BeanWl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ServletWl", urlPatterns = {
        "/get-wls",
        "/add-wl",
        "/create-wl",
        "/get-wl",
        "/save-wl"
})

public class ServletWl extends HttpServlet {
    Logger logger = Logger.getLogger("ServletWl");
    String action;
    String urlRedirect = "/get-wls";
    ServicePelicula servicePelicula = new ServicePelicula();
    ServiceWl serviceWl = new ServiceWl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        action = request.getServletPath();
        logger.log(Level.INFO, "Path-> " + action);
        switch (action) {
            case "/get-wls":
                List<BeanWl> peliculas =serviceWl.getAll();
                System.out.println(peliculas.size());
                request.setAttribute("peliculas", peliculas);
                urlRedirect = "/views/wl.jsp";
                break;
            case "/create-wl":
                List<BeanWl> wls= serviceWl.getAll();
                request.setAttribute("peliculas", wls);

                urlRedirect = "/views/createwl.jsp";
                break;
            default:
                request.setAttribute("peliculas", servicePelicula.getAll());
                urlRedirect = "/get-wls";
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
            case "/add-wl":
                System.out.println("Entra case");
                String titulo = request.getParameter("titulo");

                BeanWl pelicula = new BeanWl();
                pelicula.setTitulo(titulo);
                ResultAction result = serviceWl.save(titulo);
                urlRedirect = "/get-wls?result=" +
                        result.isResult() + "&message=" + result.getMessage() + "&status=" + result.getStatus();
                break;
            default:
                urlRedirect = "/get-wls";
                break;
        }
        response.sendRedirect(request.getContextPath() + urlRedirect);
    }
}
