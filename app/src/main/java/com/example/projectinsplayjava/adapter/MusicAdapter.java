package com.example.projectinsplayjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectinsplayjava.R;
import com.example.projectinsplayjava.domains.Items;
import com.example.projectinsplayjava.domains.Music;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private ArrayList<Music> musicList;
    Context context;

    public MusicAdapter(ArrayList<Music> musicList) {

        this.musicList = musicList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_music, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Music music = musicList.get(position);
        holder.titleTextView.setText(music.getTitle());
        holder.artistTextView.setText(music.getArtist());
        holder.albumTextView.setText(music.getAlbum());
        loadImage(holder.imageTextView, music.getImage());
    }

    private void loadImage(ImageView imageView, String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.img); // Default image if URL is invalid
        }
    }

    @Override
    public int getItemCount(){
        return musicList.size();
    }

    public void setFilteredList( ArrayList<Music> filteredList) {
        this.musicList = filteredList;
        notifyDataSetChanged();
    }

    public static class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, artistTextView, albumTextView;
        ImageView imageTextView;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
            artistTextView = itemView.findViewById(R.id.textViewArtist);
            albumTextView = itemView.findViewById(R.id.textViewAlbum);
            imageTextView = itemView.findViewById(R.id.albumCoverImg);

        }
    }
}
