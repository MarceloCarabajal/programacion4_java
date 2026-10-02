package ejercicio1;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;

public class MainEjercicio1_d {

	public static void main(String[] args) {
	
		HashSet<Persona> personas = new HashSet<>();

        personas.add(new Persona("11111111", "Ana", "Lopez", LocalDate.of(1990, 1, 10),
                "Femenino", "Calle 1", "1111111111", "ana@email.com"));
        personas.add(new Persona("22222222", "Bruno", "Diaz", LocalDate.of(1988, 3, 22),
                "Masculino", "Calle 2", "2222222222", "bruno@email.com"));
        personas.add(new Persona("33333333", "Carla", "Ruiz", LocalDate.of(1995, 7, 5),
                "Femenino", "Calle 3", "3333333333", "carla@email.com"));
        personas.add(new Persona("44444444", "Damian", "Perez", LocalDate.of(1992, 11, 30),
                "Masculino", "Calle 4", "4444444444", "damian@email.com"));
        personas.add(new Persona("55555555", "Elena", "Gomez", LocalDate.of(1985, 5, 18),
                "Femenino", "Calle 5", "5555555555", "elena@email.com"));

        System.out.println("Cantidad de personas cargadas: " + personas.size());

        // Prueba de deduplicación: intento agregar una Persona con el mismo DNI
        // que "Ana" (11111111). El HashSet no debería incorporarla, gracias a
        // que equals()/hashCode() comparan por dni.
        boolean agregada = personas.add(new Persona("11111111", "Ana Duplicada", "Lopez",
                LocalDate.of(1990, 1, 10), "Femenino", "Otra calle", "0000000000", "duplicado@email.com"));
        System.out.println("¿Se agregó el duplicado? " + agregada);
        System.out.println("Cantidad de personas luego del intento de duplicado: " + personas.size());

        System.out.println("\nListado de personas:");
        Iterator<Persona> it = personas.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }

	}

}
