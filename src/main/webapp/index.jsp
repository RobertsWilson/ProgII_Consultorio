<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.progII.entities.Turno" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Consultorio - Inicio</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<div class="container">
    <h1>Panel de Gestión de Turnos</h1>

    <%-- Mensajes de Retroalimentación --%>
    <% if (request.getParameter("mensaje") != null) { %>
        <div class="alert success">Operación realizada con éxito.</div>
    <% } %>
    <% if (request.getParameter("error") != null) { %>
        <div class="alert error">Ocurrió un error al procesar la solicitud.</div>
    <% } %>

    <%-- EJERCICIO 1: FORMULARIO PARA AGREGAR TURNO --%>
    <h3>1. Registrar Nuevo Turno</h3>
    <form action="AgregarTurnoServlet" method="POST" class="form-grid">
        <div class="form-group">
            <label>Fecha:</label>
            <input type="date" name="fecha" required>
        </div>
        <div class="form-group">
            <label>Hora:</label>
            <input type="time" name="hora" required>
        </div>
        <div class="form-group">
            <label>Consultorio #:</label>
            <input type="number" name="nroConsultorio" min="1" required>
        </div>
        <div class="form-group">
            <label>ID Paciente (Prueba):</label>
            <input type="number" name="nroPaciente" min="1" required>
        </div>
        <button type="submit">Confirmar Turno</button>
    </form>

    <%-- EJERCICIO 2: FORMULARIO PARA CANCELACIÓN POR PINTURA --%>
    <h3>2. Mantenimiento: Cancelar por Pintura</h3>
    <form action="CancelarTurnoServlet" method="POST" class="form-grid" style="background-color: #fbeee6;">
        <div class="form-group">
            <label>Consultorio afectado:</label>
            <input type="number" name="nroConsultorio" required>
        </div>
        <div class="form-group">
            <label>Fecha de Pintura:</label>
            <input type="date" name="fechaPintura" required>
        </div>
        <button type="submit" class="btn-danger">Ejecutar Cancelación Masiva</button>
    </form>

    <%-- EJERCICIO 3: LISTADO DE TURNOS --%>
    <h3>3. Turnos Programados</h3>
    <form action="ConsultarTurnosServlet" method="GET" style="margin-bottom: 15px;">
        <button type="submit" style="width: auto; padding: 8px 20px;">Actualizar Listado</button>
    </form>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Consultorio</th>
                <th>Paciente</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Turno> turnos = (List<Turno>) request.getAttribute("listaTurnos");
                if (turnos != null && !turnos.isEmpty()) {
                    for (Turno t : turnos) {
            %>
                <tr>
                    <td><%= t.getId() %></td>
                    <td><%= t.getDia() %></td>
                    <td><%= t.getHora() %></td>
                    <td><%= t.getNroConsultorio() %></td>
                    <td><%= t.getNroPaciente() %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="5" style="text-align: center;">No hay turnos registrados. Inicie la consulta.</td></tr>
            <% } %>
        </tbody>
    </table>
</div>

</body>
</html>