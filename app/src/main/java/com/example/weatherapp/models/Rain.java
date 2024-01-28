package com.example.weatherapp.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import lombok.ToString;

@ToString
@Entity(foreignKeys = @ForeignKey(entity = SavedWeather.class, parentColumns = "id", childColumns = "savedWeatherId", onDelete = ForeignKey.CASCADE))
public class Rain {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double _1h;
    private double _3h;
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

    public double get_1h() {
        return _1h;
    }

    public void set_1h(double _1h) {
        this._1h = _1h;
    }

    public double get_3h() {
        return _3h;
    }

    public void set_3h(double _3h) {
        this._3h = _3h;
    }
}
