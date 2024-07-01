package ar.edu.unju.fi.Ejercicio3.Constantes;

public enum Provincia {
    JUJUY(770881, 53219),
    SALTA(1333365, 155488),
    TUCUMAN(1687305, 22524),
    CATAMARCA(429556, 102602),
    LA_RIOJA(393531, 89680),
    SANTIAGO_DEL_ESTERO(896461, 136351);

    private int cantidadPoblacion;
    private double superficie;

    Provincia(int cantidadPoblacion, double superficie) {
        this.cantidadPoblacion = cantidadPoblacion;
        this.superficie = superficie;
    }

    public int getCantidadPoblacion() {
        return cantidadPoblacion;
    }

    public void setCantidadPoblacion(int cantidadPoblacion) {
        this.cantidadPoblacion = cantidadPoblacion;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public double calcularDensidadPoblacional() {
        return cantidadPoblacion / superficie;
    }
}
