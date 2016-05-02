package zero.ucamaps.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import zero.ucamaps.DialogFavoriteList;
import zero.ucamaps.DialogReplaceFavorite;
import zero.ucamaps.R;

/**
 * Created by francisco herrera on 22/04/2016.
 */
public class DialogFavoriteRoute extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_favorites, null))
                // Add action buttons
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Guardar Ruta
                        // Calling Application class (see application tag in AndroidManifest.xml)
                        EditText et1 = (EditText) getDialog().findViewById(R.id.nombre_ruta_favorita);
                        String nombre_ruta = et1.getText().toString();

                        final GlobalPoints globalVariable = (GlobalPoints) getActivity().getApplicationContext();
                        // Get name and email from global/application context
                        final double latitude = globalVariable.getLatitude();
                        final double longitude = globalVariable.getLongitude();
                        final String ruta_cambio = nombre_ruta + "_" + latitude + "_" + longitude + "\n";
                        try {
                            //verificamos si llegamos a las 10 rutas limite
                            int lineas = calcular_longitud();
                            if (lineas >= 10) {

                                new AlertDialog.Builder(getActivity())
                                        .setTitle("Advertencia")
                                        .setMessage("Ya tiene 10 rutas favoritas, para guardar una nueva, debe borrar una antigua, ¿desea continuar?")
                                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                DialogFragment reemplazarDia = new DialogReplaceFavorite();

                                                reemplazarDia.show(getFragmentManager(), "Seleccione la Ruta");

                                                Toast.makeText(getActivity(), "Ruta Cambiada Exitosamente", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // do nothing
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();

                            } else {
                                File tarjeta = Environment.getExternalStorageDirectory();
                                File file = new File(tarjeta.getAbsolutePath(), "favorites_routes");
                                //verificamos si el archivo existe
                                if (file.createNewFile()) {
                                    Toast.makeText(getActivity(), "no habia archivo", Toast.LENGTH_SHORT).show();
                                    //si la condicion da true, es por que el archivo no existia, y se creo, por ende, esta es la primera ruta creada
                                    FileOutputStream fos = new FileOutputStream(file);
                                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                                    osw.write(nombre_ruta + "_" + latitude + "_" + longitude + "\n");
                                    osw.flush();
                                    osw.close();
                                    fos.close();
                                    Toast.makeText(getActivity(), "Ruta Guardada Exitosamente", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "si habia archivo", Toast.LENGTH_SHORT).show();
                                    //si la consdicion da false, es por que el archivo ya existe, por ende se usa el filewrite
                                    FileWriter fw = new FileWriter(file, true);
                                    fw.write(nombre_ruta + "_" + latitude + "_" + longitude + "\n");
                                    fw.close();
                                    Toast.makeText(getActivity(), "Ruta Guardada Exitosamente", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Cancelar
                        ;
                    }
                });
        return builder.create();
    }

    private int calcular_longitud() throws IOException {
        //cuenta las lineas del archivo para que no se pase de 10 rutas favoritas
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), "favorites_routes");
        if(file.exists()){
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String s;
        int longitud = 0;
        //leemos el archivo hasta el final, y aumentamos en 1 las lineas
        while((s = br.readLine()) != null)
            longitud++;

        br.close();
        br = null;      // Libera memoria
        fr.close();
        fr = null;      // Libera memoria
        file = null; // Libera memoria
         Toast.makeText(getActivity(), "ya conte las rutas", Toast.LENGTH_SHORT).show();
        return longitud;
        }else
            return 0;
    }


    private void reemplazar_ruta(String ruta){
        Toast.makeText(getActivity(), "creando ventana", Toast.LENGTH_SHORT).show();

    }
}
