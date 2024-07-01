package ar.edu.unju.fi.Ejercicio2.Main;


import ar.edu.unju.fi.Ejercicio2.Constante.Mes;
import ar.edu.unju.fi.Ejercicio2.Model.Efemeride;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Efemeride> efemerides = new ArrayList<>();
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
        System.out.println("1 – Crear efeméride");
        System.out.println("2 – Mostrar efemérides");
        System.out.println("3 – Eliminar efeméride");
        System.out.println("4 – Modificar efeméride");
        System.out.println("5 – Salir");
        System.out.print("Elija una opción: ");
    }

    private static int obtenerOpcion() {
        int opcion = -1;
        try {
            opcion = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
        }
        return opcion;
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> crearEfemeride();
            case 2 -> mostrarEfemerides();
            case 3 -> eliminarEfemeride();
            case 4 -> modificarEfemeride();
            case 5 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void crearEfemeride() {
        Efemeride efemeride = new Efemeride();

        System.out.print("Ingrese el código: ");
        efemeride.setCodigo(scanner.nextInt());

        efemeride.setMes(seleccionarMes());

        System.out.print("Ingrese el día: ");
        efemeride.setDia(scanner.nextInt());

        System.out.print("Ingrese el detalle: ");
        efemeride.setDetalle(scanner.next());

        efemerides.add(efemeride);
        System.out.println("Efeméride creada exitosamente.");
    }

    private static Mes seleccionarMes() {
        System.out.println("---- Mes ------");
        System.out.println("1 - Enero");
        System.out.println("2 - Febrero");
        System.out.println("3 - Marzo");
        System.out.println("4 - Abril");
        System.out.println("5 - Mayo");
        System.out.println("6 - Junio");
        System.out.println("7 - Julio");
        System.out.println("8 - Agosto");
        System.out.println("9 - Septiembre");
        System.out.println("10 - Octubre");
        System.out.println("11 - Noviembre");
        System.out.println("12 - Diciembre");
        System.out.print("Elija una opción: ");

        int opcion = obtenerOpcion();
        return switch (opcion) {
            case 1 -> Mes.ENERO;
            case 2 -> Mes.FEBRERO;
            case 3 -> Mes.MARZO;
            case 4 -> Mes.ABRIL;
            case 5 -> Mes.MAYO;
            case 6 -> Mes.JUNIO;
            case 7 -> Mes.JULIO;
            case 8 -> Mes.AGOSTO;
            case 9 -> Mes.SEPTIEMBRE;
            case 10 -> Mes.OCTUBRE;
            case 11 -> Mes.NOVIEMBRE;
            case 12 -> Mes.DICIEMBRE;
            default -> {
                System.out.println("Opción no válida, eligiendo Enero por defecto.");
                yield Mes.ENERO;
            }
        };
    }

    private static void mostrarEfemerides() {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides.");
        } else {
            efemerides.forEach(System.out::println);
        }
    }

    private static void eliminarEfemeride() {
        System.out.print("Ingrese el código de la efeméride a eliminar: ");
        int codigo = scanner.nextInt();

        Efemeride efemeride = efemerides.stream()
                .filter(e -> e.getCodigo() == codigo)
                .findFirst()
                .orElse(null);

        if (efemeride == null) {
            System.out.println("Efeméride no encontrada.");
        } else {
            efemerides.remove(efemeride);
            System.out.println("Efeméride eliminada exitosamente.");
        }
    }

    private static void modificarEfemeride() {
        System.out.print("Ingrese el código de la efeméride a modificar: ");
        int codigo = scanner.nextInt();

        Efemeride efemeride = efemerides.stream()
                .filter(e -> e.getCodigo() == codigo)
                .findFirst()
                .orElse(null);

        if (efemeride == null) {
            System.out.println("Efeméride no encontrada.");
            return;
        }

        System.out.println("Efeméride encontrada: " + efemeride);

        efemeride.setMes(seleccionarMes());

        System.out.print("Ingrese el nuevo día (actual: " + efemeride.getDia() + "): ");
        efemeride.setDia(scanner.nextInt());

        System.out.print("Ingrese el nuevo detalle (actual: " + efemeride.getDetalle() + "): ");
        efemeride.setDetalle(scanner.next());

        System.out.println("Efeméride modificada exitosamente.");
    }
}