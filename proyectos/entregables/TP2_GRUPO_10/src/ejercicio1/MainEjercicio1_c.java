package ejercicio1;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.TreeSet;

public class MainEjercicio1_c {

	public static void main(String[] args) {
		// Ya se añadió Comparable en la clase Empleado.
		// El TreeSet va a ordenar automaticamente por legajo al insertar.
		TreeSet<Empleado> empleados = new TreeSet<>();
		
		empleados.add(new Empleado("30111222", "Lucia", "Fernandez", LocalDate.of(1991, 4, 12),
				"Femenino", "Av. Mitre 1200", "1145678901", "lucia.fernandez@email.com", "Analista"));
		empleados.add(new Empleado("28999444", "Martin", "Sosa", LocalDate.of(1987, 9, 3),
				"Masculino", "Belgrano 450", "1156789012", "martin.sosa@email.com", "Desarrollador"));
		empleados.add(new Empleado("33555777", "Paula", "Medina", LocalDate.of(1994, 12, 27),
				"Femenino", "San Martin 80", "1167890123", "paula.medina@email.com", "Tester"));
		empleados.add(new Empleado("31222888", "Nicolas", "Vera", LocalDate.of(1990, 2, 8),
				"Masculino", "Rivadavia 3300", "1178901234", "nicolas.vera@email.com", "Soporte"));
		empleados.add(new Empleado("27333111", "Sofia", "Aguirre", LocalDate.of(1985, 6, 19),
				"Femenino", "Sarmiento 15", "1189012345", "sofia.aguirre@email.com", "Lider de proyecto"));
		
		System.out.println("Cantidad de empleados cargados: " + empleados.size());
		
		// Muestra de menor a mayor legajo
		System.out.println(" Listado de empleados ordenados por legajo");
		Iterator<Empleado> it = empleados.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
}
