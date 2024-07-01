package ar.edu.unju.fi.Ejercicio4.Main;

import ar.edu.unju.fi.Ejercicio4.Constantes.Posicion;
import ar.edu.unju.fi.Ejercicio4.Model.Jugador;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Jugador> jugadores = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("1 – Alta de jugador");
        System.out.println("2 – Mostrar todos los jugadores");
        System.out.println("3 – Modificar la posición de un jugador");
        System.out.println("4 – Eliminar un jugador");
        System.out.println("5 – Salir");
        System.out.print("Elija una opción: ");
    }

    private static int obtenerOpcion() {
        int opcion = -1;
        try {
            opcion = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpiar el buffer
        }
        return opcion;
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> altaDeJugador();
            case 2 -> mostrarJugadores();
            case 3 -> modificarPosicionJugador();
            case 4 -> eliminarJugador();
            case 5 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void altaDeJugador() {
        try {
            System.out.print("Ingrese el nombre: ");
            String nombre = scanner.next();
            System.out.print("Ingrese el apellido: ");
            String apellido = scanner.next();
            System.out.print("Ingrese la fecha de nacimiento (AAAA-MM-DD): ");
            LocalDate fechaNacimiento = LocalDate.parse(scanner.next());
            System.out.print("Ingrese la nacionalidad: ");
            String nacionalidad = scanner.next();
            System.out.print("Ingrese la estatura (en metros): ");
            double estatura = scanner.nextDouble();
            System.out.print("Ingrese el peso (en kilogramos): ");
            double peso = scanner.nextDouble();
            Posicion posicion = seleccionarPosicion();

            Jugador jugador = new Jugador(nombre, apellido, fechaNacimiento, nacionalidad, estatura, peso, posicion);
            jugadores.add(jugador);
            System.out.println("Jugador agregado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar el jugador. Por favor, intente nuevamente.");
        }
    }

    private static Posicion seleccionarPosicion() {
        System.out.println("---- Posición ------");
        System.out.println("1 - Delantero");
        System.out.println("2 - Medio");
        System.out.println("3 - Defensa");
        System.out.println("4 - Arquero");
        System.out.print("Elija una opción: ");

        int opcion = obtenerOpcion();
        return switch (opcion) {
            case 1 -> Posicion.DELANTERO;
            case 2 -> Posicion.MEDIO;
            case 3 -> Posicion.DEFENSA;
            case 4 -> Posicion.ARQUERO;
            default -> {
                System.out.println("Opción no válida, eligiendo Delantero por defecto.");
                yield Posicion.DELANTERO;
            }
        };
    }

    private static void mostrarJugadores() {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores registrados.");
        } else {
            jugadores.forEach(System.out::println);
        }
    }

    private static void modificarPosicionJugador() {
        try {
            System.out.print("Ingrese el nombre del jugador: ");
            String nombre = scanner.next();
            System.out.print("Ingrese el apellido del jugador: ");
            String apellido = scanner.next();

            Jugador jugador = jugadores.stream()
                    .filter(j -> j.getNombre().equalsIgnoreCase(nombre) && j.getApellido().equalsIgnoreCase(apellido))
                    .findFirst()
                    .orElse(null);

            if (jugador == null) {
                System.out.println("Jugador no encontrado.");
                return;
            }

            System.out.println("Jugador encontrado: " + jugador);
            Posicion nuevaPosicion = seleccionarPosicion();
            jugador.setPosicion(nuevaPosicion);
            System.out.println("Posición modificada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al modificar la posición del jugador. Por favor, intente nuevamente.");
        }
    }

    private static void eliminarJugador() {
        try {
            System.out.print("Ingrese el nombre del jugador: ");
            String nombre = scanner.next();
            System.out.print("Ingrese el apellido del jugador: ");
            String apellido = scanner.next();

            Iterator<Jugador> iterator = jugadores.iterator();
            while (iterator.hasNext()) {
                Jugador jugador = iterator.next();
                if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
                    iterator.remove();
                    System.out.println("Jugador eliminado exitosamente.");
                    return;
                }
            }

            System.out.println("Jugador no encontrado.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el jugador. Por favor, intente nuevamente.");
        }
    }
}
