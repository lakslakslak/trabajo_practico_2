package ar.edu.unju.fi.Ejercicio1.Main;

import ar.edu.unju.fi.Ejercicio1.Model.Producto;
import ar.edu.unju.fi.Ejercicio1.Model.Producto.Categoria;
import ar.edu.unju.fi.Ejercicio1.Model.Producto.OrigenFabricacion;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Producto> productos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != 4);
    }

    private static void mostrarMenu() {
        System.out.println("1 – Crear Producto");
        System.out.println("2 – Mostrar productos");
        System.out.println("3 – Modificar producto");
        System.out.println("4 – Salir");
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
            case 1 -> crearProducto();
            case 2 -> mostrarProductos();
            case 3 -> modificarProducto();
            case 4 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void crearProducto() {
        Producto producto = new Producto();

        System.out.print("Ingrese el código: ");
        producto.setCodigo(scanner.nextInt());

        System.out.print("Ingrese la descripción: ");
        producto.setDescripcion(scanner.next());

        System.out.print("Ingrese el precio unitario: ");
        producto.setPrecioUnitario(scanner.nextDouble());

        producto.setOrigenFabricacion(seleccionarOrigenFabricacion());
        producto.setCategoria(seleccionarCategoria());

        productos.add(producto);
        System.out.println("Producto creado exitosamente.");
    }

    private static OrigenFabricacion seleccionarOrigenFabricacion() {
        System.out.println("---- Origen de fabricación ------");
        System.out.println("1 - Argentina");
        System.out.println("2 - China");
        System.out.println("3 - Brasil");
        System.out.println("4 - Uruguay");
        System.out.print("Elija una opción: ");

        int opcion = obtenerOpcion();
        return switch (opcion) {
            case 1 -> OrigenFabricacion.ARGENTINA;
            case 2 -> OrigenFabricacion.CHINA;
            case 3 -> OrigenFabricacion.BRASIL;
            case 4 -> OrigenFabricacion.URUGUAY;
            default -> {
                System.out.println("Opción no válida, eligiendo Argentina por defecto.");
                yield OrigenFabricacion.ARGENTINA;
            }
        };
    }

    private static Categoria seleccionarCategoria() {
        System.out.println("------ Categoría ------");
        System.out.println("1 – Telefonía");
        System.out.println("2 – Informática");
        System.out.println("3 – Electro hogar");
        System.out.println("4 – Herramientas");
        System.out.print("Elija una opción: ");

        int opcion = obtenerOpcion();
        return switch (opcion) {
            case 1 -> Categoria.TELEFONIA;
            case 2 -> Categoria.INFORMATICA;
            case 3 -> Categoria.ELECTROHOGAR;
            case 4 -> Categoria.HERRAMIENTAS;
            default -> {
                System.out.println("Opción no válida, eligiendo Telefonía por defecto.");
                yield Categoria.TELEFONIA;
            }
        };
    }

    private static void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    private static void modificarProducto() {
        System.out.print("Ingrese el código del producto a modificar: ");
        int codigo = scanner.nextInt();

        Producto producto = productos.stream()
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(null);

        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Producto encontrado: " + producto);
        System.out.print("Ingrese la nueva descripción (actual: " + producto.getDescripcion() + "): ");
        producto.setDescripcion(scanner.next());

        System.out.print("Ingrese el nuevo precio unitario (actual: " + producto.getPrecioUnitario() + "): ");
        producto.setPrecioUnitario(scanner.nextDouble());

        producto.setOrigenFabricacion(seleccionarOrigenFabricacion());
        producto.setCategoria(seleccionarCategoria());

        System.out.println("Producto modificado exitosamente.");
    }
}