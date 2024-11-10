package FacturacionCelulares;

import java.io.Serializable;

public class Postpago extends Cuenta {
    private long SerialVersionUID = 1L;
    private long cargoFijo;

    /**
     * Constructor de la clase Postpago con cargo fijo predeterminado (Cargo fijo = $20,000)
     */
    public Postpago(long id, long numero, String tipo) {
        super(id, numero, tipo);
        this.cargoFijo = 20000;
    }

    /**
     * Método para devolver el cargo fijo
     * @return cargo fijo
     */
    public long getCargoFijo() {
        return cargoFijo;
    }

    /**
     * Método para modificar el cargo fijo
     * @param cargoFijo
     */
    public void setCargoFijo(long cargoFijo) {
        this.cargoFijo = cargoFijo;
    }

    /**
     * Sobreescritura del método toString heredado
     * @return
     */
    @Override
    public String toString() {
        return "Postpago{" + "Cargo fijo:" + cargoFijo + '}';
    }

    @Override
    public long obtenerPagoCuenta() {
        //TODO - Crear método
        return 0;
    }
}
