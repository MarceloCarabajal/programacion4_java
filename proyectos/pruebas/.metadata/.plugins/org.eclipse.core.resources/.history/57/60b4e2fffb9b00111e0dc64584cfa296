package dominio;

public class Articulo {
	
	//atributos
	private int id;
	private String nombre;
	static int cont = 0;
	
	//constructores
	public Articulo() {
		cont ++;
		this.id = cont;
		nombre = "Sin nombre";
	}
	
	public Articulo(String nombre) {
		cont ++;
		this.id = cont;
		this.nombre = nombre;
	}
	
	// getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//metodo toString()
	@Override
	public String toString() {
		return "Articulo id=" + id + ", nombre=" + nombre;
	}
	
	// metodo 
	public static int devuelveProximoId() {
		return cont+1;
	}
	
}
