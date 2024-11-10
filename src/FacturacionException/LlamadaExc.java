/**
 * Paquete para el manejo de excepciones
 */
package FacturacionException;

/**
 * Excepción para el manejo de errores en la clase Llamada
 */
public class LlamadaExc extends Exception {
    /**
     * Constructor de la excepción
     * @param message - Mensaje de error
     */
    public LlamadaExc(String message) {
        super(message);
    }
}