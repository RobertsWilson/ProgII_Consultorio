package org.progII.test;

import org.progII.dao.PacienteImpl;
import org.progII.entities.Consultorio;
import org.progII.entities.Paciente;
import org.progII.entities.Turno;

import java.sql.Time;
import java.util.Date;

public class main {

    public static void main(String[] args) {

        Paciente paciente1 = new Paciente(1, 348212345, "Rigoberto");

        Consultorio consultorio1 = new Consultorio(1, "Rigoberto");

        Time tiempo = new Time(10);
        Date fecha = new Date();
        Turno turno1 = new Turno(fecha, tiempo, 1, 1);

        System.out.println(paciente1.toString());
        System.out.println(consultorio1.toString());
        System.out.println(turno1.toString());

        PacienteImpl pacienteDAO = new PacienteImpl();
        pacienteDAO.insert(paciente1);



    }
}
