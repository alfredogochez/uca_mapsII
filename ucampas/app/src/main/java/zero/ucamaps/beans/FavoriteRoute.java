package zero.ucamaps.beans;

import java.io.Serializable;

/**
 * Created by Uguizu on 2/5/2016.
 */
public class FavoriteRoute implements Serializable {

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

    public FavoriteRoute(String name,double longitud,double latitud){
        this.setName(name);
        this.setLongitud(longitud);
        this.setLatitud(latitud);
    }

    public FavoriteRoute(){

    }

    public String returnLine(){

        String linea = name + "_" + latitud + "_" + longitud ;

        return linea;
    }
}
