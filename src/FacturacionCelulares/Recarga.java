package FacturacionCelulares;

import FacturacionException.RecargaExc;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase Recarga
 */
public class Recarga implements Serializable {
    private static final long serialVersionUID = 1L;
    public LocalDate fecha;
    public long valor;

    /**
     * Constructor de la clase Recarga con parametros
     * @param fecha
     * @param valor
     */
    public Recarga(LocalDate fecha, long valor) throws RecargaExc {
        if (fecha == null) {
            throw new RecargaExc("La fecha no puede ser nula");
        }
        if (valor <= 0) {
            throw new RecargaExc("El valor de la recarga no puede ser menor o igual a 0");
        }
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
        return "Recarga[" + "fecha:" + fecha + ", valor=" + valor + "]\n";
    }
}
