package com.example.matteo.dresslap_app;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.matteo.dresslap_app.interfaces.TaskListener;
import com.example.matteo.dresslap_app.tasks.TaskMessage;

import java.util.ArrayList;

public class CustomModalActivity extends AppCompatActivity {

    private ListAdapterProduct adapter;
    private TaskListener<String> taskListener;
    private TaskMessage task;
    private ConnectivityManager connectivityManager;
    private int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_modal);

        Intent intent = getIntent();
        taskId = intent.getIntExtra("task_id", -1);

        final ListView lvRichiesta = (ListView)findViewById(R.id.lv_richiesta);
        String[] values = new String[] {
                "Capo trovato! Sto arrivando",
                "Sto cercando in magazzino",
                "Taglia terminata :("
        };

        taskListener = new TaskListener<String>() {
            public void onTaskSuccess(String... result) {}

            @Override
            public void onTaskError() {
                Toast.makeText(getApplicationContext(), "Errore di comunicazione",Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onTaskComplete() {
                task = null;
            }

            @Override
            public void onTaskCancelled() {

            }
        };
        lvRichiesta.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,values));
        //click su un elemento della lista
        lvRichiesta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = (String) lvRichiesta.getAdapter().getItem(position);
                task = new TaskMessage(taskListener, connectivityManager, CustomModalActivity.this,  taskId, message);
                task.execute();
            }

        });
    }
}
