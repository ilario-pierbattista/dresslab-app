package com.example.matteo.dresslap_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.matteo.dresslap_app.ListAdapterProduct;
import com.example.matteo.dresslap_app.R;

public class CustomModalActivity extends AppCompatActivity {

    private ListAdapterProduct adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_modal);

        /*Button btnAnnulla = (Button)findViewById(R.id.btn_annulla);
        btnAnnulla.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(CustomModalActivity.this,"Annulla",Toast.LENGTH_SHORT).show();
            }
        });
        Button btnConferma = (Button)findViewById(R.id.btn_conferma);
        btnConferma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(CustomModalActivity.this,"Conferma",Toast.LENGTH_SHORT).show();
            }
        });*/
        ListView lvRichiesta = (ListView)findViewById(R.id.lv_richiesta);
        String[] values = new String[] {
                "CAPO TROVATO! STO ARRIVANDO",
                "STO CERCANDO IN MAGAZZINO",
                "TAGLIA TERMINATA :("
        };
        lvRichiesta.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,values));
        lvRichiesta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Click sull'elemento :" + String.valueOf(position), Toast.LENGTH_LONG).show();
            }

        });
    }
}
