package FacturacionCelulares;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prepago implements Serializable {
    private static final long serialVersionUID = 1L;
    private long numeroMinutos;
    private List<Recarga> recargas;

    /**
     * Constructor de la clase SetMinutos
     */
    public void SetMinutos() {
        this.numeroMinutos = 5;
        recargas = new ArrayList<Recarga>();
    }

    /**
     * Método para devolver la cantidad de minutos
     * @return cantidad de minutos
     */
    public long getNumeroMinutos() {
        return numeroMinutos;
    }

    /**
     * Método para modificar la cantidad de minutos con fecha
     * @param fecha
     * @param numeroMinutos
     */
    public void recargaConFecha(LocalDate fecha, long numeroMinutos) {
        this.numeroMinutos += numeroMinutos;
        recargas.add(new Recarga(fecha,numeroMinutos));
    }

    /**
     * Método para realizar una recarga
     * @param numeroMinutos
     */
    public void Recarga(long numeroMinutos) {
        this.numeroMinutos += numeroMinutos;
        recargas.add(new Recarga(LocalDate.now(),numeroMinutos));
    }

    /**
     * Método para devolver la lista de recargas
     * @return lista de recargas
     */
    public List<Recarga> getRecargas() {
        return recargas;
    }

    /**
     * Método para modificar la lista de recargas
     * @param recargas
     */
    public void setRecargas(List<Recarga> recargas) {
        this.recargas = recargas;
    }

    /**
     * Método para devolver la información de la recarga
     * @return String
     */
    @Override
    public String toString() {
        return "Prepago{" +
                "Número minutos=" + numeroMinutos +
                ",\n recargas:\n" + recargas +
                '}';
    }
}

