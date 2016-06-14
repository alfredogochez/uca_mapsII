package zero.ucamaps.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import zero.ucamaps.R;

/**
 * Created by alf on 12/06/2016.
 */
public class DialogSearchForm extends DialogFragment{



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState){
        View vista = inflater.inflate(R.layout.search_form, container, false);
        View titulo_busqueda = vista.findViewById(R.id.titulo_formulario);
        View pregunta = vista.findViewById(R.id.pregunta_formulario);
        View boton1 = vista.findViewById(R.id.rbSitio);
        View boton2 = vista.findViewById(R.id.rbPersonal);
        View boton3 = vista.findViewById(R.id.rbEdificio);
        View busqueda = vista.findViewById(R.id.txt_ingrese);
        View txtbox = vista.findViewById(R.id.box_ingrese);
        View boton = vista.findViewById(R.id.btn_buscar);
        //Seteando las cosas del formulario
        ((TextView) titulo_busqueda).setText("Formulario de Busqueda");
        ((TextView)pregunta).setText("¿Que desea buscar?");
        ((RadioButton)boton1).setText("Sitio");
        ((RadioButton)boton2).setText("Personal");
        ((RadioButton)boton3).setText("Edificio");
        ((TextView)busqueda).setText("Ingrese su busqueda:");
        ((TextView)txtbox).setHint("...");
        ((Button)boton).setText("Buscar");

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return vista;
    }


}

