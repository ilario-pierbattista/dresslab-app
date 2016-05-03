package com.example.matteo.dresslap_app.interfaces;

/**
 * Created by Matteo on 03/05/2016.
 */
public interface ProgressedTaskListener<ResultType, ProgressType>
extends TaskListener<ResultType> {
    void onProgressPublished(ProgressType... progress);
}
