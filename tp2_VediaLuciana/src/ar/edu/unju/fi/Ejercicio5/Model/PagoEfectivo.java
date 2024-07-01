package ar.edu.unju.fi.Ejercicio5.Model;


import ar.edu.unju.fi.Ejercicio5.Interfaces.Pago;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PagoEfectivo implements Pago {
    private double montoPagado;
    private LocalDate fechaPago;

    public PagoEfectivo(double montoPagado, LocalDate fechaPago) {
        this.montoPagado = montoPagado;
        this.fechaPago = fechaPago;
    }

    @Override
    public void realizarPago(double monto) {
        double totalPago = monto * 0.90; // 10% discount
        this.montoPagado = totalPago;
    }

    @Override
    public void imprimirRecibo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.println("Recibo de Pago (Efectivo):");
        System.out.println("Fecha de pago: " + fechaPago.format(formatter));
        System.out.println("Monto pagado: " + montoPagado);
    }
}
