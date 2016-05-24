package zero.ucamaps.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zero.ucamaps.R;
import zero.ucamaps.database.CargaAsinc;
import zero.ucamaps.database.Constantes;
import zero.ucamaps.database.RutaEspecial;
import zero.ucamaps.database.VolleySingleton;
import zero.ucamaps.location.RoutingDialogFragment;

/**
 * Created by alf on 14/05/2016.
 */
public class DialogSpecialRoutes extends DialogFragment{

    private List<RutaEspecial> listaRutas = new ArrayList<RutaEspecial>();
    private static final String TAG = DialogSpecialRoutes.class.getSimpleName();
    private RoutingDialogFragment.RoutingDialogListener mRoutingDialogListener;

    public void setRoutingDialogListener(RoutingDialogFragment.RoutingDialogListener dialogListener) {
        this.mRoutingDialogListener = dialogListener;
    }




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Leer rutas favoritas para ponerlas en la lista


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();


        Log.d(TAG,"esto lleva la lista " + listaRutas);
        if(listaRutas.size()< 1){
            Toast.makeText(
                    getActivity(),
                    "Tengo null",
                    Toast.LENGTH_LONG).show();
            getActivity().finish();
        }else {
            String[] listaRutasString = new String[listaRutas.size()];


            for (int i = 0; i < listaRutas.size(); i++) {
                listaRutasString[i] = listaRutas.get(i).getNOMBRE();
            }

            ListView lv = (ListView) getActivity().findViewById(R.id.lista_favoritos);
            builder.setView(inflater.inflate(R.layout.favorites, null))
                    .setTitle("Rutas Especiales")
                    .setItems(listaRutasString, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            //Espacio para implementar el metodo para llamar las rutas especiales

                        }
                    });

        }
            return builder.create();


    }

    public List<RutaEspecial> getListaRutas() {
        return listaRutas;
    }

    public void setListaRutas(List<RutaEspecial> listaRutas) {
        this.listaRutas = listaRutas;
    }
}
