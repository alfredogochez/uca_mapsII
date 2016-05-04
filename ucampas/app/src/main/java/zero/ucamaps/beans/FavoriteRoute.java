package zero.ucamaps.beans;

import java.io.Serializable;

/**
 * Created by Uguizu on 2/5/2016.
 */
public class FavoriteRoute implements Serializable {

    private String name;
    private double startlongitud;
    private double startlatitud;
    private double endLatitud;
    private double endLongitud;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartlongitud() {
        return startlongitud;
    }

    public void setStartlongitud(double startlongitud) {
        this.startlongitud = startlongitud;
    }

    public double getStartlatitud() {
        return startlatitud;
    }

    public void setStartlatitud(double startlatitud) {
        this.startlatitud = startlatitud;
    }

    public double getEndLatitud() {
        return endLatitud;
    }

    public void setEndLatitud(double endLatitud) {
        this.endLatitud = endLatitud;
    }

    public double getEndLongitud() {
        return endLongitud;
    }

    public void setEndLongitud(double endLongitud) {
        this.endLongitud = endLongitud;
    }

    public FavoriteRoute(String name, double startlatitud, double startlongitud, double endLatitud, double endLongitud){
        this.setName(name);
        this.setStartlatitud(startlatitud);
        this.setStartlongitud(startlongitud);
        this.setEndLatitud(endLatitud);
        this.setEndLongitud(endLongitud);
    }

    public FavoriteRoute(){

    }

    public String returnLine(){

        String linea = name ;

        return linea;
    }
}
