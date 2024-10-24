package FacturacionCelulares;

import java.io.Serializable;
import java.time.LocalDate;

public class Llamada implements Serializable {
    private long SerialVersionUID = 1L;
    private long duracion;
    private LocalDate fecha;
    private long telefonoDestinatario;
    private long valor;

    public long calcularValor(){
        return 0;
        //TODO - Crear método calcularValor / Create calculateValue method
    }

}
