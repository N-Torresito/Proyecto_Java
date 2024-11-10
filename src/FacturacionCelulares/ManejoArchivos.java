package FacturacionCelulares;

import java.io.*;


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

    //TODO - Carga y guardado de archivos serializados
    //TODO - Carga y guardado de archivos de texto
}
