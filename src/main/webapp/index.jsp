<%-- 
    Document   : index
    Created on : 29 dic. 2021, 23:54:21
    Author     : Bryan Hdz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LogIn</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            .gradient-custom-2 {
                /* fallback for old browsers */
                background: #fccb90;

                /* Chrome 10-25, Safari 5.1-6 */
                background: -webkit-linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);

                /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
                background: linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
            }

            @media (min-width: 768px) {
                .gradient-form {
                    height: 100vh !important;
                }
            }
            @media (min-width: 769px) {
                .gradient-custom-2 {
                    border-top-right-radius: .3rem;
                    border-bottom-right-radius: .3rem;
                }
            }
        </style>
    </head>
    <body>
        <section class="h-100 gradient-form" style="background-color: #eee;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-xl-10">
                        <div class="card rounded-3 text-black">
                            <div class="row g-0">
                                <div class="col-lg-6">
                                    <div class="card-body p-md-5 mx-md-4">

                                        <div class="text-center">
                                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/lotus.webp" style="width: 185px;" alt="logo">
                                            <h4 class="mt-1 mb-5 pb-1">Somos el equipo Lotus</h4>
                                        </div>

                                        <s:form action="verifyLogin" class="d-flex flex-column">
                                                <p>Por favor inicie sesi??n con su cuenta</p>
                                                <s:textfield name="correo" placeholder="Direcci??n de correo electr??nico"/>
                                                <label class="form-label" for="form2Example11">Usuario</label>
                                                <s:password name="digesto"/>
                                                <label class="form-label" for="form2Example22">Contrase??a</label>
                                                <s:submit class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3"> Iniciar Sesi??n</s:submit>
                                        </s:form>

                                        <a class="text-muted" href="#!">??Olvid?? la contrase??a?</a>
                                        <p class="mb-0 me-2">??No tiene una cuenta?</p>
                                        <button type="button" class="btn btn-outline-danger">Registrarse</button>

                                    </div>
                                </div>
                                <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
                                    <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                                        <h4 class="mb-4">Somos m??s que una compa????a</h4>
                                        <p class="small mb-0">
                                            Con Lotus puedes hacer tus pedidos de comida con la seguridad que nadie m??s te ofrece.
                                            Apoyando a nuestros repartidores con cada pedido que hagas y a los negocios locales.
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
