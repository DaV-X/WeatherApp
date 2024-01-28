package com.example.weatherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.ToString;

@ToString
@Entity
public class Clouds {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int all;
    private int savedWeatherId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSavedWeatherId() {
        return savedWeatherId;
    }

    public void setSavedWeatherId(int savedWeatherId) {
        this.savedWeatherId = savedWeatherId;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
