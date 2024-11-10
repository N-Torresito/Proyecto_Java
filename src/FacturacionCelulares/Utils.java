package FacturacionCelulares;

public class Utils {
    private static long CONSECUTIVO = 1;

    /**
     * Metodo para obtener el consecutivo y aumentarlo en 1 posteriormente
     * @return
     */
    public static long getCONSECUTIVO() {
        return CONSECUTIVO++;
    }

    /**
     * Metodo para setear el consecutivo
     * @param consecutivo
     * @return
     */
    public static long setCONSECUTIVO(long consecutivo) {
        return CONSECUTIVO = consecutivo;
    }
}
