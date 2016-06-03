package zero.ucamaps.database;

/**
 * Created by alf on 18/05/2016.
 */
public class RutaEspecial {

    /*
           Atributos
            */
    private String idRutaEspecial;
    private String nombre;
    private String descripcion;
    private String puntos;

    public RutaEspecial() {
    }

    public String getIdRutaEspecial() {
        return idRutaEspecial;
    }

    public void setIdRutaEspecial(String idRutaEspecial) {
        this.idRutaEspecial = idRutaEspecial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }
}
