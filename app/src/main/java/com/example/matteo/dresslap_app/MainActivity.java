package com.example.matteo.dresslap_app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.matteo.dresslap_app.interfaces.TaskListener;
import com.example.matteo.dresslap_app.tasks.GenericTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvProduct;
    private ListAdapterProduct adapter;
    private List<Product> mProductList;
    private static final int REPEAT_TIME = 1000;
    public static final String HOSTNAME_PRODOTTI = "http://192.168.0.2/camerino/task/list";
    private TaskListener<String> taskListener;
    public static ArrayList<Integer> taskIds;
    private GenericTask task;
    private Handler loopHandler;
    private Runnable loopRunnable;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProductList = new ArrayList<>();
        lvProduct = (ListView) findViewById(R.id.lv_capi);
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        taskListener = new TaskListener<String>() {
            public void onTaskSuccess(String... result) {
                String res = result[0];
                try {
                    System.out.println(res);
                    JSONArray resultArray = new JSONArray(res);

                    if (taskIds == null || taskIds.size() != resultArray.length()) {
                        updateCurrentTaskIds(resultArray);
                        resetList();
                        addList(res);
                    }

                } catch (JSONException e) {
                    Log.e("MAIN", "errore", e);
                }
            }

            @Override
            public void onTaskError() {
            }

            @Override
            public void onTaskComplete() {
                task = null;
            }

            @Override
            public void onTaskCancelled() {

            }
        };

        loopHandler = new Handler();
        loopRunnable = new Runnable() {
            @Override
            public void run() {
                if (task == null) {
                    //Aggiornamento della lista
                    task = new GenericTask(taskListener, connectivityManager, MainActivity.this);
                    task.execute();
                }
                loopHandler.postDelayed(loopRunnable, REPEAT_TIME);
            }
        };
        loopHandler.post(loopRunnable);
    }

    public void addList(String res) {
        try {
            JSONArray jsonArray = new JSONArray(res);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject task = jsonArray.getJSONObject(i);

                String camerino = task.getString("camerino");
                int id = Integer.parseInt(task.getString("id"));
                //String messaggio = task.getString("messaggio");

                JSONObject articolo = task.getJSONObject("articolo");
                String colore = articolo.getString("colore");
                int idArticolo = articolo.getInt("id");
                String hex = articolo.getString("coloreHex");
                // hex = hex.substring(1, hex.length() - 1);
                String taglia = articolo.getString("taglia");
                String prezzo = articolo.getString("prezzo");

                JSONObject prodotto = articolo.getJSONObject("prodotto");

                String nome_prodotto = prodotto.getString("nome");

                mProductList.add(new Product(id, nome_prodotto, hex, idArticolo, taglia, prezzo, camerino));
                adapter = new ListAdapterProduct(MainActivity.this, mProductList);
                lvProduct.setAdapter(adapter);
            }
        } catch (JSONException e) {
            System.out.println(e);
        }
    }

    private void resetList() {
        mProductList.clear();
    }

    private void updateCurrentTaskIds(JSONArray resultArray) {
        try {
            taskIds = new ArrayList<>();
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject task = resultArray.getJSONObject(i);

                taskIds.add(task.getInt("id"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
