<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 30/10/2025
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Evento Estudiantes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>

<main class="login">
    <section class="login_card">
        <h1 class="login_title">Crear Cuenta</h1>

        <form class="login_form" id="form-register" action="${pageContext.request.contextPath}/register" method="POST">
            <div class="login_field">
                <label class="login_label" for="name">Nombre</label>
                <input class="login_input" type="text" id="name" name="name" placeholder="Ej: Maximiliano Moreira">
            </div>

            <div class="login_field">
                <label class="login_label" for="email">Email</label>
                <input class="login_input" type="text" id="email" name="email" placeholder="tu@correo.com">
            </div>

            <div class="login_field">
                <label class="login_label" for="password">Contraseña</label>
                <input class="login_input" type="password" id="password" name="password" placeholder="********">
            </div>

            <div class="login_field">
                <label class="login_label" for="confirmPassword">Verificar Contraseña</label>
                <input class="login_input" type="password" id="confirmPassword" name="confirmPassword" placeholder="********">
            </div>

            <p class="login_error" id="error-global">
                ${error != null ? error : ""}
            </p>

            <button type="submit" class="boton-primario">Crear cuenta</button>

            <div class="login_register">
                <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/pages/login.jsp'">Volver</button>
            </div>
        </form>
    </section>
</main>

<script src="${pageContext.request.contextPath}/assets/js/register.js" defer></script>
</body>
</html>
