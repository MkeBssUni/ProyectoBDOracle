<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <%@ page pageEncoding="UTF-8" %>
                <html>

                <head>
                    <title>WatchList</title>
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
                                            <div class="col-12">WatchList</div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <table class="table table-sm table-hover">
                                            <thead>
                                                <th>#</th>
                                                <th>Película</th>
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
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </body>

                </html>