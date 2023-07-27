package stu;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

public class Equipos extends Equipo {

    public Equipos(String nombre, int cantidadMiembros) {
        super(nombre, cantidadMiembros);
    }

    @Override
    public void realizarReunion() {
        String mensaje = "El equipo '" + getNombre() + "' está realizando una reunión.";
        JOptionPane.showMessageDialog(null, mensaje, "Reunión del Equipo", JOptionPane.INFORMATION_MESSAGE);
    }
}
