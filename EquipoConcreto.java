
package stu;

public class EquipoConcreto extends Equipo {
    public EquipoConcreto(String nombre, int cantidadMiembros) {
		super(nombre, cantidadMiembros);
		// TODO Auto-generated constructor stub
	}

	
    @Override
    public void realizarReunion() {
       
        System.out.println("Realizando reunión del EquipoConcreto: " + this.getNombre());
    }

    // Otros métodos específicos para EquipoConcreto
}
