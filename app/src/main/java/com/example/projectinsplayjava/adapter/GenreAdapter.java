package com.example.projectinsplayjava.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectinsplayjava.R;
import com.example.projectinsplayjava.activity.GenreFragment;
import com.example.projectinsplayjava.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    private List<String> genreList;
    private OnGenreClickListener onGenreClickListener;
    private ActivityMainBinding binding;

    // Interface to handle click events
    public interface OnGenreClickListener {
        void onGenreClick(String genre);
    }

    public GenreAdapter(ArrayList<String> genreList, OnGenreClickListener listener) {
        this.genreList = genreList;
        this.onGenreClickListener = listener;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_item, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        String genre = genreList.get(position);
        holder.genreTitle.setText(genre);

//         Handle the click event
        holder.itemView.setOnClickListener(v -> {
            onGenreClickListener.onGenreClick(genre);
        });
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public static class GenreViewHolder extends RecyclerView.ViewHolder {
        TextView genreTitle;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            genreTitle = itemView.findViewById(R.id.genreName);  // Assuming you have a TextView for the genre name in item_genre.xml
        }
    }
}

