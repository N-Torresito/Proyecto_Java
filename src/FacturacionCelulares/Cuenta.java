// Paquete de facturación de celulares
package FacturacionCelulares;

// Importación de librerías
import java.io.Serializable;
import java.util.*;

/**
 * Clase Cuenta
 */
public class Cuenta implements Serializable {
    private long SerialVersionUID = 1L;
    private long id;
    private long numero;
    private Prepago tipoPrepago = null;
    private Postpago tipoPostpago = null;
    private List<Llamada> llamadas;

    /**
     * Constructor de la clase Cuenta
     * @param id
     * @param numero
     * @param tipo
     */
    public Cuenta(long id, long numero, String tipo) {
        this.id = id;
        this.numero = numero;
        llamadas = new ArrayList<Llamada>();
        if (tipo.equals("Prepago")) {
            tipoPrepago = new Prepago();
        } else if (tipo.equals("Postpago")) {
            tipoPostpago = new Postpago();
        }
    }

    public long obtenerPagoCuenta(){
        return 1L;
        //TODO - Create method / Crear método
    }

    /**
     * Método para devolver la lista de llamadas
     * @return
     */
    public List<Llamada> getLlamadas() {
        return llamadas;
    }

    /**
     * Método para modificar la lista de llamadas
     * @param llamadas
     */
    public void setLlamadas(List<Llamada> llamadas) {
        this.llamadas = llamadas;
    }

    /**
     * Método para devolver el tipo de postpago
     * @return
     */
    public Postpago getTipoPostpago() {
        return tipoPostpago;
    }

    /**
     * Método para modificar el tipo de postpago
     * @param tipoPostpago
     */
    public void setTipoPostpago(Postpago tipoPostpago) {
        this.tipoPostpago = tipoPostpago;
    }

    /**
     * Método para devolver el tipo de prepago
     * @return
     */
    public Prepago getTipoPrepago() {
        return tipoPrepago;
    }

    /**
     * Método para modificar el tipo de prepago
     * @param tipoPrepago
     */
    public void setTipoPrepago(Prepago tipoPrepago) {
        this.tipoPrepago = tipoPrepago;
    }

    /**
     * Método para devolver el número de la cuenta
     * @return
     */
    public long getNumero() {
        return numero;
    }

    /**
     * Método para modificar el número de la cuenta
     * @param numero
     */
    public void setNumero(long numero) {
        this.numero = numero;
    }

    /**
     * Método para devolver el id de la cuenta
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Método para modificar el id de la cuenta
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }
}
