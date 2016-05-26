package zero.ucamaps.dialogs;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;

import zero.ucamaps.DrawerItem;
import zero.ucamaps.InfoActivity;
import zero.ucamaps.R;

/**
 * Created by francisco herrera on 15/05/2016.
 */
public class DialogInfoPlaces extends DialogFragment implements View.OnClickListener{

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

        View ver_mas = vista.findViewById(R.id.see_more_button);
        ((Button) ver_mas).setOnClickListener(this);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return vista;
    }

    @Override
    public void onClick(View v){
        Context contexto = getActivity().getApplicationContext();
        Intent i= new Intent(contexto,InfoActivity.class);
        i.putExtra("nombre_edificio",getArguments().getString("titulo"));
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try{
            startActivity(i);
        }
        catch(Exception ex){
            String error = ex.getMessage();
            error = error;
        }

    }



}