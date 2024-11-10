/**
 * Paquete para el manejo de excepciones
 */
package FacturacionException;

/**
 * Excepción para el manejo de errores en las recargas
 */
public class RecargaExc extends Exception {
    /**
     * Constructor de la excepción
     * @param message - Mensaje de error
     */
    public RecargaExc(String message) {
        super(message);
    }
}