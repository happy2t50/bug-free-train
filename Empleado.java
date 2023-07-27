package stu;

import java.util.ArrayList;
import java.util.List;

public class Empleado {
    private String nombre;
    private List<Tarea> tareasAsignadas;

    public Empleado(String nombre) {
        this.nombre = nombre;
        this.tareasAsignadas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void asignarTarea(Tarea tarea) {
        tareasAsignadas.add(tarea);
    }

    public void listarTareasAsignadas() {
        System.out.println("=== Tareas Asignadas a " + nombre + " ===");
        if (tareasAsignadas.isEmpty()) {
            System.out.println("No hay tareas asignadas.");
        } else {
            for (Tarea tarea : tareasAsignadas) {
                System.out.println(tarea);
            }
        }
    }

    public boolean isTieneTareaAsignada() {
        return !tareasAsignadas.isEmpty();
    }
    @Override
    public String toString() {
        return "Empleado: " + nombre;
    }
}


