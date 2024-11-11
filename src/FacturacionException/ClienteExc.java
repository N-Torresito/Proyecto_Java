/**
 * Paquete para el manejo de excepciones
 */
package FacturacionException;

/**
 * Excepción para el manejo de errores en la clase Cuenta
 */
public class ClienteExc extends Exception {
    /**
     * Constructor de la excepción
     * @param message - Mensaje de error
     */
    public ClienteExc(String message) {
        super(message);
    }
}