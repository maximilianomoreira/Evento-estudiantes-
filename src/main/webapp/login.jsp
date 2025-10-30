<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<section class="principal">

    <h2 class="login_title">
        Gestor de Eventos
    </h2>

    <form class="login_form">
        <div class="login_field">
            <p class="login_field_text">Usuario</p>
            <input class="login_field_input" type="text" name="user" id="user" placeholder="Nombre de Usuario">
        </div>
        <div class="login_field">
            <p class="login_field_text">Contraseña</p>
            <input class="login_field_input" type="password" name="password" id="password" placeholder="Contraseña">
        </div>
        <p class="login_error"></p>
        <div class="login_action">
            <button type="submit" class="login_button">Ingresar</button>
        </div>
        <h2>¿No tenes cuenta?</h2>
        <div class="login_action">
            <button type="button" class="login_button">Crear cuenta</button>
        </div>
    </form>
    <a href="hello-servlet">Hello Servlet</a>



</section>


<script></script>
</body>
</html>