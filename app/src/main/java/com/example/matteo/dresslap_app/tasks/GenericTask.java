package com.example.matteo.dresslap_app.tasks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.matteo.dresslap_app.MainActivity;
import com.example.matteo.dresslap_app.interfaces.TaskListener;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class GenericTask extends AsyncTask<Integer, Void, Boolean> {


    private static final String URL_CONN = "https://raw.githubusercontent.com/dstnbrkr/DRBOperationTree/master/Example/cassette.json";

    private static final String ID="id";
    private static final String ID_PRODOTTO="id_prodotto";
    private static final String COLORE="colore";
    private static final String TAGLIA="taglia";
    private static final String PREZZO="prezzo";
    private static final String QTA="qta";
    private static final String POSIZIONE="posizione";
    private static final String VETRINA="vetrina";

    private TaskListener<String> listener;
    private ConnectivityManager connectivityManager;

    private ArrayList<HashMap<String, String>> productList;


    public GenericTask(TaskListener<String> listener,ConnectivityManager connectivityManager) {
        super();
        this.listener = listener;
        this.connectivityManager = connectivityManager;

    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        int num = params[0];
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            try{
                downloadUrl(URL_CONN);
                return true;
            }catch (IOException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if(success) {
            listener.onTaskSuccess("OK");
            

        } else {
            listener.onTaskError();
        }
        listener.onTaskComplete();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        listener.onTaskCancelled();
    }

    public String downloadUrl(String url) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpRequestBase httpRequest = null;
        HttpResponse httpResponse = null;
        InputStream inputStream = null;
        String response = "";
        StringBuffer buffer = new StringBuffer();
        httpRequest = new HttpGet(url);
        httpResponse = httpclient.execute(httpRequest);
        inputStream = httpResponse.getEntity().getContent();
        int contentLength = (int) httpResponse.getEntity().getContentLength();
        if (contentLength < 0)  {
            // Log.e(TAG, "The HTTP response is too long.");
        }
        byte[] data = new byte[256];
        int len = 0;
        while (-1 != (len = inputStream.read(data)) ) {
            buffer.append(new String(data, 0, len));
        }
        inputStream.close();
        response = buffer.toString();

        try {
            JSONObject example = new JSONObject();
            example.put("id", "1");
            System.out.print(example);
        }catch (JSONException e){

        }




        try{
            int i;

            //Creazione JSON per il set di risultati
            JSONArray jsonArray = new JSONArray(response);

           /* JSONObject ex = new JSONObject()
                    .put("id","100")
                    .put("id_prodotto","2")
                    .put("colore","blue")
                    .put("taglia","M")
                    .put("prezzo","25")
                    .put("qta","100")
                    .put("posizione","negozio")
                    .put("vetrina","vetrina");
            */

            for(i=0; i<jsonArray.length(); i++){

                //Creazione oggetto JSON per il singolo risultato
                JSONObject element = jsonArray.getJSONObject(i);

                String id = element.getString(ID);
                String id_prodotto = element.getString(ID_PRODOTTO);
                String colore = element.getString(COLORE);
                String taglia = element.getString(TAGLIA);
                String prezzo = element.getString(PREZZO);
                String qta = element.getString(QTA);
                String posizione = element.getString(POSIZIONE);
                String vetrina = element.getString(VETRINA);

                HashMap<String, String> item = new HashMap<String, String>();

                item.put(ID,id);
                item.put(ID_PRODOTTO,id_prodotto);
                item.put(COLORE,colore);
                item.put(TAGLIA,taglia);
                item.put(PREZZO,prezzo);
                item.put(QTA,qta);
                item.put(POSIZIONE,posizione);
                item.put(VETRINA,vetrina);

                productList.add(item);
            }

        }catch (JSONException e){
            System.out.println(e);
        }

        return response;
    }
}
