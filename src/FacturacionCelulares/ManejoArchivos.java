package FacturacionCelulares;

import FacturacionException.ClienteExc;

import java.io.*;
import java.util.*;
import java.util.Scanner;


public class ManejoArchivos {

    public static void guardarObjeto(Empresa objeto, String ruta) {
        try {
            OutputStream archivo = new FileOutputStream(ruta);
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);
            escritor.writeObject(objeto);
            escritor.close();
        }catch (IOException e) {
            System.out.println("No se pudo crear el archivo");
        } catch (Exception e) {
            System.out.println("Error al guardar el objeto");
        }
    }

    public static Empresa cargarObjeto(String ruta) {
        try {
            InputStream archivo = new FileInputStream(ruta);
            ObjectInputStream lector = new ObjectInputStream(archivo);
            Empresa objeto = (Empresa) lector.readObject();
            lector.close();
            return objeto;
        }catch (IOException e) {
            System.out.println("No se pudo encontrar el archivo");
            return null;
        } catch (Exception e) {
            System.out.println("Error al cargar el objeto");
            return null;
        }
    }

    public static List<Cliente> cargarClientes(String ruta) throws ClienteExc {
        List<Cliente> clientes = new ArrayList<Cliente>();
        List<Cliente> listtemp = new ArrayList<Cliente>();
        try {
            FileInputStream archivo = new FileInputStream(ruta);
            Scanner sc = new Scanner(archivo);
            String temp = sc.nextLine();
            while (!temp.contentEquals("#FIN")) {
                if (!temp.contains("#")) {
                    String[] datos = temp.split("\\*");
                    String nombre = datos[0].trim();
                    String direccion = datos[2].trim();
                    String identificacion = datos[1].trim();
                    String tipoId = "Cédula";
                    Cliente cliente = new Cliente(nombre, direccion, identificacion, tipoId);
                    if (clientes.isEmpty()) {
                        clientes.add(cliente);
                    }
                    boolean existe = false;
                    for (Cliente c : clientes) {
                        if (c.getIdentificacion().equals(cliente.getIdentificacion())) {
                            existe = true;
                        }
                    }
                    if (!existe) {
                        clientes.add(cliente);
                    }
                }
                if (temp.contains("#FIN")) {
                    break;
                }
                temp = sc.nextLine().trim();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
}