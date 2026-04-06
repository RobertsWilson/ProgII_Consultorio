package org.progII.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.progII.dao.ConsultorioImpl;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet ("/CancelarPinturaServlet")
public class CancelarPinturaServlet extends HttpServlet {

    private ConsultorioImpl consultorioDao = new ConsultorioImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int nroConsultorio = Integer.parseInt(request.getParameter("nroConsultorio"));
            String fechaStr = request.getParameter("fechaPintura");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaStr);

            consultorioDao.cancelarPorPintura(nroConsultorio, fecha);

            response.sendRedirect("index.jsp?msj=Turnos cancelados por pintura");

        } catch (ParseException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Fecha invalida");
        }
    }
}
