package org.progII.dao;

import org.progII.entities.Consultorio;
import org.progII.interfaces.AdmConexion;
import org.progII.interfaces.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultorioImpl implements AdmConexion, DAO<Consultorio, Integer> {

    String SQL_INSERT = "INSERT INTO consultorio (nroConsultorio, medico) VALUES (?, ?)";
    String SQL_UPDATE = "UPDATE consultorio SET medico = ? WHERE nroConsultorio = ?";
    String SQL_DELETE = "DELETE FROM consultorio WHERE nroConsultorio = ?";
    String SQL_GETBYID = "SELECT nroConsultorio, medico FROM consultorio WHERE nroConsultorio = ?";
    String SQL_GETALL = "SELECT * FROM consultorio ORDER BY medico ASC";
    String SQL_EXISTBYID = "SELECT * FROM consultorio WHERE nroConsultorio = ?";



    @Override
    public List<Consultorio> getAll() {
            List<Consultorio> consultorios = new ArrayList<>();

            try(Connection conn = ObtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_GETALL)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int nroConsultorio = rs.getInt("nroConsultorio");
                    String medico = rs.getString("medico");
                    Consultorio consultorio = new Consultorio(nroConsultorio, medico);
                    consultorios.add(consultorio);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return consultorios;
    }


    public void insert(Consultorio consultorio) {

        try(Connection conn = ObtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setInt(1, consultorio.getNroConsultorio());
            ps.setString(2, consultorio.getMedico());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void update(Consultorio consultorio) {

     try(Connection conn = ObtenerConexion();
         PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

         ps.setInt(1, consultorio.getNroConsultorio());
         ps.setString(2, consultorio.getMedico());
         ps.setInt(3, consultorio.getNroConsultorio());
         ps.executeUpdate();

     } catch (SQLException e) {
         throw new RuntimeException(e);
     }

    }

    @Override
    public void delete(Integer nroConsultorio) {

        try(Connection conn = ObtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, nroConsultorio);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Consultorio getById(Integer nroConsultorio) {

        Consultorio consultorio = null;

            try(Connection conn = ObtenerConexion();
                PreparedStatement ps = conn.prepareStatement(SQL_GETBYID)) {

                ps.setInt(1, nroConsultorio);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    consultorio = new Consultorio();
                    consultorio.setNroConsultorio(nroConsultorio);
                    consultorio.setMedico(rs.getString("medico"));

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return consultorio;
    }

    @Override
    public boolean existsById(Integer nroConsultorio) {
        try(Connection conn = ObtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_EXISTBYID)) {

            ps.setInt(1, nroConsultorio);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

