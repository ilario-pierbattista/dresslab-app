package com.example.matteo.dresslap_app.interfaces;

/**
 * Created by Matteo on 03/05/2016.
 */
public interface TaskListener<ResultType> {
    void onTaskSuccess(ResultType... result);
    void onTaskError();
    void onTaskComplete();
    void onTaskCancelled();
}
