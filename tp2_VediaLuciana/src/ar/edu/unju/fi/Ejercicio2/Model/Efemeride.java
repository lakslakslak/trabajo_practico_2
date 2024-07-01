package ar.edu.unju.fi.Ejercicio2.Model;

import ar.edu.unju.fi.Ejercicio2.Constante.Mes;

public class Efemeride {
    private int codigo;
    private Mes mes;
    private int dia;
    private String detalle;

    // Getters y setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Efemeride{" +
                "codigo=" + codigo +
                ", mes=" + mes +
                ", dia=" + dia +
                ", detalle='" + detalle + '\'' +
                '}';
    }
}
