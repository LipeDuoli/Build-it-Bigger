package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myJokes.MyJokes;

import java.io.IOException;

public class EndpointJokesAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyJokes jokesApiService = null;
    private JokeAsyncTaskListener mListener;

    public EndpointJokesAsyncTask(JokeAsyncTaskListener listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        initService();

        try {
            return jokesApiService.fetchJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private void initService() {
        if (jokesApiService == null) {
            MyJokes.Builder builder = new MyJokes.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            jokesApiService = builder.build();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mListener.onReceiveJoke(result);
    }

    public interface JokeAsyncTaskListener {
        void onReceiveJoke(String joke);
    }
}
