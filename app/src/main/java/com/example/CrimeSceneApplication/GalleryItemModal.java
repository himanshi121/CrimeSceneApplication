package com.example.CrimeSceneApplication;

import android.net.Uri;

public class GalleryItemModal {
    private Uri image;
    //private String imageName;

    public GalleryItemModal(Uri image) {
        this.image = image;
       // this.imageName = imageName;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
/*
  //  public String getImageName() {
        return imageName;
    }*/

    /*public void setImageName(String imageName) {
        this.imageName = imageName;
    }*/
}
