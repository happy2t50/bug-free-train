package stu;

import java.time.LocalDate;
import java.util.List;

public class TareaPorEquipos extends Tarea {
    private int cantidadEquipos;
    private List<Equipo> equipos;

    public TareaPorEquipos(String nombre, String descripcion, String categoria, LocalDate fechaVencimiento, List<Equipo> equipos) {
        super(nombre, descripcion, categoria, fechaVencimiento);
        this.equipos = equipos; // Asignar la lista de equipos recibida como parámetro
        this.cantidadEquipos = equipos.size(); // Asignar el tamaño de la lista de equipos
    }

    public int getCantidadEquipos() {
        return cantidadEquipos;
    }

    public void setCantidadEquipos(int cantidadEquipos) {
        this.cantidadEquipos = cantidadEquipos;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
        this.cantidadEquipos = equipos.size(); // Actualizar la cantidad de equipos al cambiar la lista
    }

    @Override
    public String toString() {
        String nombresEquipos = obtenerNombresEquipos();
        return super.toString() + " (Equipos: " + nombresEquipos + ")";
    }

    private String obtenerNombresEquipos() {
        StringBuilder sb = new StringBuilder();
        for (Equipo equipo : equipos) {
            sb.append(equipo.getNombre()).append(", ");
        }
        // Eliminar la última coma y espacio
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    @Override
    public String toCsvString() {
        // Implementación para guardar en archivo CSV, si es necesario
        return super.getNombre() + "," + super.getDescripcion() + "," + super.getCategoria() + ","
                + super.getFechaVencimiento() + "," + cantidadEquipos;
    }

    public static TareaPorEquipos fromCsvString(String csvString) {
        // Implementación para cargar desde archivo CSV, si es necesario
        return null;
    }

    @Override
    protected void setEmpleadoAsignado(Empleado empleado) {
        // Implementación para asignar empleado, si es necesario
    }
}


