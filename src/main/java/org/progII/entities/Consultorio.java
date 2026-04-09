package org.progII.entities;

import java.util.ArrayList;
import java.util.List;

public class Consultorio {
    private int nroConsultorio;
    private String medico;
    private List<Turno> turnos;

    public Consultorio(int nroConsultorio, String medico) {
        this.nroConsultorio = nroConsultorio;
        this.medico = medico;
        this.turnos = new ArrayList<>();
    }

    public Consultorio() {
    }

    public int getNroConsultorio() {
        return nroConsultorio;
    }

    public void setNroConsultorio(int nroConsultorio) {
        this.nroConsultorio = nroConsultorio;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}
