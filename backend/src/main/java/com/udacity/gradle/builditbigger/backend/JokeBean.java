package com.udacity.gradle.builditbigger.backend;

import com.example.android.morejokes.Jokes;

public class JokeBean {

    private Jokes myJoke;

    public String getJoke() {
        return myJoke.getJoke();
    }

    public void setJoke(Jokes joke) {
        myJoke = joke;
    }
}