// Paquete que contiene la clase
package FacturacionCelulares;

//Importación de librerias
import FacturacionException.LlamadaExc;

import java.time.LocalDate;

/**
 * Clase LlamadaInternacional que hereda de la clase Llamada
 */
public class LlamadaInternacional extends Llamada {
    private String paisDestino;

    /**
     * Constructor de la clase LlamadaInternacional
     *
     * @param duracion             Duración de la llamada
     * @param fecha                Fecha de la llamada
     * @param telefonoDestinatario Teléfono del destinatario
     * @param paisDestino          País de destino
     * @param cuenta                Cuenta del cliente
     * @throws LlamadaExc Excepción de la llamada
     */
    public LlamadaInternacional(long duracion, LocalDate fecha, long telefonoDestinatario, String paisDestino, Cuenta cuenta) throws LlamadaExc {
        super(duracion, fecha, telefonoDestinatario);
        if (paisDestino == null || paisDestino.isEmpty() || paisDestino.isBlank()) {
            throw new LlamadaExc("El país de destino no puede estar vacío");
        }
        this.paisDestino = paisDestino;
        super.setValor(calcularValor(cuenta));
    }

    /**
     * Metodo para obtener el país de destino
     * @return paisDestino
     */
    public String getPaisDestino() {
        return paisDestino;
    }

    /**
     * Metodo para establecer el país de destino
     * @param paisDestino País de destino
     */
    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    /**
     * Metodo para calcular el valor de la llamada
     * @param cuenta Cuenta del cliente
     * @return valor de la llamada
     */
    @Override
    public long calcularValor(Cuenta cuenta) throws LlamadaExc {
        if (cuenta instanceof Prepago) {
            return (long) (super.getDuracion() + super.getDuracion()*0.2);
        }
        if (cuenta instanceof Postpago) {
            return (long) (Math.ceil(((Postpago) cuenta).getCargoFijo()*0.20));
        }
        throw new LlamadaExc("Tipo de cuenta no válido");
    }

    @Override
    public String toString() {
        return "Llamada Internacional[ Fecha: " + super.getFecha() + ", Duración: " + super.getDuracion()
                + ", Telefono Destinatario: (" + paisDestino + ") " + super.getTelefonoDestinatario()
                + ", Valor: " + super.getValor() + "]\n";
    }
}
