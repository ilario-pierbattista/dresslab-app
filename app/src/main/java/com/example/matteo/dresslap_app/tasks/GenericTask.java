package com.example.matteo.dresslap_app.tasks;

import android.os.AsyncTask;

import com.example.matteo.dresslap_app.interfaces.TaskListener;

/**
 * Created by Matteo on 03/05/2016.
 */
public class GenericTask extends AsyncTask<Integer, Void, Boolean> {
    private TaskListener<String> listener;

    public GenericTask(TaskListener<String> listener) {
        super();
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        int num = params[0];
        return (num % 2 == 0);
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
}
