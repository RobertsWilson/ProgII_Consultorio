package org.progII.entities;

import java.util.Objects;

public class Paciente {
    private int nroPaciente;
    private int telefono;
    private String nombre;

    public Paciente(int nroPaciente, int telefono, String nombre) {
        this.nroPaciente = nroPaciente;
        this.telefono = telefono;
        this.nombre = nombre;
    }

    public Paciente() {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return nroPaciente == paciente.nroPaciente && telefono == paciente.telefono && Objects.equals(nombre, paciente.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nroPaciente, telefono, nombre);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nroPaciente=" + nroPaciente +
                ", telefono=" + telefono +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
