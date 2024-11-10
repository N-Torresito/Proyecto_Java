//Paquete que contiene la clase
package FacturacionCelulares;

//Importación de librerias
import java.time.LocalDate;
import FacturacionException.LlamadaExc;

/**
 * Clase LlamadaNacional que hereda de la clase Llamada
 */
public class LlamadaNacional extends Llamada{
    public LlamadaNacional (long duracion, LocalDate fecha, long telefonoDestinatario) throws LlamadaExc {
        super(duracion, fecha, telefonoDestinatario);
    }

    /**
     * Metodo para calcular el valor de la llamada
     * @param cuenta Cuenta del cliente
     * @return valor de la llamada
     */
    @Override
    public long calcularValor(Cuenta cuenta) throws LlamadaExc {
        if (cuenta instanceof Prepago) {
            return super.getDuracion();
        }
        if (cuenta instanceof Postpago) {
            return 0;
        }
        throw new LlamadaExc("Tipo de cuenta no válido");
    }

}
