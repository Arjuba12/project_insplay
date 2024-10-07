package com.example.projectinsplayjava.domains;

public class Music {
    private String title;
    private String artist;
    private String album;
    private String image;

    // Empty constructor needed for Firebase
    public Music() {

    }

    // Constructor with parameters
    public Music(String title, String artist, String album, String image) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.image = image;
    }

    // Getter and Setter methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
