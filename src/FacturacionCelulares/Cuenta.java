// Paquete de facturación de celulares
package FacturacionCelulares;

// Importación de librerías
import FacturacionException.CuentaExc;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * Clase Cuenta
 */
public abstract class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private long numero;
    private List<Llamada> llamadas;

    /**
     * Constructor de la clase Cuenta
     * @param id
     * @param numero
     */
    public Cuenta(long id, long numero) throws CuentaExc {
        if (id < 0){
            throw new CuentaExc("El id de la cuenta no puede ser negativo");
        }
        if (numero < 0){
            throw new CuentaExc("El número de la cuenta no puede ser negativo");
        }
        this.id = id;
        this.numero = numero;
        llamadas = new ArrayList<Llamada>();
    }

    /**
     * Metodo abstracto para obtener el pago de la cuenta
     * @param fecha Fecha para obtener el valor mensual
     */
    public abstract long obtenerPagoCuenta(LocalDate fecha);

    /**
     * Metodo para devolver la lista de llamadas
     * @return
     */
    public List<Llamada> getLlamadas() {
        return llamadas;
    }

    /**
     * Metodo para modificar la lista de llamadas
     * @param llamadas
     */
    public void setLlamadas(List<Llamada> llamadas) {
        this.llamadas = llamadas;
    }

    /**
     * Metodo para devolver el número de la cuenta
     * @return
     */
    public long getNumero() {
        return numero;
    }

    /**
     * Metodo para modificar el número de la cuenta
     * @param numero
     */
    public void setNumero(long numero) {
        this.numero = numero;
    }

    /**
     * Metodo para devolver el id de la cuenta
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Metodo para modificar el id de la cuenta
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Metodo para devolver una lista de llamadas de una fecha específica
     * @param fecha Fecha de las llamadas
     * @return Lista de llamadas de la fecha
     */
    public List<Llamada> getLlamadasFecha(LocalDate fecha) {
        List<Llamada> LlamadasFecha = new ArrayList<Llamada>();
        for (Llamada e : this.getLlamadas()){
            if (e.getFecha().getMonthValue() == fecha.getMonthValue() && e.getFecha().getYear() == fecha.getYear()) {
                if (e instanceof LlamadaInternacional) {
                    LlamadasFecha.add(e);
                }
            }
        }
        return LlamadasFecha;
    }
}
