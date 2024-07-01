package ar.edu.unju.fi.Ejercicio3.Main;

import ar.edu.unju.fi.Ejercicio3.Constantes.Provincia;

public class Main {
    public static void main(String[] args) {
        Provincia[] provincias = Provincia.values();

        for (Provincia provincia : provincias) {
            System.out.println("Provincia: " + provincia.name());
            System.out.println("Cantidad de Población: " + provincia.getCantidadPoblacion());
            System.out.println("Superficie: " + provincia.getSuperficie());
            System.out.println("Densidad Poblacional: " + provincia.calcularDensidadPoblacional());
            System.out.println("-----------------------------");
        }
    }
}
