package zero.ucamaps.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import zero.ucamaps.dialogs.DialogFavoriteList;
import zero.ucamaps.R;
import zero.ucamaps.beans.FavoriteRoute;
import zero.ucamaps.util.GlobalPoints;

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

                        EditText et1 = (EditText) getDialog().findViewById(R.id.nombre_ruta_favorita);
                        final String nombre_ruta = et1.getText().toString();

                        GlobalPoints globalVariable = (GlobalPoints) getActivity().getApplicationContext();
                        // creamos las variables que almacenan las coordenadas de los puntos de inicio y fin
                        final double startlatitude = globalVariable.getStartLatitud();
                        final double startlongitude = globalVariable.getStartLongitude();
                        final double endLongitude = globalVariable.getEndLongitude();
                        final double endLatitud = globalVariable.getEndLatitude();
                        final String startName = globalVariable.getStartName();
                        final String endName = globalVariable.getEndName();

                        try {//Esta toast es para cuando se va a reemplazar una ruta
                            final Toast tostada= Toast.makeText(getActivity(), "Ruta Modificada Exitosamente", Toast.LENGTH_SHORT);
                            //verificamos si llegamos a las 10 rutas limite
                            int lineas = calcular_longitud();
                            if (lineas >= 10) {//si se tienen 10 rutas o mas (mediante manipulacion erronea del archivo) creamos dos dialogs
                                DialogFavoriteList dfl = new DialogFavoriteList();
                                List<FavoriteRoute> listaRutas = dfl.recuperar();
                                String[] listaRutasString = new String[listaRutas.size()];
                                for (int j = 0; j < listaRutas.size(); j++) {
                                    listaRutasString[j] = listaRutas.get(j).getName();
                                }
                                //creamos un List dialog, el cual tendra las rutas favoritas
                                AlertDialog.Builder listDia = new AlertDialog.Builder(getActivity());
                                listDia.setTitle("Seleccione la Ruta");
                                listDia.setItems(listaRutasString, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int item) {
                                        //una vez la ruta a reemplazar se seleccione, llamamos al metodo reemplazar_ruta con todo lo necesario
                                        reemplazar_ruta(item, nombre_ruta, startlatitude, startlongitude, endLatitud, endLongitude,tostada,startName,endName);

                                    }
                                });
                                final AlertDialog listAlert = listDia.create();


                                AlertDialog.Builder alertReemplazar = new AlertDialog.Builder(getActivity());
                                alertReemplazar.setTitle("Advertencia");
                                alertReemplazar.setMessage("Ya tiene 10 rutas favoritas. Si desea guardar una ruta nueva, debe reemplazarla por una ruta antigua. ¿Desea Continuar?");
                                alertReemplazar.setIcon(android.R.drawable.ic_dialog_alert);
                                alertReemplazar.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        listAlert.show();

                                    }
                                });
                                alertReemplazar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                AlertDialog replaceAlert = alertReemplazar.create();
                                replaceAlert.show();


                            } else {
                                File tarjeta = Environment.getExternalStorageDirectory();
                                File file = new File(tarjeta.getAbsolutePath(), "favorites_routes");
                                //verificamos si el archivo existe
                                if (file.createNewFile()) {
                                    //si la condicion da true, es por que el archivo no existia, y se creo, por ende, esta es la primera ruta creada
                                    ObjectOutputStream oos = null;
                                    List<FavoriteRoute> listaRutas = new LinkedList<FavoriteRoute>();
                                    listaRutas.add(new FavoriteRoute(nombre_ruta, startlatitude, startlongitude, endLatitud, endLongitude,startName,endName));
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
                                    //si la consdicion da false, es por que el archivo ya existe, por ende se usa el filewrite
                                    ObjectInputStream objectinputstream = null;
                                    ObjectOutputStream oos = null;
                                    FileOutputStream fout = null;
                                    try {
                                        FileInputStream streamIn = new FileInputStream(file);
                                        objectinputstream = new ObjectInputStream(streamIn);
                                        List<FavoriteRoute> listaRutas = (List<FavoriteRoute>) objectinputstream.readObject();
                                        listaRutas.add(new FavoriteRoute(nombre_ruta, startlatitude, startlongitude, endLatitud, endLongitude,startName,endName));
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
        if (file.exists()) {
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
        } else
            return 0;
    }


    private void reemplazar_ruta(int index_ruta,String nombre_ruta,double startlatitude,double startlongitude, double endLatitud, double endLongitude,Toast tostada,String startname, String endname) {
        DialogFavoriteList dfl = new DialogFavoriteList();
        List<FavoriteRoute> rutas = dfl.recuperar();
        ObjectInputStream objectinputstream = null;
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), "favorites_routes");
        FavoriteRoute rutaNueva = new FavoriteRoute(nombre_ruta, startlatitude, startlongitude, endLatitud, endLongitude,startname,endname);
        rutas.set(index_ruta, rutaNueva);
        try {
            FileInputStream streamIn = new FileInputStream(file);
            objectinputstream = new ObjectInputStream(streamIn);
            fout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(rutas);
            tostada.show();
            } catch (Exception e) {
                e.printStackTrace();
        }

    }
}
