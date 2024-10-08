package com.example.projectinsplayjava.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectinsplayjava.R;
import com.example.projectinsplayjava.adapter.GenreAdapter;
import com.example.projectinsplayjava.adapter.MusicAdapter;
import com.example.projectinsplayjava.domains.Music;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenreFragment extends Fragment {

    private RecyclerView genreRecyclerView;
    private RecyclerView musicRecyclerView;
    private GenreAdapter genreAdapter;
    private MusicAdapter musicAdapter;
    private ArrayList<String> genreList;
    private ArrayList<Music> musicList;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;

    public GenreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre, container, false);

        musicRecyclerView = view.findViewById(R.id.recyclerViewRekomend);
        progressBar = view.findViewById(R.id.progressBarRekomend);

        // Set up Music RecyclerView
        musicList = new ArrayList<>();
        musicAdapter = new MusicAdapter(musicList);
        musicRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        musicRecyclerView.setAdapter(musicAdapter);

        // Load music for the selected genre
        String selectedGenre = getArguments().getString("selectedGenre");
        loadMusicForGenre(selectedGenre);

        return view;
    }

    private void loadMusicForGenre(String genre) {
        progressBar.setVisibility(View.VISIBLE);

        // Firebase reference for the selected genre
        databaseReference = FirebaseDatabase.getInstance().getReference(genre);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                musicList.clear(); // Clear current music list

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Music music = dataSnapshot.getValue(Music.class);
                    musicList.add(music);
                }

                musicAdapter.notifyDataSetChanged(); // Notify the adapter
                progressBar.setVisibility(View.GONE); // Hide the progress bar
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Failed to load music", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

