package dominio;

public class Gato extends Animal{
	
	public Gato() {
		super();
	}
	
	public Gato(String nombre) {
		super(nombre);
	}

	@Override
	public String toString() {
		return "Es un gato y su nombre es: " + getNombre();
	}

	@Override
	public String habilitades() {
		return "Los gatos tienen gran flexibilidad y elasticidad";
	}
	
	
}
