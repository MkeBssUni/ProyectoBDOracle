<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <%@ page pageEncoding="UTF-8" %>
                <html>

                <head>
                    <title>Películas</title>
                    <jsp:include page="../templates/head.jsp" />
                </head>

                <body>
                    <jsp:include page="../templates/navbar.jsp" />
                    <div class="container">
                        <div class="row">
                            <div class="col-12">
                                <div class="card mt-5">
                                    <div class="card-header">
                                        <div class="row">
                                            <div class="col-12">PELÍCULAS</div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <table class="table table-sm table-hover">
                                            <thead>
                                                <th>#</th>
                                                <th>Película</th>
                                                <th>Categoría</th>
                                                <th>Acciones</th>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="pelicula" items="${peliculas}" varStatus="status">
                                                    <tr>
                                                        <td>
                                                            <c:out value="${status.count}"></c:out>
                                                        </td>
                                                        <td>
                                                            <c:out value="${pelicula.titulo}"></c:out>
                                                        </td>
                                                        <td>
                                                            <c:out value="${pelicula.categoria}"></c:out>
                                                        </td>
                                                        <td>
                                                            <a href="get-detalles?id=${pelicula.id}"
                                                                class="btn btn-info btn-sm">
                                                                DETALLES
                                                            </a>
                                                            <a href="get-pelicula?id=${pelicula.id}"
                                                                class="btn btn-warning btn-sm">
                                                                EDITAR
                                                            </a>
                                                            <a href="delete-pelicula?id=${pelicula.id}"
                                                                class="btn btn-danger btn-sm">
                                                                ELIMINAR
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="d-grid gap-2 col-3 mx-auto" style="margin-top: 40px; margin-bottom: 40px">
                                <a href="create-pelicula" class="btn btn-success">
                                    REGISTRAR PELÍCULA
                                </a>
                                <a href="create-wl" class="btn btn-success">
                                    AGREGAR PELÍCULA A WATCH LIST
                                </a>
                            </div>
                        </div>
                    </div>
                </body>

                </html>