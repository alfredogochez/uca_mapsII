package zero.ucamaps.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import zero.ucamaps.R;
import zero.ucamaps.beans.FavoriteRoute;
import zero.ucamaps.location.RoutingDialogFragment;
import zero.ucamaps.util.GlobalPoints;

/**
 * Created by alf on 23/04/2016.
 */
public class DialogFavoriteList extends DialogFragment {

    private RoutingDialogFragment.RoutingDialogListener mRoutingDialogListener;

    public void setRoutingDialogListener(RoutingDialogFragment.RoutingDialogListener dialogListener){
        this.mRoutingDialogListener = dialogListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        //Leer rutas favoritas para ponerlas en la lista
        List<FavoriteRoute> listaRutas = recuperar();

        String[] listaRutasString = new String[listaRutas.size()];



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();


        GlobalPoints globalListRoute = (GlobalPoints) getActivity().getApplicationContext();
        globalListRoute.setListaRutas(listaRutas);

        for(int i = 0;i < listaRutas.size() ;i++){
                listaRutasString[i] =  listaRutas.get(i).getName();
         }


                builder.setView(inflater.inflate(R.layout.favorites, null))
                        .setTitle("Rutas Favoritas")
                        .setItems(listaRutasString, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GlobalPoints globalListRoute = (GlobalPoints) getActivity().getApplicationContext() ;

                            List<FavoriteRoute> listaRutas = globalListRoute.getListaRutas();
                            FavoriteRoute favorito = listaRutas.get(which);
                            mRoutingDialogListener.onGetRouteFavorite(favorito.getStartName(),favorito.getEndName(),favorito.getStartlatitud(),favorito.getStartlongitud(),
                                    favorito.getEndLatitud(),favorito.getEndLongitud());



                        }
                    });



                return builder.create();


    }

    public List<FavoriteRoute> recuperar(){
        String nombrearchivo = "favorites_routes";
        List<FavoriteRoute> listaFavoritos = new LinkedList<FavoriteRoute>();
        int i = 0;
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), nombrearchivo);
        ObjectInputStream objectinputstream = null;
        FileInputStream streamIn = null;
        try {
            if(file != null) {
                try {
                    streamIn = new FileInputStream(file);
                    objectinputstream = new ObjectInputStream(streamIn);
                    listaFavoritos = (List<FavoriteRoute>) objectinputstream.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (objectinputstream != null) {
                        objectinputstream.close();
                    }
                    if (streamIn != null) {
                        streamIn.close();
                    }
                }
            }
            return listaFavoritos;
        } catch (IOException e) {
            e.printStackTrace();
            }
        return listaFavoritos;
    }
}