package zero.ucamaps;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.View;

public class InfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        //View vista = inflater.inflate(R.layout.dialog_info_places,container, false);
        //View titulo = vista.findViewById(R.id.info_titulo);
        String nombre_edificio = attrs.getAttributeValue("","nombre_edificio");
        return super.onCreateView(name, context, attrs);

    }
}
