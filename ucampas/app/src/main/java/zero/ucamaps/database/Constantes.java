package zero.ucamaps.database;

/**
 * Created by alf on 24/05/2016.
 */
public class Constantes {

    private static final String PUERTO_HOST = "80";

    /**
     * Dirección IP del servidor
     */
    private static final String IP = "http://192.241.247.212:";
    /**
     * URLs del Web Service
     */
    public static final String BASE = IP + PUERTO_HOST;
    public static final String GET = IP + PUERTO_HOST + "/ucamaps/get_rutas.php";
    public static final String INSERT = IP + PUERTO_HOST + "/ucamaps/insert_ruta.php";
    public static final String GET_BY_NOMBRE = IP + PUERTO_HOST + "/ucamaps/get_detalle.php";
    public static final String GET_SITIOS = IP + PUERTO_HOST + "/ucamaps/get_sitios.php";

}
