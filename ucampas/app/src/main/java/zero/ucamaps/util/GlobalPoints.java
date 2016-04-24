package zero.ucamaps.util;

/**
 * Created by francisco herrera on 23/04/2016.
 */

import android.app.Application;

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
}
