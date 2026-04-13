package org.progII.test;

import org.progII.dao.ConsultorioImpl;
import org.progII.dao.PacienteImpl;
import org.progII.entities.Consultorio;
import org.progII.entities.Paciente;
import org.progII.entities.Turno;

import java.sql.Time;
import java.util.Date;

public class main {
    public static void main(String[] args) {
        PacienteImpl pacienteDAO = new PacienteImpl();
        ConsultorioImpl consultorioDAO = new ConsultorioImpl();

        for (int i = 1; i <= 10; i++) {

            Paciente p = new Paciente(4444000 + i, "Paciente " + i);
            pacienteDAO.insert(p);


            Consultorio c = new Consultorio( i, "Dr. " + i);
            c.setNroConsultorio(i);
            consultorioDAO.insert(c);

            System.out.println("Insertados Paciente " + i + " y Consultorio " + i);
        }
    }
}
