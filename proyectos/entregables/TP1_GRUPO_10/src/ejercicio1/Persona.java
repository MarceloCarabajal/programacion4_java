package ejercicio1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Persona {
	//Atributos
	
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String genero;
    private String direccion;
    private String telefono;
    private String email;
    
    //Constructores
    public Persona() {
    	setDni(null);
        setNombre(null);
        setApellido(null);
        setFechaNacimiento(null);
        setGenero(null);
        setDireccion(null);
        setTelefono(null);
        setEmail(null);
    }
    
    public Persona(String dni, String nombre, String apellido, LocalDate fechaNacimiento, 
            String genero, String direccion, String telefono, String email) {
    	setDni(dni);
        setNombre(nombre);
        setApellido(apellido);
        setFechaNacimiento(fechaNacimiento);
        setGenero(genero);
        setDireccion(direccion);
        setTelefono(telefono);
        setEmail(email);
    }
    
    //Getters and Setters
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = (dni == null || dni.isBlank()) ? "Sin DNI" : dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = (nombre == null || nombre.isBlank()) ? "sin nombre" : nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = (apellido == null || apellido.isBlank()) ? "Sin Apellido" : apellido;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = (fechaNacimiento == null) ? LocalDate.of(2025, 1, 1) : fechaNacimiento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = (genero == null || genero.isBlank()) ? "Sin Género" : genero;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = (direccion == null || direccion.isBlank()) ? "Sin Dirección" : direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = (telefono == null || telefono.isBlank()) ? "Sin Teléfono" : telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = (email == null || !email.contains("@")) ? "Sin Email" : email;
	}

	//Metodo toString()
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": DNI = " + dni+ ", Nombre = " + nombre + ", Apellido = " + apellido + ", Fecha de Nacimiento = "
				+ fechaNacimiento.format(FORMATO_FECHA) + ", Genero = " + genero + ", Direccion = " + direccion + ", Telefono = " + telefono
				+ ", Email = " + email;
	}
    
    
}

