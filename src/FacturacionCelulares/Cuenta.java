package FacturacionCelulares;

import java.io.Serializable;
import java.util.*;

public class Cuenta implements Serializable {
    private long SerialVersionUID = 1L;
    private long id;
    private long numero;
    private List<Llamada> llamadas;

    public Cuenta(long id, long numero) {
        this.id = id;
        this.numero = numero;
        llamadas = new ArrayList<Llamada>();
    }
    public long obtenerPagoCuenta(){
        return 1L;
        //TODO - Create method / Crear método
    }

}
