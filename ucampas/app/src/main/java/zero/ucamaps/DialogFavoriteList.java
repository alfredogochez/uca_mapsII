package zero.ucamaps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
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
import java.util.ArrayList;
import java.util.List;

import zero.ucamaps.beans.FavoriteRoute;
import zero.ucamaps.util.GlobalPoints;

/**
 * Created by alf on 23/04/2016.
 */
public class DialogFavoriteList extends DialogFragment {
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        //Leer rutas favoritas para ponerlas en la lista
        List<FavoriteRoute> listaRutas = recuperar();

        String[] listaRutasString = new String[listaRutas.size()];


        //lista = (ListView) getActivity().findViewById(R.id.lista_favoritos);
        //adaptador = new ArrayAdapter<String>(getActivity(),R.layout.favorites, listaRutas);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //adaptador = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.favorites, listaRutas);

        GlobalPoints globalListRoute = (GlobalPoints) getActivity().getApplicationContext();
        globalListRoute.setListaRutas(listaRutas);

        for(int i = 0;i < listaRutas.size() ;i++){
                listaRutasString[i] =  listaRutas.get(i).getName();
         }

            ListView lv = (ListView) getActivity().findViewById(R.id.lista_favoritos);
                builder.setView(inflater.inflate(R.layout.favorites, null))
                        .setTitle("Rutas Favoritas")
                        .setItems(listaRutasString, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GlobalPoints globalListRoute = (GlobalPoints) getActivity().getApplicationContext() ;
                            Toast.makeText(getActivity(), globalListRoute.getListaRutas().get(which).returnLine(), Toast.LENGTH_SHORT).show();
                        }
                    });



                return builder.create();


    }

    public List<FavoriteRoute> recuperar(){
        String nombrearchivo = "favorites_routes";
        List<FavoriteRoute> listaFavoritos = new ArrayList<>();
        int i = 0;
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), nombrearchivo);
        try {

            if(file != null) {
                FileInputStream fIn = new FileInputStream(file);
                InputStreamReader archivo = new InputStreamReader(fIn);
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                String lista[] = new String[5];

                while (linea != null && i < 10) {
                    lista = linea.split("_");

                    FavoriteRoute favorite = new FavoriteRoute();
                    favorite.setName(lista[0]);
                    favorite.setLatitud(Double.parseDouble(lista[1]));
                    favorite.setLongitud(Double.parseDouble(lista[2]));

                    listaFavoritos.add(favorite);
                    i += 1;
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
            }
            return listaFavoritos;

        } catch (IOException e) {
            e.printStackTrace();
            }
        return listaFavoritos;
    }
}