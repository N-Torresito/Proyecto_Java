package FacturacionCelulares;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Empresa implements IEmpresa, Serializable {
    private long SerialVersionUID = 1L;
    private String nombre;
    private List<Cliente> clientes;
    private List<Cuenta> cuentas;

    public Empresa(String nombre) {
        this.nombre = nombre;
        clientes = new ArrayList<Cliente>();
        cuentas = new ArrayList<Cuenta>();
    }

    @Override
    public ArrayList<Cliente> getClientes() {
        return (ArrayList<Cliente>) clientes;
    }

    @Override
    public Cliente BuscarCliente(String identificacion) {
        //TODO - Crear método
        return null;
    }

    @Override
    public void IngresarCliente(Cliente cliente) {
        //TODO - Crear método
    }

    @Override
    public void IngresarCliente(String direccion, String nombre, String identificacion, String tipoIdentificacion) {
        //TODO - Crear método
    }

    @Override
    public void AgregarCuenta(String identificacion, String tipoCuenta) {
        //TODO - Crear método
    }

    @Override
    public void AgregarLlamadaNacional(String identificacion, long duracion, long telefonoDestinatario) {
        //TODO - Crear método
    }

    @Override
    public void AgregarLlamadaInternacional(String identificacion, long duracion, long telefonoDestinatario, String paisDestino) {
        //TODO - Crear método
    }

    @Override
    public void AgregarRecarga(String identificacion, long valor) {
        //TODO - Crear método
    }

    @Override
    public void GenerarReportePostpago() {
        //TODO - Crear método
    }

    @Override
    public void GenerarReportePrepago(LocalDate fecha) {
        //TODO - Crear método
    }


}
