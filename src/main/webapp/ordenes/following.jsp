<%--
    Document   : following
    Created on : 30 dic. 2021, 21:45:54
    Author     : Bryan Hdz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seguimiento</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Lotus Proyect</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarScroll">
                    <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="Inicio">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Productos">Productos</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Mis pedidos
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                                <li><a class="dropdown-item" href="Follow">¡Sigue tu orden!</a></li>
                                <li><a class="dropdown-item" href="viewShortReport">Estadísticas</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="Account">Cuenta</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Reviews">Reseñas</a>
                        </li>
                    </ul>
                    <form class="d-flex">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="card bg-dark m-3 justify-content-center">
            <div class="card-header text-white">
                Detalles de la orden
            </div>
            <div class="card-body mx-1">
                <div class="row m-1">
                    <div class="col-sm-3">
                        <div class="my-2">
                            <div class="card">
                                <img src="https://image.flaticon.com/icons/png/512/107/107831.png" class="card-img-top p-2" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title">Carrito de Compras</h5>
                                    <p class="card-text">
                                        <b>Nombre Cliente: </b>
                                        <s:property value="dtoClient.entidad.nombre"/> 
                                        <s:property value="dtoClient.entidad.apellido"/> 
                                        <s:property value="dtoClient.entidad.amaterno"/>
                                    </p>
                                    <p class="card-text">
                                        <b>ID Cliente: </b>
                                        <s:property value="dtoClient.entidad.idclient"/>
                                    </p>
                                    <p class="card-text">
                                        <b>Dirección Cliente: </b>
                                        <s:property value="dtoClientAdd.entidad.fk_address.calle"/>
                                        #<s:property value="dtoClientAdd.entidad.fk_address.numero"/>
                                        , <s:property value="dtoClientAdd.entidad.fk_address.cp"/>
                                        , <s:property value="dtoClientAdd.entidad.fk_address.colonia"/>
                                        . <s:property value="dtoClientAdd.entidad.fk_address.municipio"/>
                                        , <s:property value="dtoClientAdd.entidad.fk_address.ciudad"/>
                                    </p>
                                    <a href="#" class="btn btn-primary">Vaciar Carrito</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-9 my-2">
                        <table class="table table-dark table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Nombre del Producto</th>
                                    <th>Precio</th>
                                    <th>Restaurant</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="carrito" status="stat" var="env">
                                    <tr>
                                        <td><s:property value="%{#stat.index}"/></td>
                                        <td><s:property value="entidad.fk_product.nombre"/></td>
                                        <td><s:property value="entidad.fk_product.precio"/></td>
                                        <td><s:property value="entidad.fk_product.fk_restaurant.razon_social"/></td>
                                        <td>
                                            <s:url action="eliminarProducto" var="dto">
                                                <s:param name="order_prod.idorderprod" value="%{entidad.idorderprod}"/>
                                            </s:url>
                                            <s:a href="%{dto}" class="btn btn-outline-danger">
                                                Eliminar
                                            </s:a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="card-footer text-muted justify-content-end d-flex">
                <a type="button" class="btn btn-outline-light" href="RealizarPedido">Finalizar Pedido</a>
            </div>
        </div>
    </body>
</html>
