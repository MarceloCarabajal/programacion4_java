package ejercicio1;

import java.time.LocalDate;

public class Empleado extends Persona {
	// use final porque el enunciado dice que es una variable constante
	private final int legajo; 
	private String puesto;

	// contador estatico para autogenerar el legajo (arranca en 1000)
	private static int proximoLegajo = 1000;
	
	// Constructores
	public Empleado() {
		super();
		legajo = proximoLegajo;
		proximoLegajo++;
		puesto = "Sin puesto";	// A TENER EN CONSIDERACIÓN ESTO, YA QUE ES ALGO QUE EL ENUNCIADO NO LO ESPECIFICA
	}
	public Empleado(String dni, String nombre, String apellido, LocalDate fechaNacimiento, 
            String genero, String direccion, String telefono, String email, String puesto) {
		super(dni, nombre, apellido, fechaNacimiento, genero, direccion, telefono, email);
		legajo = proximoLegajo;
		proximoLegajo++;
		this.puesto = puesto;
	}
	
	// Getters y setters
	public int getLegajo() {
		return legajo;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	// Metodo estatico que devuelve el proximo legajo a generar.
	public static int devuelveProximoLegajo() {
		return proximoLegajo;
	}


	// Metodo toString()
	@Override
	public String toString() {
		return  super.toString() + ", Legajo = " + legajo + ", Puesto = " + puesto;
	}
	
}
