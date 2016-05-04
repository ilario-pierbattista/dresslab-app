package com.example.matteo.dresslap_app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.matteo.dresslap_app.interfaces.TaskListener;
import com.example.matteo.dresslap_app.tasks.GenericTask;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvProduct;
    private ListAdapterProduct adapter;
    private List<Product> mProductList;
    public static final String KEY_ID="keyID";
    public static final String KEY_PREZZO="keyPrezzo";
    public static final String KEY_COLORE="keyColore";
    public static final String KEY_TAGLIA="keyTaglia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProduct = (ListView)findViewById(R.id.lv_capi);

        mProductList = new ArrayList<>();

        mProductList.add(new Product("jeans AS","E53935","46","80 euro","CAMERINO 1"));
        mProductList.add(new Product("scarpe AS","125688","47","120 euro","CAMERINO 1"));
        mProductList.add(new Product("maglioncino AS","0092E6","46","175 euro","CAMERINO 2"));


        //adapter = new ListAdapterProduct(getApplicationContext(),mProductList);
        adapter = new ListAdapterProduct(MainActivity.this,mProductList);

        lvProduct.setAdapter(adapter);

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("MAIN", "Eseguito");
                Intent intent = new Intent(MainActivity.this, GestioneRichiestaActivity.class);
                intent.putExtra(KEY_ID, mProductList.get(position).getId());
                intent.putExtra(KEY_PREZZO, mProductList.get(position).getPrezzo());
                intent.putExtra(KEY_COLORE, mProductList.get(position).getColore());
                intent.putExtra(KEY_TAGLIA, mProductList.get(position).getTaglia());
                startActivity(intent);
            }
        });

        TaskListener<String> taskListener = new TaskListener<String>() {
            @Override
            public void onTaskSuccess(String... result) {
                String res = result[0];
                Toast.makeText(getApplicationContext(), "Connessione effettuata", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTaskError() {
                Toast.makeText(getApplicationContext(), "Non è possibile connettersi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTaskComplete() {
                Toast.makeText(getApplicationContext(), "COMPLETATO", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTaskCancelled() {

            }
        };

        Context context = getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        GenericTask task = new GenericTask(taskListener,connectivityManager);
        task.execute(13);

    }
}
