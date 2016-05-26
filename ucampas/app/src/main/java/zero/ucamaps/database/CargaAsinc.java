package zero.ucamaps.database;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.zxing.client.android.LocaleManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zero.ucamaps.R;
import zero.ucamaps.dialogs.DialogSpecialRoutes;


/**
 * Created by alf on 23/05/2016.
 */
public class CargaAsinc extends AsyncTask<Activity,Void,List<RutaEspecial>> {
    private VolleySingleton volley;
    private RequestQueue requestQueue;
    private static final String TAG = CargaAsinc.class.getSimpleName();
    private List<RutaEspecial> listaRutas = new ArrayList<RutaEspecial>();
    public DialogSpecialRoutes dsr = new DialogSpecialRoutes();

    @Override
    protected List<RutaEspecial>  doInBackground(Activity... activities) {

        volley = VolleySingleton.getInstance(activities[0].getApplicationContext());
        requestQueue = volley.getRequestQueue();

        cargarAdaptador();
        return listaRutas;
    }

    @Override
    protected void onPostExecute(List<RutaEspecial> rutas){
        Log.d(TAG,"se termino de ejecutar esto, ya esta la lista");

    }

    public void cargarAdaptador() {
        // Petición GET
        String url = Constantes.GET;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        procesarRespuesta(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "estoy cargando el adaptador, esto pasa: " + error.toString());
                    }
                });
        addtoQueue(request);
    }

    private void procesarRespuesta(JSONObject response) {
        try {
            // Obtener atributo "estado"
            String estado = response.getString("estado");
            Log.d(TAG,"esta es la respuesta actual: "+response);
            switch (estado) {
                case "1": // EXITO
                    // Obtener array "rutas" Json
                    JSONArray arrayRutas = response.getJSONArray("rutas");
                    // Parsear
                    for (int i = 0; i < arrayRutas.length(); i++) {
                        JSONObject ruta = (JSONObject) arrayRutas.get(i);
                        String idRuta = ruta.getString("idRUTAESPECIAL");
                        String nombre = ruta.getString("NOMBRE");
                        String descripcion = ruta.getString("DESCRIPCION");
                        String puntos = ruta.getString("PUNTOS");
                        RutaEspecial rutaEspecialAux = new RutaEspecial();
                        rutaEspecialAux.setNOMBRE(nombre);
                        rutaEspecialAux.setDESCRIPCION(descripcion);
                        rutaEspecialAux.setIdRUTAESPECIAL(idRuta);
                        rutaEspecialAux.setPUNTOS(puntos);
                        listaRutas.add(rutaEspecialAux);
                        Log.d(TAG, "tengo esta lista " + listaRutas);
                    }
                    Log.d(TAG, "dentro del procesado de la respues, cargue esta lista " + listaRutas);
                    dsr.setListaRutas(listaRutas);
                    Log.d(TAG, "el dialog tiene esta lista " + dsr.getListaRutas());
                    break;
                case "2": // FALLIDO
                    String mensaje2 = response.getString("mensaje");
                    Log.d(TAG, "tengo este error " + mensaje2.toString());
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void addtoQueue(Request request) {
        if (request != null) {
            request.setTag(this);
            if (requestQueue == null)
                requestQueue = volley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(
                    600000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));

            requestQueue.add(request);
        }
    }


}
