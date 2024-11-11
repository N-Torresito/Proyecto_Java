//Paquete de la clase
package FacturacionCelulares;

//Importacion de librerias
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase Utils para manejar metodos de utilidad
 */
public class Utils {
    private static long CONSECUTIVO = 1;

    /**
     * Metodo para obtener el consecutivo y aumentarlo en 1 posteriormente
     * @return consecutivo valor del consecutivo actual
     */
    public static long getCONSECUTIVO() {
        return CONSECUTIVO++;
    }

    /**
     * Metodo para setear el consecutivo
     * @param consecutivo valor maximo del consecutivo
     */
    public static void setCONSECUTIVO(long consecutivo) {
         CONSECUTIVO = consecutivo;
    }

    /**
     * Metodo para convertir una fecha en string a LocalDate
     * @param fecha fecha en string
     * @return fecha en LocalDate
     */
    public static LocalDate convertirStringFecha(String fecha) {
        return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Metodo para convertir una fecha en LocalDate a string
     * @param fecha fecha en LocalDate
     * @return fecha en string
     */
    public static String convertirFechaString(LocalDate fecha) {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
