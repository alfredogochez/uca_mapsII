package zero.ucamaps.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zero.ucamaps.R;
import zero.ucamaps.database.RutaEspecial;
import zero.ucamaps.database.Sitio;
import zero.ucamaps.util.GlobalPoints;

/**
 * Created by alf on 14/06/2016.
 */
public class DialogSearchResult extends DialogFragment{
    private List<Sitio> listaSitio = new ArrayList<>();
    private Context contexto;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        //Leer rutas favoritas para ponerlas en la lista
                String[] listaSitioString = new String[listaSitio.size()];



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();


        for(int i = 0;i < listaSitio.size() ;i++){
            listaSitioString[i] =  listaSitio.get(i).getNombre();
        }
        String titulo = listaSitio.size()+" coinciencias encontradas";

        builder.setView(inflater.inflate(R.layout.favorites, null))
                .setTitle(titulo)
                .setItems(listaSitioString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Sitio sitioEscogido = listaSitio.get(which);
                        AlertDialog.Builder decision = new AlertDialog.Builder(getActivity());
                        decision.setTitle("Seleccione")
                                .setMessage("Su objetivo fue encontrado, ¿que desea hacer?")
                                .setIcon(R.drawable.ic_find_in_page_black_24dp)
                                .setPositiveButton("Trazar ruta hacia objetivo", new DialogInterface.OnClickListener() {
                                    @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Sitio sitioEscogido = listaSitio.get(which);
                                            Toast.makeText(contexto,"Ubicacion: "+sitioEscogido.getNombreEdificio(),Toast.LENGTH_SHORT).show();
                                            Toast.makeText(contexto,"Ruta trazada, ¡a por el!",Toast.LENGTH_SHORT).show();
                                        };
                                    })
                                .setNegativeButton("Solo Marcar objetivo", new DialogInterface.OnClickListener() {
                                    @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(contexto,"Ubicacion: "+sitioEscogido.getNombreEdificio(),Toast.LENGTH_SHORT).show();
                                        Toast.makeText(contexto,"Objetico Marcado",Toast.LENGTH_SHORT).show();
                                        };
                                    });
                        AlertDialog decisionAlert = decision.create();
                        decisionAlert.show();
                    }
                });



        return builder.create();


    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public void setListaSitio(List<Sitio> listaSitio) {
        this.listaSitio = listaSitio;
    }
}
