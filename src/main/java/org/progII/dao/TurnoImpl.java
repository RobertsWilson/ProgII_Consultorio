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

public class TurnoImpl implements AdmConexion, DAO<Turno, Integer> {

    String SQL_INSERT = "INSERT INTO Turno (id, dia, hora, nroConsultorio, nroPaciente) VALUES (?, ?, ?, ?, ?)";

    String SQL_UPDATE = "UPDATE Turno SET dia = ?, hora = ?, nroConsultorio = ?, nroPaciente = ? WHERE id = ?";

    String SQL_DELETE = "DELETE FROM Turno WHERE nroPaciente = id";

    String SQL_GETBYID = "SELECT id, dia, hora, nroConsultorio, nroPaciente FROM Turno WHERE id = ?";

    String SQL_GETALL = "SELECT * FROM Turno ORDER BY dia ASC, hora ASC";

    String SQL_EXISTBYID = "SELECT * FROM Turno WHERE id = ?";


    @Override
    public List<Turno> getAll() {
        List<Turno> turnos = new ArrayList<>();
        try (Connection conn = ObtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_GETALL)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Turno turno = new Turno();
                turno.setId(rs.getInt("id"));
                turno.setDia(rs.getDate("dia"));
                turno.setHora(rs.getTime("hora"));
                turno.setNroConsultorio(rs.getInt("nroConsultorio"));
                turno.setNroPaciente(rs.getInt("nroPaciente"));
                turnos.add(turno);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return turnos;
    }

    @Override
    public void insert(Turno turno) {

        try (Connection conn = ObtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setDate(1, new java.sql.Date(turno.getDia().getTime()));
            ps.setTime(2, turno.getHora());
            ps.setInt(3, turno.getNroConsultorio());
            ps.setInt(4, turno.getNroPaciente());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public void update(Turno turno) {

        try (Connection conn = ObtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setDate(1, new java.sql.Date(turno.getDia().getTime()));
            ps.setTime(2, turno.getHora());
            ps.setInt(3, turno.getNroConsultorio());
            ps.setInt(4, turno.getNroPaciente());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void delete(Integer id) {

        try (Connection conn = ObtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public Turno getById(Integer id) {

        Turno turno = null;

        try (Connection conn = ObtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_GETBYID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                turno = new Turno();
                turno.setDia(rs.getDate("dia"));
                turno.setHora(rs.getTime("hora"));
                turno.setNroConsultorio(rs.getInt("nroConsultorio"));
                turno.setNroPaciente(rs.getInt("id"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return turno;
    }

    @Override
    public boolean existsById(Integer id) {

        try(Connection conn = ObtenerConexion();
        PreparedStatement ps = conn.prepareStatement(SQL_EXISTBYID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
