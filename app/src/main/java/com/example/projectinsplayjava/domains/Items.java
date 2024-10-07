package com.example.projectinsplayjava.domains;

import java.io.Serializable;
import java.util.ArrayList;

public class Items implements Serializable {
   String image, name, artist;

   public Items() {

   }

   public Items(String image, String name, String artist) {
      this.image = image;
      this.name = name;
      this.artist = artist;
   }

   public String getImage() {
      return image;
   }

   public String getTitle() {
      return name;
   }

   public String getSinger() {
      return artist;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setArtist(String artist) {
      this.artist = artist;
   }
}

