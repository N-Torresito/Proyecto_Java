// Paquete de la clase
package FacturacionCelulares;

// Importacion de librerias
import FacturacionException.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * Clase Empresa
 */
public class Empresa implements IEmpresa, Serializable {
    private long SerialVersionUID = 1L;
    private String nombre;
    private List<Cliente> clientes;
    private List<Cuenta> cuentas;

    /**
     * Constructor de la clase Empresa
     * @param nombre Nombre de la empresa
     */
    public Empresa(String nombre) {
        this.nombre = nombre;
        clientes = new ArrayList<Cliente>();
        cuentas = new ArrayList<Cuenta>();
    }

    /**
     * Obtiene la lista de clientes
     * @return ArrayList de clientes
     */
    @Override
    public ArrayList<Cliente> getClientes() {
        return (ArrayList<Cliente>) clientes;
    }

    /**
     * Busca un cliente por su identificación
     * @param identificacion Identificación del cliente
     * @return Cliente
     */
    @Override
    public Cliente BuscarCliente(String identificacion) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion().equals(identificacion)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Busca un cliente por su número de cuenta
     * @param numero Número de la cuenta
     * @return Cliente
     */
    @Override
    public Cliente BuscarCliente(long numero) {
        for (Cliente cliente : clientes) {
            if (cliente.getCuenta() != null){
                if (cliente.getCuenta().getNumero() == numero) {
                    return cliente;
                }
            }
        }
        return null;
    }

    /**
     * Ingresa un cliente a la empresa
     * @param cliente Cliente a ingresar
     */
    @Override
    public void IngresarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    /**
     * Ingresa un cliente a la empresa
     * @param direccion Dirección del cliente
     * @param nombre Nombre del cliente
     * @param identificacion Identificación del cliente
     * @param tipoIdentificacion Tipo de identificación del cliente
     * @throws ClienteExc
     */
    @Override
    public void IngresarCliente(String direccion, String nombre, String identificacion, String tipoIdentificacion) throws ClienteExc {
        clientes.add(new Cliente(direccion, nombre, identificacion, tipoIdentificacion));
    }

    /**
     * Agrega una cuenta a un cliente
     * @param identificacion Identificación del cliente
     * @param tipoCuenta Tipo de cuenta
     * @param numero Número de la cuenta
     * @throws EmpresaExc
     * @throws ClienteExc
     * @throws CuentaExc
     */
    @Override
    public void AgregarCuenta(String identificacion, String tipoCuenta, long numero) throws EmpresaExc, ClienteExc, CuentaExc {
        Cuenta cuenta = null;
        if (tipoCuenta.equalsIgnoreCase("prepago")) {
            cuenta = new Prepago(Utils.getCONSECUTIVO(), numero, tipoCuenta);
        }
        if (tipoCuenta.equalsIgnoreCase("postpago")) {
            cuenta = new Postpago(Utils.getCONSECUTIVO(), numero, tipoCuenta);
        }
        if (BuscarCliente(identificacion) != null && BuscarCliente(numero) == null) {
            BuscarCliente(identificacion).setCuenta(cuenta);
            cuentas.add(cuenta);
        } else {
            throw new EmpresaExc("No se pudo agregar la cuenta, cliente inexistente o numero de telefono ya existe");
        }
    }

    /**
     * Agrega una llamada nacional a la cuenta de un cliente
     * @param identificacion Identificación del cliente
     * @param duracion Duración de la llamada
     * @param telefonoDestinatario Teléfono del destinatario
     * @param fecha Fecha de la llamada
     * @throws EmpresaExc
     * @throws LlamadaExc
     */
    @Override
    public void AgregarLlamadaNacional(String identificacion, long duracion, long telefonoDestinatario, LocalDate fecha) throws EmpresaExc, LlamadaExc {
        Cuenta cuenta = BuscarCliente(identificacion).getCuenta();
        if (cuenta != null) {
            if (cuenta instanceof Prepago) {
                if (((Prepago) cuenta).getNumeroMinutos() >= duracion) {
                    ((Prepago) cuenta).setNumeroMinutos(((Prepago) cuenta).getNumeroMinutos() - duracion);
                    ((Prepago) cuenta).getLlamadas().add(new LlamadaNacional(duracion, fecha, telefonoDestinatario, cuenta));
                } else {
                    throw new EmpresaExc("No tiene suficientes minutos");
                }
            }
            if (cuenta instanceof Postpago) {
                ((Postpago) cuenta).getLlamadas().add(new LlamadaNacional(duracion, fecha, telefonoDestinatario, cuenta));
            }
        } else {
            throw new EmpresaExc("El usuario no existe");
        }
    }

