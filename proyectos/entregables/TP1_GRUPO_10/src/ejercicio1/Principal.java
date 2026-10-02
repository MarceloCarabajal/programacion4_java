package ejercicio1;

import java.time.LocalDate;
import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) {
		
		Persona p1 = new Empleado();
		
		Persona p2 = new Empleado(
		   "12345678", 
           "Julian", 
           "Gomez", 
           LocalDate.of(1990, 5, 15), 
           "Masculino", 
           "Av. Corrientes 123", 
           "1223344556", 
           "julian.Gomez@email.com", 
           "Desarrollador"
        );
		
		Persona p3 = new Empleado();

		Persona p4 = new Empleado(
		   "30111222",
           "Lucia",
           "Fernandez",
           LocalDate.of(1983, 11, 2),
           "Femenino",
           "Belgrano 850",
           "1145678900",
           "lucia.fernandez@email.com",
           "Analista Funcional"
        );

		Persona p5 = new Empleado(
		   "41998777",
           "Martin",
           "Sosa",
           LocalDate.of(1999, 3, 24),
           "Masculino",
           "Mitre 1420",
           "1156781234",
           "martin.sosa@email.com",
           "Tester QA"
        );
		
		
		
		ArrayList<Persona> empleados = new ArrayList<>();
		empleados.add(p1);
		empleados.add(p2);
		empleados.add(p3);
		empleados.add(p4);
		empleados.add(p5);
		
		for (Persona emp : empleados) {
		    System.out.println(emp.toString());
		}
		
		// Verificacion del metodo estatico
        System.out.println("El próximo legajo será el " + Empleado.devuelveProximoLegajo());
    }

}

