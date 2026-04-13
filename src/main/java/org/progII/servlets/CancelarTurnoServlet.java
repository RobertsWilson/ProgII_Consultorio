package org.progII.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.progII.dao.TurnoImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/CancelarTurnoServlet")
public class CancelarTurnoServlet extends HttpServlet {

    private TurnoImpl turnoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.turnoDAO = new TurnoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            int nroConsultorio = Integer.parseInt(request.getParameter("nroConsultorio"));
            String fechaStr = request.getParameter("fechaPintura");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaStr);

            turnoDAO.cancelarPorPintura(nroConsultorio, fecha);

            response.sendRedirect(request.getContextPath() + "/ConsultarTurnosServlet?mensaje=turno_agregado");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/PerfilServlet?error=2");
        }
    }
}