    /**
     * Agrega una llamada internacional a la cuenta de un cliente
     * @param identificacion Identificación del cliente
     * @param duracion Duración de la llamada
     * @param telefonoDestinatario Teléfono del destinatario
     * @param paisDestino País de destino
     * @param fecha Fecha de la llamada
     * @throws LlamadaExc
     */
    @Override
    public void AgregarLlamadaInternacional(String identificacion, long duracion, long telefonoDestinatario, String paisDestino, LocalDate fecha) throws LlamadaExc {
        Cuenta cuenta = BuscarCliente(identificacion).getCuenta();
        LlamadaInternacional llamada = new LlamadaInternacional(duracion, fecha, telefonoDestinatario, paisDestino, cuenta);
        if (cuenta != null) {
            if (cuenta instanceof Prepago) {
                if (((Prepago) cuenta).getNumeroMinutos() >= llamada.calcularValor(cuenta)) {
                    ((Prepago) cuenta).setNumeroMinutos(((Prepago) cuenta).getNumeroMinutos() - duracion);
                    ((Prepago) cuenta).getLlamadas().add(llamada);
                } else {
                    throw new LlamadaExc("No tiene suficientes minutos");
                }
            }
            if (cuenta instanceof Postpago) {
                ((Postpago) cuenta).getLlamadas().add(llamada);
            }
        }
    }

    /**
     * Agrega una recarga a la cuenta de un cliente
     * @param fecha Fecha de la recarga
     * @param identificacion Identificación del cliente
     * @param valor Valor de la recarga
     * @throws RecargaExc
     */
    @Override
    public void AgregarRecarga(LocalDate fecha, String identificacion, long valor) throws RecargaExc {
        Cuenta cuenta = BuscarCliente(identificacion).getCuenta();
        if (cuenta instanceof Prepago) {
            ((Prepago) cuenta).recargaConFecha(fecha, valor);
        } else {
            throw new RecargaExc("La cuenta no es de tipo prepago");
        }
    }

    /**
     * Genera un reporte de facturación para los clientes de tipo postpago
     * @param fecha Fecha de la que se desea generar el reporte
     * @param identificacion Identificación del cliente
     * @return String con el reporte de facturación
     * @throws EmpresaExc
     */
    @Override
    public String GenerarReportePostpago(LocalDate fecha, String identificacion) throws EmpresaExc {
        StringBuilder reporte = new StringBuilder();
        if (identificacion.isBlank() || identificacion.isEmpty()) {
            throw new EmpresaExc("La identificación invalida");
        }
        if (BuscarCliente(identificacion) != null && BuscarCliente(identificacion).getCuenta() instanceof Postpago) {
            Cliente cliente = BuscarCliente(identificacion);
            List<Llamada> cuenta = cliente.getCuenta().getLlamadasFecha(fecha);


            reporte.append("Reporte de facturación postpago a fin de mes\n");
            reporte.append("Cliente: ").append(cliente.getNombre()).append("\n");
            reporte.append("Identificación: ").append(cliente.getIdentificacion()).append("\n");
            reporte.append("Dirección: ").append(cliente.getDireccion()).append("\n");
            reporte.append("Cuenta: ").append(cliente.getCuenta().getNumero()).append("\n");
            reporte.append("Tipo de cuenta: ").append(cliente.getCuenta().getClass()).append("\n");

            for (Llamada e : cuenta) {
                reporte.append(e.toString()).append("\n");
            }

            long total = 0;

            for (Llamada e : cuenta) {
                total += e.getDuracion();
            }
            reporte.append("Duración total es: ").append(total).append("\n");
            total = 0;
            for (Llamada e : cuenta) {
                total += e.getValor();
            }
            reporte.append("Valor total es: ").append(total).append("\n");
            return reporte.toString();
        } else {
            throw new EmpresaExc("El cliente no existe o la cuenta es de tipo prepago");
        }

    }

    /**
     * Genera un reporte de facturación para los clientes de tipo prepago
     * @param fecha Fecha de la que se desea generar el reporte
     * @return String con el reporte de facturación
     * @throws EmpresaExc
     */
    @Override
    public String GenerarReportePrepago(LocalDate fecha) throws EmpresaExc {
        StringBuilder reporte = new StringBuilder();
        clientes.sort(Comparator.comparing(Cliente::getIdentificacion));
        if (!clientes.isEmpty()){
            for (Cliente cliente : clientes) {
                if (cliente.getCuenta() instanceof Prepago) {
                    List<Llamada> cuenta = cliente.getCuenta().getLlamadasFecha(fecha);

                    reporte.append("Reporte de facturación prepago a fin de mes\n");
                    reporte.append("Cliente: ").append(cliente.getNombre()).append("\n");
                    reporte.append("Identificación: ").append(cliente.getIdentificacion()).append("\n");
                    reporte.append("Dirección: ").append(cliente.getDireccion()).append("\n");
                    reporte.append("Cuenta: ").append(cliente.getCuenta().getNumero()).append("\n");
                    reporte.append("Tipo de cuenta: ").append(cliente.getCuenta().getClass()).append("\n");
                    reporte.append("Minutos restantes: ").append(((Prepago) cliente.getCuenta()).getNumeroMinutos()).append("\n");

                    for (Recarga e : ((Prepago) cliente.getCuenta()).getRecargasFecha(fecha)) {
                        reporte.append(e.toString()).append("\n");
                    }

                    long total = 0;

                    for (Llamada e : cuenta) {
                        total += e.getDuracion();
                    }
                    reporte.append("Duración total es: ").append(total).append("\n");
                    total = 0;

                    reporte.append("Valor total es: ").append(cliente.getCuenta().obtenerPagoCuenta(fecha)).append("\n\n");
                }
            }
        }
        return reporte.toString();
    }
}
