<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${pelicula.titulo}></title>
    <jsp:include page="../templates/head.jsp"/>
</head>
<body>
<jsp:include page="../templates/navbar.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="card mt-5">
                <div class="card-header">
                    DETALLES
                </div>
                <div class="card-body">
                    <table class="table table-sm table-hover">
                        <tbody>
                            <tr>
                                <td style="font-weight: bold">
                                    Título
                                </td>
                                <td>
                                    ${pelicula.titulo}
                                </td>
                            </tr>
                            <tr>
                                <td style="font-weight: bold">
                                    Director
                                </td>
                                <td>
                                    ${pelicula.director}
                                </td>
                            </tr>
                            <tr>
                                <td style="font-weight: bold">
                                    Productor
                                </td>
                                <td>
                                    ${pelicula.productor}
                                </td>
                            </tr>
                            <tr>
                                <td style="font-weight: bold">
                                    Escritor
                                </td>
                                <td>
                                    ${pelicula.escritor}
                                </td>
                            </tr>
                            <tr>
                                <td style="font-weight: bold">
                                    Año de Estreno
                                </td>
                                <td>
                                    ${pelicula.estreno}
                                </td>
                            </tr>
                            <tr>
                                <td style="font-weight: bold">
                                    Calificación
                                </td>
                                <td>
                                    ${pelicula.calificacion}
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
