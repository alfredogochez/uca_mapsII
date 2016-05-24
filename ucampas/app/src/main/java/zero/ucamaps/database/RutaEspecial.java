package zero.ucamaps.database;

/**
 * Created by alf on 18/05/2016.
 */
public class RutaEspecial {

    /*
           Atributos
            */
    private String idRUTAESPECIAL;
    private String NOMBRE;
    private String DESCRIPCION;
    private String PUNTOS;

    public RutaEspecial() {
    }

    public String getIdRUTAESPECIAL() {
        return idRUTAESPECIAL;
    }

    public void setIdRUTAESPECIAL(String idRUTAESPECIAL) {
        this.idRUTAESPECIAL = idRUTAESPECIAL;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getPUNTOS() {
        return PUNTOS;
    }

    public void setPUNTOS(String PUNTOS) {
        this.PUNTOS = PUNTOS;
    }
}
