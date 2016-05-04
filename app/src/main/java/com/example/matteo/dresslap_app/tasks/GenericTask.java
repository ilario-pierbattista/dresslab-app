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
import java.net.URL;

public class GenericTask extends AsyncTask<Integer, Void, Boolean> {
    private TaskListener<String> listener;
    private ConnectivityManager connectivityManager;
    private static final String URL_CONN = "https://raw.githubusercontent.com/dstnbrkr/DRBOperationTree/master/Example/cassette.json";
    private JSONArray body= null;

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

        try{
            JSONObject jsonObject = new JSONObject(response);
            body=jsonObject.getJSONArray("body");
            System.out.println(String.valueOf(body));

        }catch (JSONException e){}

        return response;
    }
}
