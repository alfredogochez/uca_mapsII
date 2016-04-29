package zero.ucamaps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import zero.ucamaps.MainActivity;
import zero.ucamaps.R;

/**
 * Created by alf on 23/04/2016.
 */
public class DialogFavoriteList extends DialogFragment {
    //lista quemada de strings, con el adaptador para cuando la lista sea dinamica
    ListView lista;
    ArrayAdapter<String> adaptador;
    //dialogo de la lista
    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String[] listaRutas = {"", "", "", "", "", "", "", "", "" , ""};

        //Leer rutas favoritas para ponerlas en la lista
        listaRutas = recuperar();

        //lista = (ListView) getActivity().findViewById(R.id.lista_favoritos);
        adaptador = new ArrayAdapter<String>(getActivity(),R.layout.favorites, listaRutas);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //adaptador = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.favorites, listaRutas);

        if(listaRutas.length <= 0){
            //No habian rutas
            //Toast.makeText(getActivity(), "No hay rutas guardadas como favoritas", Toast.LENGTH_SHORT).show();
            builder.setMessage("No hay rutas marcadas como favoritas");
            return builder.create();
        }
        else{
            builder.setView(inflater.inflate(R.layout.favorites, null))
                    .setTitle("Rutas Favoritas")
                    /*.setItems(listaRutas, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ;
                        }
                    });*/
                    .setAdapter(adaptador, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            System.out.println(which);
                            // The 'which' argument contains the index position
                            // of the selected item
                        }
                    });
                return builder.create();
        }
    }

    public String[] recuperar(){
        String nombrearchivo = "favorites_routes";
        String[] lista = {"", "", "", "", "", "", "", "", "" , ""};
        int i = 0;
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), nombrearchivo);
        try {
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader archivo = new InputStreamReader(fIn);
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String todo = "";
            while (linea != null && i<10) {
                lista[i] = linea.split("_")[0];
                i+=1;
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            return lista;

        } catch (IOException e) {
            e.printStackTrace();
        }
        lista = new String[10];
        return lista;
    }
}