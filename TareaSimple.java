package stu;

import java.time.LocalDate;

public class TareaSimple extends Tarea {
    public TareaSimple(String nombre, String descripcion, String categoria, LocalDate fechaVencimiento) {
        super(nombre, descripcion, categoria, fechaVencimiento);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toCsvString() {
        return String.format("TareaSimple,%s,%s,%s,%s", getNombre(), getDescripcion(), getCategoria(), getFechaVencimiento());
    }

    public static TareaSimple fromCsvString(String csvString) {
        String[] valores = csvString.split(",");
        if (valores.length == 5 && valores[0].equals("TareaSimple")) {
            String nombre = valores[1];
            String descripcion = valores[2];
            String categoria = valores[3];
            LocalDate fechaVencimiento = LocalDate.parse(valores[4]);
            return new TareaSimple(nombre, descripcion, categoria, fechaVencimiento);
        }
        return null;
    }

    @Override
    protected void setEmpleadoAsignado(Empleado empleado) {
       
    }
}
