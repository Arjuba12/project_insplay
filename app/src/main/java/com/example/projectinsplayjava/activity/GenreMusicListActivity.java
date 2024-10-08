package com.example.projectinsplayjava.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectinsplayjava.R;

public class GenreMusicListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_item);

        // Get the genre from the intent
        String genre = getIntent().getStringExtra("genre");

        // Use this genre to fetch the music list from Firebase or local storage
        // Display the music list for the selected genre
    }
}

