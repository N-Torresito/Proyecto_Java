package FacturacionCelulares;

import FacturacionException.*;

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
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion().equals(identificacion)) {
                return cliente;
            }
        }
        return null;
    }

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


    @Override
    public void IngresarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public void IngresarCliente(String direccion, String nombre, String identificacion, String tipoIdentificacion) throws ClienteExc {
        clientes.add(new Cliente(direccion, nombre, identificacion, tipoIdentificacion));
    }

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

    @Override
    public void AgregarRecarga(LocalDate fecha, String identificacion, long valor) throws RecargaExc {
        Cuenta cuenta = BuscarCliente(identificacion).getCuenta();
        if (cuenta instanceof Prepago) {
            ((Prepago) cuenta).recargaConFecha(fecha, valor);
        } else {
            throw new RecargaExc("La cuenta no es de tipo prepago");
        }
    }

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
