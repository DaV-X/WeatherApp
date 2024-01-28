package com.example.weatherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.ToString;

@ToString
@Entity
public class Coord {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double lon;
    private double lat;
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

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
