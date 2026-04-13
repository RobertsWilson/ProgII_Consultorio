package org.progII.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.progII.dao.TurnoImpl;
import org.progII.entities.Turno;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/AgregarTurnoServlet")
public class AgregarTurnoServlet extends HttpServlet {

    private TurnoImpl turnoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.turnoDAO = new TurnoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            int nroPaciente = Integer.parseInt(request.getParameter("nroPaciente"));
            String fechaStr = request.getParameter("fecha");
            String horaStr = request.getParameter("hora");
            int nroConsultorio = Integer.parseInt(request.getParameter("nroConsultorio"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaStr);

            Time hora = Time.valueOf(horaStr + ":00");

            Turno nuevoTurno = new Turno(fecha, hora, nroConsultorio, nroPaciente);

            turnoDAO.insert(nuevoTurno);

            response.sendRedirect(request.getContextPath() + "/ConsultarTurnosServlet?mensaje=turno_agregado");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=1");
        }
    }
}