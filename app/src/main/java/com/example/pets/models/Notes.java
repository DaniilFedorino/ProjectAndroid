package com.example.pets.models;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.pets.R;

import java.io.Serializable;

@Entity (tableName = "notes")
public class Notes implements Serializable {

    @PrimaryKey (autoGenerate = true)
    int ID = 0;

    @ColumnInfo (name="title")
    String title = "";

    @ColumnInfo (name="breed")
    String breed = "";

    @ColumnInfo (name="age")
    String age = "";

    @ColumnInfo (name="passportData")
    String passportData = "";

    @ColumnInfo (name="vaccinations")
    String vaccinations = "";

    @ColumnInfo (name="image")
    byte[] image=new byte[0];

    //@ColumnInfo (name="image")
   // String image;

    @ColumnInfo (name="pinned")
    boolean pinned = false;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(String vaccinations) {
        this.vaccinations = vaccinations;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

   public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    /*public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }*/
}
