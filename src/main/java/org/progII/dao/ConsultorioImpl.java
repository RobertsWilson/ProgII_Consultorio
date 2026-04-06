package org.progII.dao;

import org.progII.entities.Turno;
import org.progII.interfaces.AdmConexion;
import org.progII.interfaces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ConsultorioImpl implements AdmConexion, DAO<Turno, Integer> {

    private Connection conn = null;

    private static final String SQL_INSERT = "INSERT INTO turnos (dia, hora, nroConsultorio, nroPaciente) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM turnos WHERE nroConsultorio = ?";
    private static final String SQL_DELETE_PINTURA = "DELETE FROM turnos WHERE nroConsultorio = ? AND dia = ?";

    @Override
    public void insert(Turno objeto) {
        try {
            conn = AdmConexion.ObtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT);

            ps.setDate(1, new java.sql.Date(objeto.getDia().getTime()));
            ps.setTime(2, objeto.getHora());
            ps.setInt(3, objeto.getNroConsultorio());
            ps.setInt(4, objeto.getNroPaciente());

            ps.executeUpdate();
            System.out.println("Turno insertado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelarPorPintura(Integer nroConsultorio, Date fecha) {
        try {
            conn = AdmConexion.ObtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_PINTURA);

            ps.setInt(1, nroConsultorio);
            ps.setDate(2, new java.sql.Date(fecha.getTime()));

            int filas = ps.executeUpdate();
            System.out.println("Turnos cancelados por pintura: " + filas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Turno> getAll() {
        return new ArrayList<>();
    }

    @Override
    public List<Turno> listarTurnos(int nroConsultorio) {
        List<Turno> lista = new ArrayList<>();
        String sql = "SELECT * FROM turnos WHERE nroConsultorio = ?";

        try (Connection conn = AdmConexion.ObtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, nroConsultorio);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Turno t = new Turno(
                        rs.getDate("dia"),
                        rs.getTime("hora"),
                        rs.getInt("nroConsultorio"),
                        rs.getInt("nroPaciente")
                );
                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void update(Turno objeto) {
        // No solicitado explícitamente en la actividad actual
    }

    @Override
    public void delete(Integer id) {
        // Implementación de borrado simple por ID
    }

    @Override
    public Turno getById(Integer id) {
        return null;
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }
}