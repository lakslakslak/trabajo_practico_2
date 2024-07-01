package ar.edu.unju.fi.Ejercicio5.Main;

import ar.edu.unju.fi.Ejercicio1.Model.Producto;
import ar.edu.unju.fi.Ejercicio5.Interfaces.Pago;
import ar.edu.unju.fi.Ejercicio5.Model.PagoEfectivo;
import ar.edu.unju.fi.Ejercicio5.Model.PagoTarjeta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Precarga de productos
        cargarProductos();

        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (opcion) {
                case 1:
                    mostrarProductos();
                    break;
                case 2:
                    realizarCompra();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 3);

        scanner.close();
    }

    private static void cargarProductos() {
        productos.add(new Producto(1, "Smartphone", 500.0, Producto.OrigenFabricacion.CHINA, Producto.Categoria.TELEFONIA));
        productos.add(new Producto(2, "Laptop", 1200.0, Producto.OrigenFabricacion.ARGENTINA, Producto.Categoria.INFORMATICA));
        productos.add(new Producto(3, "Cocina eléctrica", 800.0, Producto.OrigenFabricacion.URUGUAY, Producto.Categoria.ELECTROHOGAR));
        productos.add(new Producto(4, "Taladro", 150.0, Producto.OrigenFabricacion.BRASIL, Producto.Categoria.HERRAMIENTAS));
        // Añadir más productos según sea necesario
    }

    private static void mostrarMenu() {
        System.out.println("Menú de opciones:");
        System.out.println("1 - Mostrar productos");
        System.out.println("2 - Realizar compra");
        System.out.println("3 - Salir");
        System.out.print("Elija una opción: ");
    }

    private static void mostrarProductos() {
        System.out.println("Listado de productos:");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    private static void realizarCompra() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles para comprar.");
            return;
        }

        // Seleccionar productos para comprar (ejemplo: seleccionar los primeros 3 productos)
        ArrayList<Producto> productosComprados = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            productosComprados.add(productos.get(i));
        }

        // Calcular total de la compra
        double totalCompra = productosComprados.stream()
                .mapToDouble(Producto::getPrecioUnitario)
                .sum();

        System.out.println("Total a pagar: " + totalCompra);

        // Seleccionar método de pago
        System.out.println("Seleccione el método de pago:");
        System.out.println("1 - Pago efectivo");
        System.out.println("2 - Pago con tarjeta");
        int opcionPago = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (opcionPago) {
            case 1:
                PagoEfectivo pagoEfectivo = new PagoEfectivo(totalCompra, LocalDate.now());
                try {
                    pagoEfectivo.realizarPago(totalCompra);
                    pagoEfectivo.imprimirRecibo();
                } catch (Exception e) {
                    System.out.println("Error al realizar el pago en efectivo: " + e.getMessage());
                }
                break;
            case 2:
                System.out.print("Ingrese el número de tarjeta: ");
                String numeroTarjeta = scanner.nextLine();
                PagoTarjeta pagoTarjeta = new PagoTarjeta(numeroTarjeta, LocalDate.now(), totalCompra);
                try {
                    pagoTarjeta.realizarPago(totalCompra);
                    pagoTarjeta.imprimirRecibo();
                } catch (Exception e) {
                    System.out.println("Error al realizar el pago con tarjeta: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Opción de pago inválida.");
        }
    }
}