package org.progII.entities;

public class Paciente {
    private int nroPaciente;
    private int telefono;
    private String nombre;

    public Paciente(int telefono, String nombre) {
        this.telefono = telefono;
        this.nombre = nombre;
    }

    public int getNroPaciente() {
        return nroPaciente;
    }

    public void setNroPaciente(int nroPaciente) {
        this.nroPaciente = nroPaciente;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
