package FacturacionCelulares;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IEmpresa {
    ArrayList<Cliente> getClientes();
    Cliente BuscarCliente(String identificacion);
    void IngresarCliente(Cliente cliente);
    void IngresarCliente(String direccion, String nombre, String identificacion, String tipoIdentificacion);
    void AgregarCuenta(String identificacion, String tipoCuenta);
    void AgregarLlamadaNacional(String identificacion, long duracion, long telefonoDestinatario);
    void AgregarLlamadaInternacional(String identificacion, long duracion, long telefonoDestinatario, String paisDestino);
    void AgregarRecarga(String identificacion, long valor);
    void GenerarReportePostpago();
    void GenerarReportePrepago(LocalDate fecha);
}
