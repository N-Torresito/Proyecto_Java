package FacturacionCelulares;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prepago implements Serializable {
    private long SerialVersionUID = 1L;
    public long numeroMinutos;
    public List<Recarga> recargas;

    /**
     * Constructor de la clase Prepago con parametros
     * @param numeroMinutos - Cantidad de minutos
     */
    public Prepago(long numeroMinutos) {
        this.numeroMinutos = numeroMinutos;
        recargas = new ArrayList<>();
    }

    /**
     * Método para devolver la cantidad de minutos
     * @return cantidad de minutos
     */
    public long getNumeroMinutos() {
        return numeroMinutos;
    }

    /**
     * Método para modificar la cantidad de minutos
     * @param numeroMinutos
     */
    public void setNumeroMinutos(long numeroMinutos) {
        this.numeroMinutos = numeroMinutos;
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
}

