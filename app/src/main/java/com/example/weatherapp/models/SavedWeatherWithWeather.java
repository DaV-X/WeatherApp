package com.example.weatherapp.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SavedWeatherWithWeather {
    @Embedded
    private SavedWeather savedWeather;
    @Relation(parentColumn = "id", entityColumn = "savedWeatherId")
    private List<Weather> weathers;

    public SavedWeather getSavedWeather() {
        return savedWeather;
    }

    public void setSavedWeather(SavedWeather savedWeather) {
        this.savedWeather = savedWeather;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }
}
