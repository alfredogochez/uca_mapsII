package zero.ucamaps.util;

/**
 * Created by francisco herrera on 23/04/2016.
 */

import android.app.Application;

import java.util.List;

import zero.ucamaps.beans.FavoriteRoute;

public class GlobalPoints extends Application{

    private double endLatitude;
    private double endLongitude;
    private double startLatitud;
    private double startLongitude;
    private List<FavoriteRoute> listaRutas;
    private String startName;
    private String endName;

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public double getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(double endLatitude) {
        this.endLatitude = endLatitude;
    }

    public double getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(double endLongitude) {
        this.endLongitude = endLongitude;
    }

    public double getStartLatitud() {
        return startLatitud;
    }

    public void setStartLatitud(double startLatitud) {
        this.startLatitud = startLatitud;
    }

    public double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(double startLongitude) {
        this.startLongitude = startLongitude;
    }

    public List<FavoriteRoute> getListaRutas() {
        return listaRutas;
    }

    public void setListaRutas(List<FavoriteRoute> listaRutas) {
        this.listaRutas = listaRutas;
    }
}
