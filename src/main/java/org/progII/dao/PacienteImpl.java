package org.progII.dao;

import org.progII.entities.Paciente;
import org.progII.interfaces.AdmConexion;
import org.progII.interfaces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteImpl implements AdmConexion, DAO<Paciente, Integer> {

    String SQL_INSERT = "INSERT INTO Paciente (nombre, telefono) VALUES (?, ?)";

    String SQL_UPDATE = "UPDATE Paciente SET nombre = ?, telefono = ? WHERE nroPaciente = ?";

    String SQL_DELETE = "DELETE FROM Paciente WHERE nroPaciente = ?";

    String SQL_GETBYID = "SELECT nroPaciente, nombre, telefono FROM Paciente WHERE nroPaciente = ?";

    String SQL_GETALL = "SELECT * FROM Paciente ORDER BY nombre ASC";

    String SQL_EXISTBYID = "SELECT 1 FROM Paciente WHERE nroPaciente = ?";


    @Override
    public List<Paciente> getAll() {
        List<Paciente> pacientes = new ArrayList<>();
        return pacientes;
    }

    @Override
    public void insert(Paciente paciente) {

        try(Connection conn = ObtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setString(1, paciente.getNombre());
            ps.setInt(2, paciente.getTelefono());
            ps.executeUpdate();

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Paciente paciente) {

        try(Connection conn = ObtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, paciente.getNombre());
            ps.setInt(2, paciente.getTelefono());
            ps.executeUpdate();

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Integer nroPaciente) {
        try(Connection conn = ObtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, nroPaciente);
            ps.executeUpdate();

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Paciente getById(Integer nroPaciente) {

        Paciente paciente = null;

        try(Connection conn = ObtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_GETBYID)) {

            ps.setInt(1, nroPaciente);
            ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    paciente = new Paciente();
                    paciente.setNroPaciente(nroPaciente);
                    paciente.setNombre(rs.getString("nombre"));
                    paciente.setTelefono(rs.getInt("telefono"));
                }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return paciente;
    }

    @Override
    public boolean existsById(Integer nroPaciente) {
        try(Connection conn = ObtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_EXISTBYID)) {

            ps.setInt(1, nroPaciente);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
