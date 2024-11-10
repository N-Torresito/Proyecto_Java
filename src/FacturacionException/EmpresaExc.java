/**
 * Paquete para el manejo de excepciones
 */
package FacturacionException;

/**
 * Excepción para el manejo de errores en la clase Empresa
 */
public class EmpresaExc extends Exception {
    /**
     * Constructor de la excepción
     * @param message - Mensaje de error
     */
    public EmpresaExc(String message) {
        super(message);
    }
}