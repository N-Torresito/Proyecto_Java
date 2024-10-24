package FacturacionCelulares;

import java.io.Serializable;

public class Postpago implements Serializable {
    private long SerialVersionUID = 1L;
    private long cargoFijo;

    public Postpago(long cargoFijo) {
        this.cargoFijo = cargoFijo;
    }

    public long getCargoFijo() {
        return cargoFijo;
    }

    public void setCargoFijo(long cargoFijo) {
        this.cargoFijo = cargoFijo;
    }

    @Override
    public String toString() {
        return "Postpago{" + "cargoFijo=" + cargoFijo + '}';
    }
}
