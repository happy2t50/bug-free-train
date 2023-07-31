package stu;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AppGestionTareas {
    private List<Tarea> tareas;
    private List<Empleado> empleados;
    private Scanner scanner;
    private Random random;

    public AppGestionTareas() {
        tareas = new ArrayList<>();
        empleados = new ArrayList<>();
        scanner = new Scanner(System.in);
        random = new Random();
    }

    public void iniciar() {
        int opcion = 8;
        do {
            mostrarMenuPrincipal();
            opcion = obtenerOpcion();
            ejecutarOpcionPrincipal(opcion);
        } while (opcion != 9);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("=== Menú Principal ===");
        System.out.println("1. Gestionar Tareas");
        System.out.println("2. Gestionar Empleados");
        System.out.println("");
        System.out.println("9. Salir");
        System.out.println("======================");
        System.out.print("Ingrese una opción: ");
    }

    private void ejecutarOpcionPrincipal(int opcion) {
        switch (opcion) {
            case 1:
                mostrarMenuTareas();
                break;
            case 2:
                mostrarMenuEmpleados();
                break;
            case 9:
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
                break;
        }
    }

    private void mostrarMenuTareas() {
        int opcion = 0;
        do {
            System.out.println("\n=== Gestión de Tareas ===");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Listar tareas");
            System.out.println("3. Marcar tarea como concluida");
            System.out.println("4. Modificar tarea");
            System.out.println("5. Eliminar tarea");
            System.out.println("6. Asignar tarea a empleado");
            System.out.println("7. Cargar tareas desde archivo");
            System.out.println("8. Guardar tareas en archivo");
            System.out.println("9. Volver al menú principal");
            System.out.println("=========================");
            System.out.print("Ingrese una opción: ");
            opcion = obtenerOpcion();
            ejecutarOpcionTareas(opcion);
        } while (opcion != 9);
    }

    private void ejecutarOpcionTareas(int opcion) {
        scanner.nextLine(); // Limpiar el buffer del scanner

        switch (opcion) {
            case 1:
                try {
                    agregarTarea();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                listarTareas();
                break;
            case 3:
                marcarTareaConcluida();
                break;
            case 4:
                modificarTarea();
                break;
            case 5:
                eliminarTarea();
                break;
            case 6:
                asignarTarea();
                break;
            case 7:
                cargarTareasDesdeArchivo();
                break;
            case 8:
                guardarTareasEnArchivo();
                break;
            case 9:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
                break;
        }
    }

    private void agregarTarea() throws IOException {
        System.out.println("\n=== Agregar Tarea ===");
        System.out.println("1. Tarea Simple");
        System.out.println("2. Tarea por Equipos");
        System.out.print("Ingrese el tipo de tarea que desea agregar: ");
        int tipoTarea = obtenerOpcion();

        switch (tipoTarea) {
            case 1:
                agregarTareaSimple();
                break;
            case 2:
                agregarTareaPorEquipos();
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
                break;
        }
    }

 

    private void agregarTareaSimple() throws IOException {
    	 scanner.nextLine();
        System.out.print("Ingrese el nombre de la tarea: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la descripción de la tarea: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese la categoría de la tarea: ");
        String categoria = scanner.nextLine();

        LocalDate fechaActual = LocalDate.now();
        System.out.println("Fecha actual: " + fechaActual);

        int diasVencimiento;
        do {
            System.out.print("Ingrese la cantidad de días para el vencimiento (debe ser entero ): ");
            diasVencimiento = scanner.nextInt();
            scanner.nextLine();
        } while (diasVencimiento <= 0);

        LocalDate fechaVencimiento = fechaActual.plusDays(diasVencimiento);

        TareaSimple tarea = new TareaSimple(nombre, descripcion, categoria, fechaVencimiento);
        tareas.add(tarea);

        System.out.println("La tarea se ha agregado exitosamente.");
    }

    


    private void agregarTareaPorEquipos() {
    	 scanner.nextLine(); // Limpiar el buffer del scanner
    	    System.out.print("Ingrese el nombre de la tarea: ");
    	    String nombre = scanner.nextLine();
    
        System.out.print("Ingrese la descripción de la tarea: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese la categoría de la tarea: ");
        String categoria = scanner.nextLine();
        
        LocalDate fechaActual = LocalDate.now();
        System.out.println("Fecha actual: " + fechaActual);
        


        int diasVencimiento;
        do {
            System.out.print("Ingrese la cantidad de días para el vencimiento (debe ser entero ): ");
            diasVencimiento = scanner.nextInt();
            scanner.nextLine();
        } while (diasVencimiento <= 0);
        LocalDate fechaVencimiento = fechaActual.plusDays(diasVencimiento);

        System.out.print("Ingrese la cantidad de equipos para la tarea: ");
        int cantidadEquipos = scanner.nextInt();
        scanner.nextLine();

        List<Equipo> equipos = new ArrayList<>(); 

        for (int i = 1; i <= cantidadEquipos; i++) {
            System.out.print("Ingrese el nombre del equipo " + i + ": ");
            String nombreEquipo = scanner.nextLine();
            Equipo equipo = new Equipos(nombreEquipo, i);
            equipos.add(equipo);
        }

        TareaPorEquipos tarea = new TareaPorEquipos(nombre, descripcion, categoria, fechaVencimiento, equipos);
        for (Equipo equipo : equipos) {
            equipo.realizarReunion();
        }
        Empleado empleado = obtenerEmpleadoDisponible();
        if (empleado != null) {
            tarea.setEmpleadoAsignado(empleado);
            System.out.println("La tarea se ha asignado al empleado exitosamente.");
        } else {
            System.out.println("No hay empleados disponibles para asignar la tarea.");
        }

        tareas.add(tarea);
        System.out.println("La tarea por equipos se ha agregado exitosamente.");
    }
    

    private void listarTareas() {
        System.out.println("\n=== Listado de Tareas ===");
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        } else {
            for (Tarea tarea : tareas) {
                System.out.println(tarea);
            }
        }
    }

    private void marcarTareaConcluida() {
        System.out.println("\n=== Marcar Tarea como Concluida ===");
        listarTareas();
        System.out.print("Ingrese el número de folio de la tarea que desea marcar como concluida: ");
        int numeroFolio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        Tarea tarea = buscarTareaPorNumeroFolio(numeroFolio);
        if (tarea != null) {
            tarea.setConcluida(true);
            System.out.println("La tarea se ha marcado como concluida.");
        } else {
            System.out.println("No se encontró una tarea con el número de folio especificado.");
        }
    }

    private void modificarTarea() {
        System.out.println("\n=== Modificar Tarea ===");
        listarTareas();
        System.out.print("Ingrese el número de folio de la tarea que desea modificar: ");
        int numeroFolio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        Tarea tarea = buscarTareaPorNumeroFolio(numeroFolio);
        if (tarea != null) {
            System.out.print("Ingrese el nuevo nombre de la tarea: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese la nueva descripción de la tarea: ");
            String descripcion = scanner.nextLine();

            System.out.print("Ingrese la nueva categoría de la tarea: ");
            String categoria = scanner.nextLine();

            LocalDate fechaVencimiento = obtenerFechaVencimiento();

            tarea.setNombre(nombre);
            tarea.setDescripcion(descripcion);
            tarea.setCategoria(categoria);
            tarea.setFechaVencimiento(fechaVencimiento);

            System.out.println("La tarea se ha modificado exitosamente.");
        } else {
            System.out.println("No se encontró una tarea con el número de folio especificado.");
        }
    }

    private LocalDate obtenerFechaVencimiento() {
        LocalDate fechaActual = LocalDate.now();
        System.out.println("Fecha actual: " + fechaActual);

        int diasVencimiento;
        do {
            System.out.print("Ingrese la cantidad de días para el vencimiento (debe ser un número entero positivo): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Valor inválido. Por favor, ingrese un número entero positivo.");
                scanner.next();
            }
            diasVencimiento = scanner.nextInt();
            scanner.nextLine();
            if (diasVencimiento <= 0) {
                System.out.println("El número de días debe ser un valor positivo. Intente nuevamente.");
            }
        } while (diasVencimiento <= 0);

        return fechaActual.plusDays(diasVencimiento);
    }
    

	private void eliminarTarea() {
        System.out.println("\n=== Eliminar Tarea ===");
        listarTareas();
        System.out.print("Ingrese el número de folio de la tarea que desea eliminar: ");
        int numeroFolio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        Tarea tarea = buscarTareaPorNumeroFolio(numeroFolio);
        if (tarea != null) {
            tareas.remove(tarea);
            System.out.println("La tarea se ha eliminado exitosamente.");
        } else {
            System.out.println("No se encontró una tarea con el número de folio especificado.");
        }
    }

    private void asignarTarea() {
        System.out.println("\n=== Asignar Tarea a Empleado ===");
        listarTareas();
        System.out.print("Ingrese el número de folio de la tarea que desea asignar: ");
        int numeroFolio = scanner.nextInt();
        scanner.nextLine(); 

        Tarea tarea = buscarTareaPorNumeroFolio(numeroFolio);
        if (tarea != null) {
            if (empleados.isEmpty()) {
                System.out.println("No hay empleados registrados. Registre al menos un empleado antes de asignar una tarea.");
                return;
            }

            Empleado empleado = obtenerEmpleadoDisponible();
            if (empleado != null) {
                tarea.setEmpleadoAsignado(empleado);
                System.out.println("La tarea se ha asignado al empleado exitosamente.");
            } else {
                System.out.println("No hay empleados disponibles para asignar la tarea.");
            }
        } else {
            System.out.println("No se encontró una tarea con el número de folio especificado.");
        }
    }

    private Empleado obtenerEmpleadoDisponible() {
        List<Empleado> empleadosDisponibles = new ArrayList<>();
        for (Empleado empleado : empleados) {
            if (!empleado.isTieneTareaAsignada()) {
                empleadosDisponibles.add(empleado);
            }
        }
        if (empleadosDisponibles.isEmpty()) {
            return null;
        } else {
            int indice = random.nextInt(empleadosDisponibles.size());
            return empleadosDisponibles.get(indice);
        }
    }

    private void cargarTareasDesdeArchivo() {
        System.out.println("\n=== Cargar Tareas desde Archivo ===");
        System.out.print("Ingrese la ruta del archivo CSV: ");
        String rutaArchivo = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        	String linea;
            int contadorTareasCargadas = 0;
            while ((linea = br.readLine()) != null) {                
                Tarea tarea = Tarea.fromCsvString(linea);
                
                if (tarea != null) {
                    tareas.add(tarea);
                    contadorTareasCargadas++;
                }
            }
            System.out.println("Se cargaron " + contadorTareasCargadas + " tareas desde el archivo.");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo especificado.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo.");
        }
    }

    private void guardarTareasEnArchivo() {
        System.out.println("\n=== Guardar Tareas en Archivo ===");
        System.out.print("Ingrese la ruta del archivo CSV: ");
        String rutaArchivo = scanner.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Tarea tarea : tareas) {
                String linea = tarea.toCsvString();
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Se guardaron las tareas en el archivo.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
        }
    }

    private Tarea buscarTareaPorNumeroFolio(int numeroFolio) {
        for (Tarea tarea : tareas) {
            if (tarea.getNumeroFolio() == numeroFolio) {
                return tarea;
            }
        }
        return null;
    }

    private void mostrarMenuEmpleados() {
        int opcion = 0;
        do {
            System.out.println("\n=== Gestión de Empleados ===");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Listar empleados");
            System.out.println("9. Volver al menú principal");
            System.out.println("===========================");
            System.out.print("Ingrese una opción: ");
            opcion = obtenerOpcion();
            ejecutarOpcionEmpleados(opcion);
        } while (opcion != 9);
    }

    private void ejecutarOpcionEmpleados(int opcion) {
        scanner.nextLine(); // Limpiar el buffer del scanner

        switch (opcion) {
            case 1:
                agregarEmpleado();
                break;
            case 2:
                listarEmpleados();
                break;
            case 9:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
                break;
        }
    }

    private void agregarEmpleado() {
        System.out.println("\n=== Agregar Empleado ===");

        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();

        Empleado empleado = new Empleado(nombre);
        empleados.add(empleado);

        System.out.println("El empleado se ha agregado exitosamente.");
    }

    private void listarEmpleados() {
        System.out.println("\n=== Listado de Empleados ===");
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado empleado : empleados) {
                System.out.println(empleado);
            }
        }
    }

    private int obtenerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("Opción inválida. Intente nuevamente.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        AppGestionTareas app = new AppGestionTareas();
        app.iniciar();
    }
}

