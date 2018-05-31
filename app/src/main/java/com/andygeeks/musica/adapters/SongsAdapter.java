package com.andygeeks.musica.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andygeeks.musica.R;
import com.andygeeks.musica.pojo.SongsPojo;

import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.MyViewHolder> {

    private ArrayList<SongsPojo> songs;

    public SongsAdapter(ArrayList<SongsPojo> songs) {
        this.songs = songs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.songImage.setImageDrawable(R.drawable.ic_my_music);
        holder.title.setText(songs.get(position).getTitle());
        holder.artist.setText(songs.get(position).getArtist());
        holder.duration.setText(songs.get(position).getDuration());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView songImage;
        private TextView title;
        private TextView artist;
        private TextView duration;

        public MyViewHolder(View view) {
            super(view);
            songImage = view.findViewById(R.id.img_song);
            title = view.findViewById(R.id.title);
            artist = view.findViewById(R.id.artist);
            duration = view.findViewById(R.id.duration);
        }
    }
}
