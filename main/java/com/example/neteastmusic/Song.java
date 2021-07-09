package com.example.neteastmusic;

public class Song {
    private long id;
    private String title;
    private String artist;
    private int src;
    private boolean favorite = false;

    public Song(long id, String title, String artist) {
        this(id,title,artist,0);
    }


    public Song(long id, String title, String artist, int src) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.src = src;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getSrc() {
        return src;
    }

    public boolean getFavorite() {
        return favorite;
    }
}
