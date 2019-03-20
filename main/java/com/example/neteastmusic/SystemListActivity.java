package com.example.neteastmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class SystemListActivity extends AppCompatActivity {

    ArrayList<Song> systemSongList;
    private ListView systemSongView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        systemSongList = getSystemSongList();

        systemSongView = (ListView) findViewById(R.id.song_list);
        SongAdapter systemSongAdapter = new SongAdapter(this, systemSongList);
        systemSongView.setAdapter(systemSongAdapter);

        //Set onClickItemListener on songView
        systemSongView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SystemListActivity.this, systemSongList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent playMusicIntent = new Intent(SystemListActivity.this, MusicPlayingActivity.class);
                playMusicIntent.putExtra("source", systemSongList.get(position).getSrc());
                startActivity(playMusicIntent);

            }
        });
    }

    private ArrayList<Song> getSystemSongList () {
        ArrayList<Song> list = new ArrayList<>();

        list.add(new Song(1, "music1", "artist1", R.raw.music1));
        list.add(new Song(2, "music2", "artist2", R.raw.music2));
        list.add(new Song(3, "music3", "artist3", R.raw.music3));

        return list;
    }


}
