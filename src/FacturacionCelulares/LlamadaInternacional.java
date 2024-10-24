package FacturacionCelulares;

import java.io.Serializable;

public class LlamadaInternacional implements Serializable {
    private long SerialVersionUID = 1L;
    private String paisDestino;

    public LlamadaInternacional(String paisDestino) {
        //TODO - Revisar Constructor
        this.paisDestino = paisDestino;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public long CalcularValor(){
        return 0;
        //TODO - Crear método para calcular el valor de la llamada internacional

    }

}
