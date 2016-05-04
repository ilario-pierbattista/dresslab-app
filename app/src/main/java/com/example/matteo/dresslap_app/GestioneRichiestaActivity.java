package com.example.matteo.dresslap_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class GestioneRichiestaActivity extends AppCompatActivity {

    private String id;
    private String colore;
    private String taglia;
    private String prezzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestione_richiesta);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        id=b.getString(MainActivity.KEY_ID);
        colore=b.getString(MainActivity.KEY_COLORE);
        prezzo=b.getString(MainActivity.KEY_PREZZO);
        taglia=b.getString(MainActivity.KEY_TAGLIA);

        /*TextView tvId = (TextView)findViewById(R.id.tv_id);
        TextView tvColore = (TextView)findViewById(R.id.tv_color);
        TextView tvTaglia = (TextView)findViewById(R.id.tv_taglia);
        TextView tvPrezzo = (TextView)findViewById(R.id.tv_prezzo);
        tvId.setText(this.id);
        tvPrezzo.setText(this.prezzo);
        tvColore.setText(this.colore);
        tvTaglia.setText(this.taglia);*/

    }
}
