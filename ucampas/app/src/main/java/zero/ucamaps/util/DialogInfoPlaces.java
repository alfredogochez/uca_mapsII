package zero.ucamaps.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import zero.ucamaps.R;

/**
 * Created by francisco herrera on 15/05/2016.
 */
public class DialogInfoPlaces extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_info_places, null));

        //Variables para los controles
        TextView titulo_edificio = (TextView) getDialog().findViewById(R.id.titulo_edificio);
        ImageView foto_edificio = (ImageView) getDialog().findViewById(R.id.foto_edificio);
        TextView info_edificio = (TextView) getDialog().findViewById(R.id.info_edificio);

        //Aqui deberia traer la info desde la BD, pero no se como aún, asi que voy a poner cosas random
        titulo_edificio.setText("Soy un edificio",null);
        info_edificio.setText("Yo soy la descripcion del edificio", null);

        return builder.create();

        //super.onCreate(savedInstanceState);
    }
}
