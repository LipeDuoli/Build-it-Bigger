package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class EndpointJokesAsyncTaskTest {

    private String resultJoke;

    @Test
    public void endpointReturnValidJokeTest() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        new EndpointJokesAsyncTask(new EndpointJokesAsyncTask.JokeAsyncTaskListener() {
            @Override
            public void onReceiveJoke(String joke) {
                resultJoke = joke;
                signal.countDown();
            }
        }).execute();

        signal.await();

        Log.i("JokeTest", "endpointReturnValidJokeTest: " + resultJoke);
        assertNotNull(resultJoke);
        Assert.assertNotEquals("Empty Joke returns", resultJoke, "");
    }

}