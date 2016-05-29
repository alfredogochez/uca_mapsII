package zero.ucamaps;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String titulo = getIntent().getStringExtra("nombre_edificio");
        TextView titulo_edificio;
        try{
            titulo_edificio = (TextView) findViewById(R.id.info_titulo);
            titulo_edificio.setText(titulo);
        }
        catch(Exception ex){
            String error = ex.getMessage();
            error= error;
        }
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);

    }
}
