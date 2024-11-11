package FacturacionCelulares;

import FacturacionException.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Interfaz que define los métodos que debe implementar la clase hija
 */
public interface IEmpresa {
    ArrayList<Cliente> getClientes();
    Cliente BuscarCliente(String identificacion);
    Cliente BuscarCliente(long numero);
    void IngresarCliente(Cliente cliente);
    void IngresarCliente(String direccion, String nombre, String identificacion, String tipoIdentificacion) throws ClienteExc;
    void AgregarCuenta(String identificacion, String tipoCuenta, long numero) throws EmpresaExc, ClienteExc, CuentaExc;
    void AgregarLlamadaNacional(String identificacion, long duracion, long telefonoDestinatario, LocalDate fecha) throws EmpresaExc, LlamadaExc;
    void AgregarLlamadaInternacional(String identificacion, long duracion, long telefonoDestinatario, String paisDestino, LocalDate fecha) throws LlamadaExc;
    void AgregarRecarga(LocalDate fecha, String identificacion, long valor) throws RecargaExc;
    String GenerarReportePostpago(LocalDate fecha, String identificacion) throws EmpresaExc;
    String GenerarReportePrepago(LocalDate fecha) throws EmpresaExc;
}
