package org.progII.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.progII.dao.ConsultorioImpl;
import org.progII.entities.Turno;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet ("/AltaTurnoServlet")
public class AltaTurnoServlet extends HttpServlet {

    private ConsultorioImpl consultorioDao = new ConsultorioImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String fechaStr = request.getParameter("fecha");
            String horaStr = request.getParameter("hora") + ":00";
            int nroConsultorio = Integer.parseInt(request.getParameter("nroConsultorio"));
            int nroPaciente = Integer.parseInt(request.getParameter("nroPaciente"));


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaStr);
            Time hora = Time.valueOf(horaStr);


            Turno nuevoTurno = new Turno(fecha, hora, nroConsultorio, nroPaciente);


            consultorioDao.insert(nuevoTurno);

            response.sendRedirect("index.jsp?msj=Turno Agregado");

        } catch (ParseException | IllegalArgumentException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Datos invalidos");
        }
    }
}