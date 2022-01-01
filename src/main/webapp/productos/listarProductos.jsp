<%-- 
    Document   : listarProductos
    Created on : 30 dic. 2021, 00:30:28
    Author     : Bryan Hdz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
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
                                <li><a class="dropdown-item" href="Stats">Estadísticas</a></li>
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

        <div class="container">
            <div class="card text-black my-3">
                <div class="card-header text-center">
                    <h1>Productos Disponibles</h1>
                </div>
                <div class="card-body table-responsive">
                    <h4 class="card-title">
                    </h4>
                    <div class="mb-3 d-flex flex-wrap justify-content-center"/>
                    <s:iterator value="productos">
                        <div class="card d-flex mx-2" style="width: 18rem;">
                            <img src="https://cdn4.iconfinder.com/data/icons/web-pack/64/reloading-512.png" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title"><s:property value="nombre"/></h5>
                                <p class="card-text"><s:property value="descripcion"/></p>
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Precio: $<s:property value="precio"/></li>
                                <li class="list-group-item">Cantidad Disponible: <s:property value="stock"/></li>
                                <li class="list-group-item">Restaurant: <s:property value="fk_restaurant.razon_social"/></li>
                            </ul>
                            <div class="card-body">
                                <s:url action="AgregarProducto" var="idProdTag">
                                    <s:param name="product.idproducto" value="%{idproducto}"/>
                                </s:url>
                                <s:a href="%{idProdTag}" class="btn btn-outline-success">
                                    Añadir al carrito
                                </s:a>
                            </div>
                        </div>
                    </s:iterator>

                </div>
            </div> 
        </div>
    </body>
</html>
