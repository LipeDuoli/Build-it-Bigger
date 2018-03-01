package com.example.android.joketeller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "jokeExtra";
    private String mJoke = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if (getIntent().hasExtra(JOKE_EXTRA)) {
            mJoke = getIntent().getStringExtra(JOKE_EXTRA);
        }

        JokeFragment jokeFragment = JokeFragment.newInstance(mJoke);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, jokeFragment)
                .commit();
    }
}
