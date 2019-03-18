package com.example.neteastmusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter (Context context, ArrayList<Song> songs) {
        super(context,0, songs);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_music_item, parent, false);
        }

        Song currentSong = getItem(position);

        //get title and artist views
        TextView titleView = (TextView)convertView.findViewById(R.id.song_title);
        TextView artistView = (TextView)convertView.findViewById(R.id.song_artist);

        return convertView;
    }

}
