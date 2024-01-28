package com.example.weatherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.ToString;

@ToString
@Entity
public class Wind {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double speed;
    private double deg;
    private double gust;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    public double getGust() {
        return gust;
    }

    public void setGust(double gust) {
        this.gust = gust;
    }
}
