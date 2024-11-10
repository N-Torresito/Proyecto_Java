package FacturacionCelulares;

//Importación de librerias
import java.io.Serializable;
import java.time.LocalDate;
import FacturacionException.*;

/**
 * Clase abstracta Llamada
 */
public abstract class Llamada implements Serializable {
    private static final long SerialVersionUID = 1L;
    private long duracion;
    private LocalDate fecha;
    private long telefonoDestinatario;
    private long valor;

    /**
     * Constructor de la clase Llamada
     * @param duracion
     * @param fecha
     * @param telefonoDestinatario
     * @throws LlamadaExc
     */
    public Llamada(long duracion, LocalDate fecha, long telefonoDestinatario) throws LlamadaExc {
        if (duracion <= 0) {
            throw new LlamadaExc("La duración de la llamada no puede ser menor o igual a 0");
        }
        if (fecha == null) {
            throw new LlamadaExc("La fecha es invalida");
        }
        if (telefonoDestinatario <= 0) {
            throw new LlamadaExc("El número de telefono del destinatario no puede ser menor o igual a 0");
        }
        this.duracion = duracion;
        this.fecha = fecha;
        this.telefonoDestinatario = telefonoDestinatario;
    }

    /**
     * Metodo abstracto para calcular el valor de la llamada
     *
     * @param cuenta
     * @return
     */
    public abstract long calcularValor(Cuenta cuenta) throws LlamadaExc;

    /**
     * Metodo para obtener la duración de la llamada
     * @return
     */
    public long getDuracion() {
        return duracion;
    }

    /**
     * Metodo para establecer la duración de la llamada
     * @param duracion
     */
    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }

    /**
     * Metodo para obtener la fecha de la llamada
     * @return
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Metodo para establecer la fecha de la llamada
     * @param fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Metodo para obtener el número de telefono del destinatario
     * @return
     */
    public long getTelefonoDestinatario() {
        return telefonoDestinatario;
    }

    /**
     * Metodo para establecer el número de telefono del destinatario
     * @param telefonoDestinatario
     */
    public void setTelefonoDestinatario(long telefonoDestinatario) {
        this.telefonoDestinatario = telefonoDestinatario;
    }

    /**
     * Metodo para obtener el valor de la llamada
     * @return - Valor de la llamada
     */
    public long getValor() {
        return valor;
    }

    /**
     * Metodo para establecer el valor de la llamada
     * @param valor - Valor de la llamada
     */
    public void setValor(long valor) {
        this.valor = valor;
    }
}
