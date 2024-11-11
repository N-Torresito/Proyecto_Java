package FacturacionCelulares;

//
import FacturacionException.*;


import java.time.LocalDate;
import java.util.*;

public class TestConsola {
    public static IEmpresa empresa = new Empresa("PontiEmpresa");

    public static void main (String[] Args) throws ClienteExc {
        Scanner scanner = new Scanner(System.in);
        int option = 9;
        try {
            do {
                System.out.println("Menú:");
                System.out.println("1. Ingresar clientes");
                System.out.println("2. Agregar nueva cuenta de prepago o postpago");
                System.out.println("3. Agregar una llamada nacional o internacional");
                System.out.println("4. Agregar una recarga");
                System.out.println("5. Reporte de facturación postpago a fin de mes");
                System.out.println("6. Reporte de recargas a fin de mes");
                System.out.println("7. Guardar la empresa en un archivo como un objeto");
                System.out.println("8. Cargar de un archivo el objeto empresa");
                System.out.println("9. Salir");
                System.out.print("Seleccione una opción: ");
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        System.out.println("---- Ingresar clientes ----");
                        System.out.println("1. Seleccionar archivo de clientes");
                        System.out.println("2. Ingresar cliente manualmente");
                        System.out.println("3. Regresar al menú principal");
                        System.out.print("Seleccione una opción: ");

                        switch (Integer.parseInt(scanner.nextLine())) {
                            case 1:
                                System.out.println("Seleccionar archivo de clientes");
                                System.out.println("Ingrese la ruta del archivo de clientes");
                                String ruta = scanner.nextLine();
                                for (Cliente e : ManejoArchivos.cargarClientes(ruta)) {
                                    empresa.IngresarCliente(e);
                                }
                                System.out.println("Clientes ingresados correctamente");
                                break;
                            case 2:
                                System.out.println("Ingresar cliente manualmente");
                                System.out.print("Ingrese el nombre del cliente: ");
                                String nombre = scanner.nextLine();
                                System.out.print("Ingrese la dirección del cliente: ");
                                String direccion = scanner.nextLine();
                                System.out.print("Ingrese la identificación del cliente: ");
                                String identificacion = scanner.nextLine();
                                System.out.print("Ingrese el tipo de identificación: ");
                                String tipo = scanner.nextLine();
                                empresa.IngresarCliente(direccion, nombre, identificacion, tipo);
                                break;
                            case 3:
                                System.out.println("Regresar al menú principal");
                                break;
                            default:
                                System.out.println("Opción no válida. Intente de nuevo.");
                        }
                        break;
                    case 2:
                        System.out.println("Agregar nueva cuenta de prepago o postpago");
                        System.out.println("1. Agregar cuenta de prepago");
                        System.out.println("2. Agregar cuenta de postpago");
                        System.out.println("3. Regresar al menú principal");
                        System.out.print("Seleccione una opción: ");
                        int selec = Integer.parseInt(scanner.nextLine());
                        if (selec == 1) {
                            System.out.println("Agregar cuenta de prepago");
                            System.out.print("Ingrese el número de identificación del cliente: ");
                            String identificacion = scanner.nextLine();
                            System.out.print("Ingrese el número de telefono de la cuenta: ");
                            long cuenta = Long.parseLong(scanner.nextLine());
                            empresa.AgregarCuenta(identificacion, "prepago", cuenta, Utils.getCONSECUTIVO());
                        } else if (selec == 2) {
                            System.out.println("Agregar cuenta de prepago");
                            System.out.print("Ingrese el número de identificación del cliente: ");
                            String identificacion = scanner.nextLine();
                            System.out.print("Ingrese el número de telefono de la cuenta: ");
                            long cuenta = Long.parseLong(scanner.nextLine());
                            empresa.AgregarCuenta(identificacion, "postpago", cuenta, Utils.getCONSECUTIVO());
                        } else {
                            System.out.println("Regresar al menú principal");
                        }
                        break;
                    case 3:
                        System.out.println("Agregar una llamada nacional o internacional");
                        System.out.println("1. Agregar llamada nacional");
                        System.out.println("2. Agregar llamada internacional");
                        System.out.println("3. Regresar al menú principal");
                        System.out.print("Seleccione una opción: ");
                        selec = Integer.parseInt(scanner.nextLine());
                        if (selec == 1) {
                            System.out.println("Agregar llamada nacional");
                            System.out.print("Ingrese el número de identificación del cliente: ");
                            String identificacion = scanner.nextLine();
                            System.out.print("Ingrese el número de telefono de destino: ");
                            long cuenta = Long.parseLong(scanner.nextLine());
                            System.out.print("Ingrese la duración de la llamada: ");
                            int duracion = Integer.parseInt(scanner.nextLine());
                            System.out.println("Ingrese la fecha de la llamada con formato dd/MM/yyyy");
                            LocalDate fecha = Utils.convertirStringFecha(scanner.nextLine());
                            empresa.AgregarLlamadaNacional(identificacion, duracion, cuenta, fecha);
                        } else if (selec == 2) {
                            System.out.println("Agregar llamada Internacional");
                            System.out.print("Ingrese el número de identificación del cliente: ");
                            String identificacion = scanner.nextLine();
                            System.out.print("Ingrese el número de identificación del pais de destino: ");
                            String pais = scanner.nextLine();
                            System.out.print("Ingrese el número de telefono de destino: ");
                            long cuenta = Long.parseLong(scanner.nextLine());
                            System.out.print("Ingrese la duración de la llamada: ");
                            int duracion = Integer.parseInt(scanner.nextLine());
                            System.out.println("Ingrese la fecha de la llamada con formato dd/MM/yyyy");
                            LocalDate fecha = Utils.convertirStringFecha(scanner.nextLine());
                            empresa.AgregarLlamadaInternacional(identificacion, duracion, cuenta, pais, fecha);
                        }
                        break;
                    case 4:
                        System.out.println("Agregar una recarga");
                        System.out.print("Ingrese el número de identificación del cliente: ");
                        String identificacion = scanner.nextLine();
                        System.out.println("Ingrese la fecha de la llamada con formato dd/MM/yyyy");
                        LocalDate fecha = Utils.convertirStringFecha(scanner.nextLine());
                        System.out.println("Ingrese el valor de la recarga en minutos");
                        long valor = Long.parseLong(scanner.nextLine());
                        empresa.AgregarRecarga(fecha, identificacion, valor);
                        break;
                    case 5:
                        System.out.println("Reporte de facturación postpago a fin de mes");
                        System.out.println("Ingrese la fecha para el reporte con formato dd/MM/yyyy");
                        fecha = Utils.convertirStringFecha(scanner.nextLine());
                        System.out.println("Ingrese la identificación del cliente");
                        identificacion = scanner.nextLine();
                        System.out.println(empresa.GenerarReportePostpago(fecha, identificacion));
                        break;
                    case 6:
                        System.out.println("Reporte de recargas a fin de mes");
                        System.out.println("Ingrese la fecha para el reporte con formato dd/MM/yyyy");
                        fecha = Utils.convertirStringFecha(scanner.nextLine());
                        System.out.println(empresa.GenerarReportePrepago(fecha));
                        break;
                    case 7:
                        System.out.println("Guardar la empresa en un archivo como un objeto");
                        System.out.println("Ingrese la ruta del archivo de clientes");
                        String ruta = scanner.nextLine();
                        ManejoArchivos.guardarObjeto((Empresa) empresa, ruta);
                        break;
                    case 8:
                        System.out.println("Cargar de un archivo el objeto empresa");
                        System.out.println("Ingrese la ruta del archivo de clientes");
                        ruta = scanner.nextLine();
                        empresa = ManejoArchivos.cargarObjeto(ruta);
                        break;
                    case 9:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (option != 9);

            scanner.close();
        } catch (CuentaExc | ClienteExc | EmpresaExc | RecargaExc | LlamadaExc e) {
            System.out.println(e);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
