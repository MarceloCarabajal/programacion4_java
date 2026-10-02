package ejercicio2;

public class Polideportivo implements InstalacionDeportiva, Edificio {
	private String nombre;
    private double superficie;

    public Polideportivo(String nombre, double superficie) {
        this.nombre = nombre;
        this.superficie = superficie;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public double getSuperficieEdificio() {
        return superficie;
    }

    @Override
    public int getTipoDeInstalacion() {
        // el enunciado no especifica valores concretos
        return 1;
    }

    @Override
    public String toString() {
        return "Polideportivo [nombre=" + nombre + ", superficie=" + superficie + " m2]";
    }
}
