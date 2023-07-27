package stu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Equipo {
    private String nombre;
    private int cantidadMiembros;

    public Equipo(String nombre, int cantidadMiembros) {
        this.nombre = nombre;
        this.cantidadMiembros = cantidadMiembros;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadMiembros() {
        return cantidadMiembros;
    }

    public abstract void realizarReunion();

    @Override
    public String toString() {
        return "Equipo [Nombre: " + nombre + ", Cantidad de Miembros: " + cantidadMiembros + "]";
    }
}

