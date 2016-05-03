package zero.ucamaps.util;

/**
 * Created by francisco herrera on 23/04/2016.
 */

import android.app.Application;

import java.util.List;

import zero.ucamaps.beans.FavoriteRoute;

public class GlobalPoints extends Application{

    private double latitude;
    private double longitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private List<FavoriteRoute> listaRutas;

    public List<FavoriteRoute> getListaRutas() {
        return listaRutas;
    }

    public void setListaRutas(List<FavoriteRoute> listaRutas) {
        this.listaRutas = listaRutas;
    }
}
