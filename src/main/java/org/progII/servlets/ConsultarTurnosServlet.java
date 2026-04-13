package org.progII.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.progII.dao.TurnoImpl;
import org.progII.entities.Turno;

import java.io.IOException;
import java.util.List;

@WebServlet("/ConsultarTurnosServlet")
public class ConsultarTurnosServlet extends HttpServlet {

    private TurnoImpl turnoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.turnoDAO = new TurnoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nroStr = request.getParameter("nroConsultorio");
        int nroConsultorio = (nroStr != null) ? Integer.parseInt(nroStr) : 1;

        //Buscamos los turnos en la base de datos
        //getAll()
        List<Turno> listaTurnos = turnoDAO.getAll();

        //Guardamos la lista en el 'request'
        request.setAttribute("listaTurnos", listaTurnos);
        request.setAttribute("consultorioActual", nroConsultorio);


        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}