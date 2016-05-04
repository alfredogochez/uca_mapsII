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
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

import zero.ucamaps.DialogFavoriteList;
import zero.ucamaps.DialogReplaceFavorite;
import zero.ucamaps.R;
import zero.ucamaps.beans.FavoriteRoute;

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

                        GlobalPoints globalVariable = (GlobalPoints) getActivity().getApplicationContext();
                        // Get name and email from global/application context
                        double startlatitude = globalVariable.getStartLatitud();
                        double startlongitude = globalVariable.getStartLongitude();
                        double endLongitude = globalVariable.getEndLongitude();
                        double endLatitud = globalVariable.getEndLatitude();

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
                                    ObjectOutputStream oos = null;
                                    List<FavoriteRoute> listaRutas = new LinkedList<FavoriteRoute>();
                                    listaRutas.add(new FavoriteRoute(nombre_ruta, startlatitude, startlongitude, endLatitud, endLongitude));
                                    FileOutputStream fout = null;
                                    try {
                                        fout = new FileOutputStream(file);
                                        oos = new ObjectOutputStream(fout);
                                        oos.writeObject(listaRutas);
                                        Toast.makeText(getActivity(), "Ruta Guardada Exitosamente", Toast.LENGTH_SHORT).show();
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    } finally {
                                        if (oos != null) {
                                            oos.close();
                                        }
                                        if (fout != null) {
                                            oos.close();
                                        }
                                    }

                                } else {
                                    Toast.makeText(getActivity(), "si habia archivo", Toast.LENGTH_SHORT).show();
                                    //si la consdicion da false, es por que el archivo ya existe, por ende se usa el filewrite
                                    ObjectInputStream objectinputstream = null;
                                    ObjectOutputStream oos = null;
                                    FileOutputStream fout = null;
                                    try {
                                        FileInputStream streamIn = new FileInputStream(file);
                                        objectinputstream = new ObjectInputStream(streamIn);
                                        List<FavoriteRoute> listaRutas = (List<FavoriteRoute>) objectinputstream.readObject();
                                        listaRutas.add(new FavoriteRoute(nombre_ruta, startlatitude, startlongitude, endLatitud, endLongitude));
                                        fout = new FileOutputStream(file);
                                        oos = new ObjectOutputStream(fout);
                                        oos.writeObject(listaRutas);
                                        Toast.makeText(getActivity(), "Ruta Guardada Exitosamente", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    } finally {
                                        if (objectinputstream != null) {
                                            objectinputstream.close();
                                        }
                                        if (oos != null) {
                                            oos.close();
                                        }
                                        if (fout != null) {
                                            oos.close();
                                        }
                                    }
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
        ObjectInputStream objectinputstream = null;
        if(file.exists()){
            try {
                FileInputStream streamIn = new FileInputStream(file);
                objectinputstream = new ObjectInputStream(streamIn);
                List<FavoriteRoute> listaRutas = (List<FavoriteRoute>) objectinputstream.readObject();
                return listaRutas.size();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (objectinputstream != null) {
                    objectinputstream.close();
                }
            }
            return 0;
        }else
            return 0;
    }


    private void reemplazar_ruta(String ruta){
        Toast.makeText(getActivity(), "creando ventana", Toast.LENGTH_SHORT).show();

    }
}
