package com.example.jsp4.controler.categorias;

import com.example.jsp4.model.categoria.BeanCategoria;
import com.example.jsp4.model.service.ServiceCategoria;
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

@WebServlet(name = "ServletCategoria", urlPatterns = {
        "/get-categorias",
        "/add-categoria",
        "/create-categoria",
        "/update-categoria",
        "/get-categoria",
        "/save-categoria",
        "/delete-categoria"
})

public class ServletCategoria extends HttpServlet {
    Logger logger = Logger.getLogger("ServletCategoria");
    String action;
    String urlRedirect = "/get-categorias";
    ServiceCategoria serviceCategoria = new ServiceCategoria();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        action = request.getServletPath();
        logger.log(Level.INFO, "Path-> " + action);
        switch (action) {
            case "/get-categorias":
                List<BeanCategoria> categorias = serviceCategoria.getAll();
                System.out.println(categorias.size());
                request.setAttribute("categorias", categorias);
                urlRedirect = "/views/indexCategorias.jsp";
                break;
            case "/create-categoria":
                urlRedirect = "/views/createCategoria.jsp";
                break;
                
            case "/get-categoria":
                String id = request.getParameter("id");
                id = (id == null) ? "0" : id;
                try {
                    BeanCategoria categoria = serviceCategoria.categoria(Integer.parseInt(id));
                    request.setAttribute("categoria", categoria);
                    urlRedirect = "/views/updateCategoria.jsp";
                } catch (Exception e) {
                    urlRedirect = "/get-categorias";
                }
                break;
            case "/delete-categoria":
                String id3 = request.getParameter("id");
                ResultAction result3 = serviceCategoria.delete(id3);
                urlRedirect = "/get-categorias?result=" +
                        result3.isResult() + "&message=" + result3.getMessage() + "&status=" + result3.getStatus();
                break;

            default:
                request.setAttribute("categorias", serviceCategoria.getAll());
                urlRedirect = "/get-categorias";
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
            case "/add-categoria":
                System.out.println("Entra case");
                String nombre = request.getParameter("categoria");
                BeanCategoria categoria = new BeanCategoria();
                categoria.setNombre(nombre);
                ResultAction result = serviceCategoria.save(categoria);
                urlRedirect = "/get-categorias?result=" +
                        result.isResult() + "&message=" + result.getMessage() + "&status=" + result.getStatus();
                break;
            case "/save-categoria":
                String nombre2 = request.getParameter("categoria");
                String id = request.getParameter("id");
                BeanCategoria categoria2 = new BeanCategoria();
                categoria2.setId(Integer.parseInt(id));
                categoria2.setNombre(nombre2);
                ResultAction result2 = serviceCategoria.update(categoria2);
                urlRedirect = "/get-categorias?result=" +
                        result2.isResult() + "&message=" + result2.getMessage() + "&status=" + result2.getStatus();
                break;
            default:
                urlRedirect = "/get-categorias";
                break;
        }
        response.sendRedirect(request.getContextPath() + urlRedirect);
    }
}
