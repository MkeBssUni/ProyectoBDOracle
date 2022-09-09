<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actualización</title>
    <jsp:include page="../templates/head.jsp"/>
</head>
<body>
<jsp:include page="../templates/navbar.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="card mt-5">
                <div class="card-header">
                    ACTUALIZACION
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12">
                            <form class="needs-validation" novalidate action="save-pelicula" method="post">
                                <div class="form-group mb-3">
                                    <div class="row">
                                        <div class="col">
                                            <label class="fw-bold" for="pelicula">Título</label>
                                            <input name="titulo" id="pelicula"
                                                   required class="form-control" value="${pelicula.titulo}"/>
                                            <div class="invalid-feedback">
                                                Campo Obligatorio
                                            </div>
                                            <input type="hidden" name="id" value="${pelicula.id}">
                                        </div>
                                        <div class="col">
                                            <label class="fw-bold" for="director">Director</label>
                                            <input name="director" id="director"
                                                   required class="form-control" value="${pelicula.director}"/>
                                            <div class="invalid-feedback">
                                                Campo Obligatorio
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group mb-3">
                                    <div class="row">
                                        <div class="col">
                                            <label class="fw-bold" for="productor">Productor</label>
                                            <input name="productor" id="productor"
                                                   required class="form-control" value="${pelicula.productor}"/>
                                            <div class="invalid-feedback">
                                                Campo Obligatorio
                                            </div>
                                        </div>
                                        <div class="col">
                                            <label class="fw-bold" for="escritor">Escritor</label>
                                            <input name="escritor" id="escritor"
                                                   required class="form-control" value="${pelicula.escritor}"/>
                                            <div class="invalid-feedback">
                                                Campo Obligatorio
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group mb-3">
                                    <div class="row">
                                        <div class="col">
                                            <label class="fw-bold" for="estreno">Año de estreno</label>
                                            <input name="estreno" id="estreno"
                                                   required class="form-control" type="number" value="${pelicula.estreno}"/>
                                            <div class="invalid-feedback">
                                                Campo Obligatorio
                                            </div>
                                        </div>
                                        <div class="col">
                                            <label class="fw-bold" for="calificacion">Calificación</label>
                                            <input name="calificacion" id="calificacion"
                                                   required class="form-control" type="number" value="${pelicula.calificacion}"/>
                                            <div class="invalid-feedback">
                                                Campo Obligatorio
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group mb-3">
                                    <div class="row">
                                        <div class="col-12">
                                            <!-- Listar categorias de la BD en radios-->
                                            <label class="fw-bold" for="categoria">Selecciona una categoría</label>
                                            <c:forEach var="categoria" items="${categorias}">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="hidden" name="categoriaPelicula" value="${pelicula.id_categoria}"> 
                                                    <c:if test="${pelicula.id_categoria == categoria.id}">
                                                        <input class="form-check-input" type="radio" name="categoria" id="${categoria.id}" value="${categoria.id}" checked/>
                                                        <label class="form-check-label" for="${categoria.id}">${categoria.nombre}</label>
                                                    </c:if>
                                                    <c:if test="${pelicula.id_categoria != categoria.id}">
                                                        <input class="form-check-input" type="radio" name="categoria" id="${categoria.id}" value="${categoria.id}"/>
                                                        <label class="form-check-label" for="${categoria.id}">${categoria.nombre}</label>
                                                    </c:if>
                                                </div>
                                            </c:forEach>

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group mb-3">
                                    <div class="row">
                                        <div class="col-12 text-end">
                                            <button type="submit" class="btn btn-success btn-sm">Actualizar</button>
                                            <button type="button" class="btn btn-danger btn-sm">Cancelar</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    (function () {
        'use strict'
        var forms = document.querySelectorAll('.needs-validation')
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>
</body>
</html>
