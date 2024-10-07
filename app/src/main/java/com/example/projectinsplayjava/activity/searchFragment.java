package com.example.projectinsplayjava.activity;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projectinsplayjava.R;
import com.example.projectinsplayjava.adapter.MusicAdapter;
import com.example.projectinsplayjava.adapter.MusicListAdapter;
import com.example.projectinsplayjava.domains.Items;
import com.example.projectinsplayjava.domains.Music;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class searchFragment extends Fragment {

    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;
    private ArrayList<Music> items;
    private DatabaseReference databaseReference;
    private SearchView searchView;

    public searchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewRekomend);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        items = new ArrayList<>();
        musicAdapter = new MusicAdapter(items);
        recyclerView.setAdapter(musicAdapter);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("music");

        // Fetch data from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Music music = snapshot.getValue(Music.class);
                    items.add(music);
                }
                ProgressBar progressBar = view.findViewById(R.id.progressBarRekomend);
                progressBar.setVisibility(View.GONE);
                musicAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });

        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterList(query);
                hideKeyboard();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        return view;
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        View view = getActivity().getCurrentFocus();

        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void filterList(String text) {
        ArrayList<Music> filteredList = new ArrayList<>();

        if (text.isEmpty()) {
            filteredList.addAll(items);
        } else {
            for (Music music : items) {
                if (music.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(music);
                }
            }

            if (filteredList.isEmpty() && !text.isEmpty()) {
                // Handle empty list
                Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
            } else {
                musicAdapter.setFilteredList(filteredList);
            }
        }
        musicAdapter.setFilteredList(filteredList);

    }

}
