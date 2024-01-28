package com.example.weatherapp.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import lombok.ToString;

@ToString
@Entity(foreignKeys = @ForeignKey(entity = SavedWeather.class, parentColumns = "id", childColumns = "savedWeatherId", onDelete = ForeignKey.CASCADE))
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
