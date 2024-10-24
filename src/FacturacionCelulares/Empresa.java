package FacturacionCelulares;

import java.io.Serializable;
import java.util.*;

public class Empresa implements Serializable {
    private long SerialVersionUID = 1L;
    private String nombre;
    private List<Cliente> clientes;

    public Empresa(String nombre) {
        this.nombre = nombre;
        clientes = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

}
