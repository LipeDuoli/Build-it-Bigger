package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.joketeller.JokeActivity;


public class MainActivity extends AppCompatActivity implements EndpointJokesAsyncTask.JokeAsyncTaskListener {

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.instructions_progress_bar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        new EndpointJokesAsyncTask(this).execute();
    }


    @Override
    public void onReceiveJoke(String joke) {
        mProgressBar.setVisibility(View.GONE);
        if (joke != null && !joke.isEmpty()) {
            startJokeActivity(joke);
        } else {
            Toast.makeText(this, getString(R.string.failed_get_jokes),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void startJokeActivity(String joke) {
        Intent jokeIntent = new Intent(this, JokeActivity.class);
        jokeIntent.putExtra(JokeActivity.JOKE_EXTRA, joke);
        startActivity(jokeIntent);
    }
}
