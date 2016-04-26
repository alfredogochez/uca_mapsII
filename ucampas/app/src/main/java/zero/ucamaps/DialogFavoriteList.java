package zero.ucamaps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import zero.ucamaps.MainActivity;
import zero.ucamaps.R;

/**
 * Created by alf on 23/04/2016.
 */
public class DialogFavoriteList extends DialogFragment {
    //lista quemada de strings, con el adaptador para cuando la lista sea dinamica
    String[] listaRutas = {"item1", "item2", "item3", "item4"};
    ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.favorites, listaRutas);
    //dialogo de la lista
    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Rutas Favoritas")
                .setAdapter(adaptador,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        System.out.println(which);
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        return builder.create();
    }
}