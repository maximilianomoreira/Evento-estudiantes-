<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Evento Estudiantes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>

<main class="login">
    <section class="login_card">
        <h1 class="login_title">Manager De Eventos De Estudiantes</h1>
        <form class="login_form" id="form-login" action="${pageContext.request.contextPath}/login" method="POST" >
            <div class="login_field">
                <label class="login_label" for="email">Email</label>
                <input class="login_input" type="text" id="email" name="email" placeholder="tu@correo.com" >
            </div>

            <div class="login_field">
                <label class="login_label" for="password">Contraseña</label>
                <input class="login_input" type="password" id="password" name="password" placeholder="********" minlength="3">
            </div>

            <p class="login_error" id="error-global">
                ${error != null ? error : ""}
            </p>

            <button type="submit" class="boton-primario">Ingresar</button>

            <div class="login_register">
                <p class="login_p-cuenta">¿No tenés cuenta?</p>
                <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/pages/register.jsp'">Crear cuenta</button>
            </div>
        </form>
    </section>
</main>

<script src="${pageContext.request.contextPath}/assets/js/login.js" defer></script>
</body>
</html>