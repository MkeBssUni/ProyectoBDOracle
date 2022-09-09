<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <html>

            <head>
                <title>Actualización</title>
                <jsp:include page="../templates/head.jsp" />
            </head>

            <body>
                <jsp:include page="../templates/navbar.jsp" />
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
                                            <form class="needs-validation" novalidate action="save-categoria"
                                                method="post">
                                                <div class="form-group mb-3">
                                                    <div class="row">
                                                        <div class="col">
                                                            <label class="fw-bold" for="categoria">Nombre</label>
                                                            <input name="categoria" id="categoria" required
                                                                class="form-control" value="${categoria.nombre}" />
                                                            <div class="invalid-feedback">
                                                                Campo Obligatorio
                                                            </div>
                                                            <input type="hidden" name="id" value="${categoria.id}">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group mb-3">
                                                    <div class="row">
                                                        <div class="col-12 text-end">
                                                            <button type="submit"
                                                                class="btn btn-success btn-sm">Actualizar</button>
                                                            <button type="button"
                                                                class="btn btn-danger btn-sm">Cancelar</button>
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