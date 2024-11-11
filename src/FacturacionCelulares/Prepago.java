package FacturacionCelulares;

import FacturacionException.CuentaExc;
import FacturacionException.RecargaExc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Prepago que hereda de la clase Cuenta
 */
public class Prepago extends Cuenta {
    private static final long serialVersionUID = 1L;
    private long numeroMinutos;
    private List<Recarga> recargas;

    /**
     * Constructor de la clase SetMinutos con numero de minutos por defecto (5 minutos)
     */
    public Prepago(long id, long numero, String tipo) throws CuentaExc {
        super(id, numero);
        this.numeroMinutos = 5;
        recargas = new ArrayList<Recarga>();
    }

    /**
     * Metodo para devolver la cantidad de minutos
     * @return cantidad de minutos
     */
    public long getNumeroMinutos() {
        return numeroMinutos;
    }

    /**
     * Metodo para modificar la cantidad de minutos
     * @param numeroMinutos cantidad de minutos
     */
    public void setNumeroMinutos(long numeroMinutos) {
        this.numeroMinutos = numeroMinutos;
    }

    /**
     * Metodo para modificar la cantidad de minutos con fecha
     * @param fecha fecha de la recarga
     * @param numeroMinutos cantidad de minutos
     */
    public void recargaConFecha(LocalDate fecha, long numeroMinutos) throws RecargaExc {
        this.numeroMinutos += numeroMinutos;
        recargas.add(new Recarga(fecha,numeroMinutos));
    }

    /**
     * Metodo para realizar una recarga en la fecha actual
     * @param numeroMinutos cantidad de minutos
     */
    public void Recarga(long numeroMinutos) throws RecargaExc {
        this.numeroMinutos += numeroMinutos;
        recargas.add(new Recarga(LocalDate.now(),numeroMinutos));
    }

    /**
     * Metodo para devolver la lista de recargas
     * @return lista de recargas
     */
    public List<Recarga> getRecargas() {
        return recargas;
    }

    /**
     * Metodo para devolver la lista de recargas
     * @param recargas lista de recargas
     */
    public void setRecargas(List<Recarga> recargas) {
        this.recargas = recargas;
    }

    /**
     * Metodo para devolver la información de la recarga
     * @return String con la información de la recarga
     */
    @Override
    public String toString() {
        return "Prepago{" +
                "Número minutos=" + numeroMinutos +
                ",\n recargas:\n" + recargas +
                '}';
    }

    /**
     * Metodo para obtener el pago de la cuenta
     * @param fecha fecha de la recarga
     * @return total
     */
    @Override
    public long obtenerPagoCuenta(LocalDate fecha) {
        long total = 0;
        for (Recarga e : this.getRecargas()){
            if (e.getFecha().getMonthValue() == fecha.getMonthValue() && e.getFecha().getYear() == fecha.getYear()) {
                total += e.getValor();
            }
        }
        return total;
    }

    /**
     * Metodo para la lista de recargas en una fecha
     * @param fecha fecha de la recarga
     * @return lista de recargas
     */
    public List<Recarga> getRecargasFecha(LocalDate fecha) {
        List<Recarga> recargasFecha = new ArrayList<Recarga>();
        for (Recarga e : this.getRecargas()){
            if (e.getFecha().getMonthValue() == fecha.getMonthValue() && e.getFecha().getYear() == fecha.getYear()) {
                recargasFecha.add(e);
            }
        }
        return recargasFecha;
    }
}

