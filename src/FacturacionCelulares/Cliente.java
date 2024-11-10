package FacturacionCelulares;

import javax.sql.rowset.serial.SerialException;
import java.io.Serializable;

public class Cliente implements Serializable {
    private long SerialVersionUID = 1L;
    private String nombre;
    private String direccion;
    private String identificacion;
    private String tipoId;
    private Cuenta cuenta;

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTipoId() {
        return tipoId;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
