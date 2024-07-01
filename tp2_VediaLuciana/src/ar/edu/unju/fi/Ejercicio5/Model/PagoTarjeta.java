package ar.edu.unju.fi.Ejercicio5.Model;

import ar.edu.unju.fi.Ejercicio5.Interfaces.Pago;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PagoTarjeta implements Pago {
    private String numeroTarjeta;
    private LocalDate fechaPago;
    private double montoPagado;

    public PagoTarjeta(String numeroTarjeta, LocalDate fechaPago, double montoPagado) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaPago = fechaPago;
        this.montoPagado = montoPagado;
    }

    @Override
    public void realizarPago(double monto) {
        double totalPago = monto * 1.15; // 15% surcharge
        this.montoPagado = totalPago;
    }

    @Override
    public void imprimirRecibo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.println("Recibo de Pago (Tarjeta):");
        System.out.println("Número de tarjeta: " + numeroTarjeta);
        System.out.println("Fecha de pago: " + fechaPago.format(formatter));
        System.out.println("Monto pagado: " + montoPagado);
    }
}
