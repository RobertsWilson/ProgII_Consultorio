<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.progII.dao.ConsultorioImpl" %>
<%@ page import="org.progII.entities.Turno" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Consultorio</title>
    <style>
        body { font-family: Arial; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .form-section { background: #f9f9f9; padding: 15px; border-radius: 5px; margin-bottom: 20px; border: 1px solid #ccc; }
        .btn-cancelar { background-color: #ff4d4d; color: white; border: none; padding: 5px 10px; cursor: pointer; }
    </style>
</head>
<body>

    <h1>Sistema de Turnos - Consultorio</h1>

    <%-- Mensajes de confirmación --%>
    <% if(request.getParameter("msj") != null) { %>
        <div style="color: green; font-weight: bold;"><%= request.getParameter("msj") %></div>
    <% } %>

    <%-- SECCIÓN 1: AGREGAR TURNO (Ejercicio 1) --%>
    <div class="form-section">
        <h3>Registrar Nuevo Turno</h3>
        <form action="AltaTurnoServlet" method="POST">
            Fecha: <input type="date" name="fecha" required>
            Hora: <input type="time" name="hora" required>
            Consultorio #: <input type="number" name="nroConsultorio" required>
            Paciente #: <input type="number" name="nroPaciente" required>
            <button type="submit">Guardar Turno</button>
        </form>
    </div>

    <%-- SECCIÓN 2: CANCELAR POR PINTURA (Ejercicio 2) --%>
    <div class="form-section" style="border-color: #ff4d4d;">
        <h3>Mantenimiento: Cancelar por Pintura</h3>
        <form action="CancelarPinturaServlet" method="POST">
            Consultorio #: <input type="number" name="nroConsultorio" required>
            Fecha de Pintura: <input type="date" name="fechaPintura" required>
            <button type="submit" class="btn-cancelar">Ejecutar Cancelación Masiva</button>
        </form>
    </div>

    <%-- SECCIÓN 3: CONSULTAR TURNOS (Ejercicio 3) --%>
    <h3>Listado de Turnos Actuales</h3>
    <table>
        <thead>
            <tr>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Consultorio</th>
                <th>ID Paciente</th>
            </tr>
        </thead>
        <tbody>
            <%
                ConsultorioImpl dao = new ConsultorioImpl();
                List<Turno> lista = dao.listarTurnos(1);
                for(Turno t : lista) {
            %>
            <tr>
                <td><%= t.getDia() %></td>
                <td><%= t.getHora() %></td>
                <td><%= t.getNroConsultorio() %></td>
                <td><%= t.getNroPaciente() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>

</body>
</html>