<%@ page import="com.example.demo.model.Events" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Eventos | StudentEventHub</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/events.css">
</head>
<body class="fondo-oscuro texto-claro">

<header class="events_header">
    <h1 class="events_brand">Manager De Eventos De Estudiantes</h1>
    <button class="btn btn-ghost" id="btn-logout-open">Cerrar sesion</button>
</header>

<main class="events_main">
    <section class="events_toolbar">
        <c:choose>
            <c:when test="${showPast}">
                <a class="btn" href="${pageContext.request.contextPath}/events">Ver eventos futuros</a>
            </c:when>
            <c:otherwise>
                <a class="btn" href="${pageContext.request.contextPath}/events?past=1">Ver eventos pasados</a>
            </c:otherwise>
        </c:choose>
        <button class="btn btn-primary" id="btn-add-open">Añadir evento</button>
    </section>

    <p class="events_error" id="error-global">
        ${error != null ? error : ""}
    </p>

    <section class="events_grid">
        <%
            List<Events> events = (List<Events>) request.getAttribute("events");
            if (events == null || events.isEmpty()) {
        %>
        <div class="events_empty">No hay eventos para mostrar.</div>
        <%
        } else {
            for (Events e : events) {
        %>
        <article class="event-card"
                 data-id="<%= e.getId() %>"
                 data-title="<%= e.getTitle() %>"
                 data-description="<%= e.getDescription() == null ? "" : e.getDescription() %>"
                 data-type="<%= e.getEventType() %>"
                 data-date="<%= e.getEventDate().toString() %>">
            <div class="event-card_header">
                <h3 class="event-card_title"><%= e.getTitle() %></h3>
                <span class="event-card_type"><%= e.getEventType() %></span>
            </div>
            <p class="event-card_desc"><%= e.getDescription() == null ? "" : e.getDescription() %></p>
            <div class="event-card_footer">
                <p class="event-card_when">
                    Cuando? <span class="when-label" data-date="<%= e.getEventDate().toString() %>"></span>
                    <span class="event-card_date">(<%= e.getEventDate() %>)</span>
                </p>
                <div class="event-card_actions">
                    <button class="btn btn-ghost btn-edit">Editar</button>
                    <button class="btn btn-danger btn-delete">Eliminar</button>
                </div>
            </div>
        </article>
        <%
                }
            }
        %>
    </section>
</main>

<!-- MODALES -->

<div class="modal oculto" id="modal-add">
    <div class="modal_dialog">
        <header class="modal_header">
            <h2>Añadir evento</h2>
            <button class="modal_close" data-close>×</button>
        </header>
        <form class="modal_body" action="${pageContext.request.contextPath}/events" method="POST" id="form-add">
            <input type="hidden" name="action" value="add">
            <div class="form_row">
                <label>Titulo</label>
                <input class="input" type="text" name="title" required>
            </div>
            <div class="form_row">
                <label>Descripcion</label>
                <textarea class="input" name="description" rows="3"></textarea>
            </div>
            <div class="form_row">
                <label>Fecha</label>
                <input class="input" type="date" name="eventDate" required>
            </div>
            <div class="form_row">
                <label>Tipo</label>
                <select class="input" name="eventType" required>
                    <option value="parcial">Parcial</option>
                    <option value="entrega">Entrega</option>
                    <option value="otro">Otro</option>
                </select>
            </div>
            <footer class="modal_footer">
                <button type="button" class="btn" data-close>Cancelar</button>
                <button type="submit" class="btn btn-primary">Crear</button>
            </footer>
        </form>
    </div>
</div>

<!-- Modal Editar -->
<div class="modal oculto" id="modal-edit">
    <div class="modal_dialog">
        <header class="modal_header">
            <h2>Editar evento</h2>
            <button class="modal_close" data-close>×</button>
        </header>
        <form class="modal_body" action="${pageContext.request.contextPath}/events" method="POST" id="form-edit">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="id" id="edit-id">
            <div class="form_row">
                <label>Título</label>
                <input class="input" type="text" name="title" id="edit-title" required>
            </div>
            <div class="form_row">
                <label>Descripcion</label>
                <textarea class="input" name="description" id="edit-description" rows="3"></textarea>
            </div>
            <div class="form_row">
                <label>Fecha</label>
                <input class="input" type="date" name="eventDate" id="edit-date" required>
            </div>
            <div class="form_row">
                <label>Tipo</label>
                <select class="input" name="eventType" id="edit-type" required>
                    <option value="parcial">Parcial</option>
                    <option value="entrega">Entrega</option>
                    <option value="otro">Otro</option>
                </select>
            </div>
            <footer class="modal_footer">
                <button type="button" class="btn" data-close>Cancelar</button>
                <button type="submit" class="btn btn-primary">Guardar</button>
            </footer>
        </form>
    </div>
</div>

<!-- Modal Eliminar  -->
<div class="modal oculto" id="modal-delete">
    <div class="modal_dialog">
        <header class="modal_header">
            <h2>Eliminar evento</h2>
            <button class="modal_close" data-close>×</button>
        </header>
        <form class="modal_body" action="${pageContext.request.contextPath}/events" method="POST" id="form-delete">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="id" id="delete-id">
            <p>¿Estas seguro que deseas eliminar el evento?</p>
            <footer class="modal_footer">
                <button type="button" class="btn" data-close>Cancelar</button>
                <button type="submit" class="btn btn-danger">Eliminar</button>
            </footer>
        </form>
    </div>
</div>

<!-- Modal Cerrar sesion -->
<div class="modal oculto" id="modal-logout">
    <div class="modal_dialog">
        <header class="modal_header">
            <h2>Cerrar sesion</h2>
            <button class="modal_close" data-close>×</button>
        </header>
        <form class="modal_body" action="${pageContext.request.contextPath}/logout" method="POST" id="form-logout">
            <p>¿Estas seguro que deseas cerrar sesion?</p>
            <footer class="modal_footer">
                <button type="button" class="btn" data-close>Cancelar</button>
                <button type="submit" class="btn btn-danger">Cerrar sesión</button>
            </footer>
        </form>
    </div>
</div>

<script>
    const contextPath = '<%= request.getContextPath() %>';
    const showPast = '<%= request.getAttribute("showPast") %>' === 'true';
</script>
<script src="${pageContext.request.contextPath}/assets/js/events.js" defer></script>
</body>
</html>