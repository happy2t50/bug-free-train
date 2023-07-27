package stu;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Tarea {
    private static int contadorFolios = 1;

    private int numeroFolio;
    private String nombre;
    private String descripcion;
    private String categoria;
    private LocalDate fechaVencimiento;
    private boolean concluida;
    private Empleado empleadoAsignado;
    public Tarea(String nombre, String descripcion, String categoria, LocalDate fechaVencimiento) {
        this.numeroFolio = contadorFolios++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.concluida = false;
        this.empleadoAsignado= null;
    }

    public int getNumeroFolio() {
        return numeroFolio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
    public Empleado getEmpleadoasignado() {
    	return empleadoAsignado;
    }
    

    public abstract String toCsvString();

    public static Tarea fromCsvString(String csvString) {
        String[] datos = csvString.split(",");
        for(String dato:datos) {
        	System.out.println(dato);
        }
        
        if (datos.length ==  5) {
            try {
                String nombre = datos[0];
                String descripcion = datos[1];
                String categoria = datos[2];
                LocalDate fechaVencimiento = LocalDate.parse(datos[3]);
                boolean concluida = Boolean.parseBoolean(datos[4]);
                Tarea tarea = new TareaSimple(nombre, descripcion, categoria, fechaVencimiento);
                System.out.println(tarea.toString());
                tarea.setConcluida(concluida);

                return tarea;
            } catch (NumberFormatException | DateTimeParseException e) {
                return null;
            }
        } else {
            return null;
        }
    }
    @Override
    public String toString() {
        String empleadoAsignadoString = (empleadoAsignado != null) ? ", Empleado Asignado: " + empleadoAsignado.getNombre() : "";
        return "Tarea [Folio: " + numeroFolio + ", Nombre: " + nombre + ", Descripción: " + descripcion +
                ", Categoría: " + categoria + ", Fecha de Vencimiento: " + fechaVencimiento +
                ", Concluida: " + concluida + empleadoAsignadoString + "]";
    }

	public void realizar() {
		
		
	}

	protected abstract void setEmpleadoAsignado(Empleado empleado);
}

