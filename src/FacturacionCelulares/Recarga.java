package FacturacionCelulares;

import java.io.Serializable;
import java.time.LocalDate;

public class Recarga implements Serializable {
    private long SerialVersionUID = 1L;
    public LocalDate fecha;
    public long valor;

    /**
     * Constructor de la clase Recarga con parametros
     * @param fecha
     * @param valor
     */
    public Recarga(LocalDate fecha, long valor) {
        this.fecha = fecha;
        this.valor = valor;
    }

    /**
     * Metodo que retorna la fecha de la recarga
     * @return fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Metodo que retorna el valor de la recarga
     * @return valor
     */
    public long getValor() {
        return valor;
    }

    /**
     * Metodo que retorna la informacion de la recarga
     * @return String
     */
    @Override
    public String toString() {
        return "\nRecarga{" + "fecha:" + fecha + ", valor=" + valor + '}';
    }
}
