package com.example.matteo.dresslap_app.tasks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.example.matteo.dresslap_app.MainActivity;
import com.example.matteo.dresslap_app.interfaces.TaskListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class TaskMessage extends AsyncTask<Void, Void, Boolean> {
    private String path;
    private TaskListener<String> listener;
    private ConnectivityManager connectivityManager;
    private Context mContext;
    private String jsonResponse, message;

    public TaskMessage(TaskListener<String> listener,
                       ConnectivityManager connectivityManager, Context mContext,
                       int id, String message) {
        super();
        this.listener = listener;
        this.connectivityManager = connectivityManager;
        this.mContext = mContext;
        this.message = message;
        path = "http://192.168.0.2/camerino/task/" + Integer.toString(id) + "/message";
    }

    @Override
    protected Boolean doInBackground(Void... p) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(path);
            post.setHeader(HTTP.CONTENT_TYPE, "application/json");

            String json = "{\"messaggio\": \"" + message +"\"}";
            Log.i("TASK", json);
            post.setEntity(new StringEntity(json));
            HttpResponse resp = client.execute(post);
            HttpEntity ent = resp.getEntity();
            System.out.println(ent.toString());

            return true;
        } catch (IOException e) {
            Log.e("TASK_MESSAGE", "ERRORE", e);
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (success) {
            listener.onTaskSuccess();
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
}