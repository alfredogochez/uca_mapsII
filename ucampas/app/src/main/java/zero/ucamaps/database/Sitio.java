package zero.ucamaps.database;

/**
 * Created by alf on 14/06/2016.
 */
public class Sitio {

    private String idSitioEdificio;
    private String nombre;
    private String categoria;
    private String descripcion;
    private String ubicacion;
    private String idEdificio;

    public Sitio(){
        }

    public String getIdSitioEdificio() {
        return idSitioEdificio;
    }

    public void setIdSitioEdificio(String idSitioEdificio) {
        this.idSitioEdificio = idSitioEdificio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(String idEdificio) {
        this.idEdificio = idEdificio;
    }
}
