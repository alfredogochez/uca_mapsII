package zero.ucamaps.beans;

/**
 * Created by Uguizu on 2/5/2016.
 */
public class FavoriteRoute {

    private String name;
    private double longitud;
    private double latitud;

    public double getLatitud() {
        return latitud;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public String returnLine(){

        String linea = name + "_" + latitud + "_" + longitud ;

        return linea;
    }
}
