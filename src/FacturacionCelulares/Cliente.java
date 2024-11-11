//Paquete que contiene la clase
package FacturacionCelulares;

//Importación de librerías
import FacturacionException.ClienteExc;
import java.io.Serializable;

/**
 * Clase Cliente
 */
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String direccion;
    private String identificacion;
    private String tipoId;
    private Cuenta cuenta;

    /**
     * Constructor de la clase Cliente
     * @param nombre nombre del cliente
     * @param direccion dirección del cliente
     * @param identificacion identificación del cliente
     * @param tipoId tipo de identificación del cliente
     * @throws ClienteExc excepción de cliente
     */
    public Cliente(String nombre, String direccion, String identificacion, String tipoId) throws ClienteExc {
        if (nombre == null || nombre.isEmpty()) {
            throw new ClienteExc("El nombre es invalido");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new ClienteExc("La dirección es invalida");
        }
        if (identificacion == null || identificacion.isEmpty()) {
            throw new ClienteExc("La identificación es invalida");
        }
        if (tipoId == null || tipoId.isEmpty()) {
            throw new ClienteExc("El tipo de identificación es invalido");
        }
        this.nombre = nombre;
        this.direccion = direccion;
        this.identificacion = identificacion;
        this.tipoId = tipoId;
    }

    /**
     * Metodo para devolver el nombre del cliente
     * @return nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para modificar el nombre del cliente
     * @param nombre nombre del cliente
     * @throws ClienteExc excepción de cliente
     */
    public void setNombre(String nombre) throws ClienteExc {
        if (nombre == null || nombre.isEmpty()) {
            throw new ClienteExc("El nombre es invalido");
        }
        this.nombre = nombre;
    }

    /**
     * Metodo para devolver la dirección del cliente
     * @return dirección del cliente
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo para modificar la dirección del cliente
     * @param direccion dirección del cliente
     * @throws ClienteExc excepción de cliente
     */
    public void setDireccion(String direccion) throws ClienteExc {
        if (direccion == null || direccion.isEmpty()) {
            throw new ClienteExc("La dirección es invalida");
        }
        this.direccion = direccion;
    }

    /**
     * Metodo para devolver la identificación del cliente
     * @return identificación del cliente
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Metodo para modificar la identificación del cliente
     * @param identificacion identificación del cliente
     * @throws ClienteExc excepción de cliente
     */
    public void setIdentificacion(String identificacion) throws ClienteExc {
        if (identificacion == null || identificacion.isEmpty()) {
            throw new ClienteExc("La identificación es invalida");
        }
        this.identificacion = identificacion;
    }

    /**
     * Metodo para devolver el tipo de identificación del cliente
     * @return tipo de identificación del cliente
     */
    public String getTipoId() {
        return tipoId;
    }

    /**
     * Metodo para modificar el tipo de identificación del cliente
     * @param tipoId tipo de identificación del cliente
     * @throws ClienteExc excepción de cliente
     */
    public void setTipoId(String tipoId) throws ClienteExc {
        if (tipoId == null || tipoId.isEmpty()) {
            throw new ClienteExc("El tipo de identificación es invalido");
        }
        this.tipoId = tipoId;
    }

    /**
     * Metodo para devolver la cuenta del cliente
     * @return cuenta del cliente
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * Metodo para modificar la cuenta del cliente
     * @param cuenta cuenta del cliente
     * @throws ClienteExc excepción de cliente
     */
    public void setCuenta(Cuenta cuenta) throws ClienteExc {
        if (cuenta == null) {
            throw new ClienteExc("La cuenta es invalida");
        }
        this.cuenta = cuenta;
    }



    /**
     * Sobreescritura del metodo toString heredado
     * @return String con la información del cliente
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", tipoId='" + tipoId + '\'' +
                ", cuenta=" + cuenta +
                '}';
    }
}
