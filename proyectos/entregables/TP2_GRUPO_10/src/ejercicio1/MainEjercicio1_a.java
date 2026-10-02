package ejercicio1;

public class MainEjercicio1_a {

	public static void main(String[] args) {
		Persona p1 = new Persona();
        try {
            Persona.VerificarDNI("AA202020");
            p1.setDni("AA202020");
            System.out.println("Persona agregada correctamente");
        } catch (ExVerificarDNI e) {
            System.out.println("Persona no agregada por no verificar el DNI");
        }

        Persona p2 = new Persona();
        try {
            Persona.VerificarDNI("20202020");
            p2.setDni("20202020");
            System.out.println("Persona agregada correctamente");
        } catch (ExVerificarDNI e) {
            System.out.println("Persona no agregada por no verificar el DNI");
        }
	}
}
