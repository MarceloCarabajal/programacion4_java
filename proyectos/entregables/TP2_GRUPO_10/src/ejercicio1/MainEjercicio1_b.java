package ejercicio1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class MainEjercicio1_b {

	public static void main(String[] args) {

		// Punto E: 5 Empleado guardados dentro de un ArrayList.
		// Se usa ArrayList (no Set) porque acepta duplicados y mantiene el
		// orden de insercion
		ArrayList<Empleado> empleados = new ArrayList<>();

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
		System.out.println("Proximo legajo a asignar: " + Empleado.devuelveProximoLegajo());

		// Recorrido pedido por el enunciado: Iterator, no for-each.
		System.out.println("\n--- Listado de empleados (Iterator) ---");
		Iterator<Empleado> it = empleados.iterator();
		while (it.hasNext()) {
			Empleado e = it.next();
			System.out.println(e.toString());
		}

		// Extra: baja de un empleado durante el recorrido.
		// Con it.remove() el Iterator queda sincronizado con la lista. Si se
		// borrara con empleados.remove(...) dentro del while, la siguiente
		// llamada a next() lanzaria ConcurrentModificationException.
		System.out.println("\n--- Baja del puesto 'Soporte' durante la iteracion ---");
		Iterator<Empleado> itBaja = empleados.iterator();
		while (itBaja.hasNext()) {
			Empleado e = itBaja.next();
			if (e.getPuesto().equals("Soporte")) {
				System.out.println("Se da de baja al legajo " + e.getLegajo() + " (" + e.getNombre() + " " + e.getApellido() + ")");
				itBaja.remove();
			}
		}

		System.out.println("Cantidad de empleados luego de la baja: " + empleados.size());

		System.out.println("\n--- Listado final (Iterator) ---");
		Iterator<Empleado> itFinal = empleados.iterator();
		while (itFinal.hasNext()) {
			System.out.println(itFinal.next().toString());
		}
	}
}
