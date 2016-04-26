package zero.ucamaps.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
                        final double latitude  = globalVariable.getLatitude();
                        final double longitude = globalVariable.getLongitude();

                        try {
                            File tarjeta = Environment.getExternalStorageDirectory();
                            File file = new File(tarjeta.getAbsolutePath(), "favorites_routes");
                            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
                            osw.write(nombre_ruta + "_" + latitude + "_" + longitude);
                            osw.flush();
                            osw.close();
                            //et1.setText("");
                        } catch (IOException ioe) {
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
}
