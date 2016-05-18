package zero.ucamaps.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import zero.ucamaps.R;

/**
 * Created by francisco herrera on 15/05/2016.
 */
public class DialogInfoPlaces extends DialogFragment {

    public static DialogInfoPlaces newInstance(String titulo) {
        DialogInfoPlaces f = new DialogInfoPlaces();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("titulo", titulo);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_info_places, null));

        //Variables para los controles

        try{
            TextView titulo_edificio = (TextView) getDialog().findViewById(R.id.titulo_edificio);
            ImageView foto_edificio = (ImageView) getDialog().findViewById(R.id.foto_edificio);
            TextView info_edificio = (TextView) getDialog().findViewById(R.id.info_edificio);

            //Aqui deberia traer la info desde la BD, pero no se como aún, asi que voy a poner cosas random
            titulo_edificio.setText("Soy un edificio",null);
            info_edificio.setText("Yo soy la descripcion del edificio", null);
        }
        catch (Exception ex){
            String error = ex.getMessage();
        }
        */
        //return builder.create();

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.dialog_info_places,container, false);
        //View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        View titulo_edificio = vista.findViewById(R.id.titulo_edificio);
        View info_edificio = vista.findViewById(R.id.info_edificio);
        ((TextView)titulo_edificio).setText(getArguments().getString("titulo"));
        ((TextView)info_edificio).setText("Admiren mi edificiosidad :v");


        return vista;
    }



}
