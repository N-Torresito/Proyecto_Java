package FacturacionCelulares;

import FacturacionException.CuentaExc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Postpago que hereda de la clase Cuenta
 */
public class Postpago extends Cuenta {
    private static final long serialVersionUID = 1L;
    private long cargoFijo;

    /**
     * Constructor de la clase Postpago con cargo fijo predeterminado (Cargo fijo = $20,000)
     */
    public Postpago(long id, long numero, String tipo) throws CuentaExc {
        super(id, numero);
        this.cargoFijo = 20000;
    }

    /**
     * Metodo para devolver el cargo fijo
     * @return cargo fijo
     */
    public long getCargoFijo() {
        return cargoFijo;
    }

    /**
     * Metodo para modificar el cargo fijo
     * @param cargoFijo
     */
    public void setCargoFijo(long cargoFijo) {
        this.cargoFijo = cargoFijo;
    }

    /**
     * Sobreescritura del metodo toString heredado
     * @return
     */
    @Override
    public String toString() {
        return "Postpago{" + "Cargo fijo:" + cargoFijo + '}';
    }

    /**
     * Metodo para obtener el pago de la cuenta
     * @return total
     */
    @Override
    public long obtenerPagoCuenta(LocalDate fecha) {
        long total = 0;
        for (Llamada e : super.getLlamadas()){
            if (e.getFecha().getMonthValue() == fecha.getMonthValue() && e.getFecha().getYear() == fecha.getYear()) {
                total += e.getValor();
            }
        }
        return total;
    }

    /**
     * Metodo para obtener las llamadas de la cuenta
     * @return llamadas
     */
    public List<Llamada> getLlamadasFecha(LocalDate fecha) {
        List<Llamada> LlamadasFecha = new ArrayList<Llamada>();
        for (Llamada e : this.getLlamadas()){
            if (e.getFecha().getMonthValue() == fecha.getMonthValue() && e.getFecha().getYear() == fecha.getYear()) {
                if (e instanceof LlamadaInternacional) {
                    LlamadasFecha.add(e);
                }
            }
        }
        return LlamadasFecha;
    }
}
