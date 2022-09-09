<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Categorías</title>
    <jsp:include page="../templates/head.jsp"/>
</head>
<body>
<jsp:include page="../templates/navbar.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="card mt-5">
                <div class="card-header">
                    <div class="row">
                        <div class="col-12">Categorías</div>
                    </div>
                </div>
                <div class="card-body">
                    <table class="table table-sm table-hover">
                        <thead>
                        <th>#</th>
                        <th>Categorías</th>
                        <th>Acciones</th>
                        </thead>
                        <tbody>
                            <c:forEach var="categoria" items="${categorias}" varStatus="status">
                                <tr>
                                    <td>
                                        <c:out value="${status.count}"></c:out>
                                    </td>
                                    <td>
                                <c:out value="${categoria.nombre}"></c:out>
                                    </td>
                                    <td>
                                        <a href="get-categoria?id=${categoria.id}" class="btn btn-warning btn-sm">
                                            EDITAR
                                        </a>
                                        <a href="delete-categoria?id=${categoria.id}" class="btn btn-danger btn-sm">
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
            <a href="create-categoria" class="btn btn-success">
                REGISTRAR CATEGORÍA
            </a>
        </div>
    </div>
</div>
</body>
</html>
