package zero.ucamaps.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import zero.ucamaps.database.Sitio;

/**
 * Created by alf on 14/06/2016.
 */
public class DialogSearchResult extends DialogFragment{
    private List<Sitio> listaSitio = new ArrayList<>();




    @Override
    public Dialog onCreateDialog(Bundle SavedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        String[] listaSitiosStrings = new String[listaSitio.size()];


        return builder.create();
    }

    public List<Sitio> getListaSitio() {
        return listaSitio;
    }

    public void setListaSitio(List<Sitio> listaSitio) {
        this.listaSitio = listaSitio;
    }
}
