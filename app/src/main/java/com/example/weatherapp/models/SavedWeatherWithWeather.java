package com.example.weatherapp.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SavedWeatherWithWeather {
    @Embedded
    private SavedWeather savedWeather;
    @Relation(parentColumn = "id", entityColumn = "savedWeatherId")
    private List<Weather> weathers;
    @Relation(parentColumn = "id", entityColumn = "savedWeatherId")
    private Clouds clouds;
    @Relation(parentColumn = "id", entityColumn = "savedWeatherId")
    private Main main;
    @Relation(parentColumn = "id", entityColumn = "savedWeatherId")
    private Coord coord;
    @Relation(parentColumn = "id", entityColumn = "savedWeatherId")
    private Sys sys;
    @Relation(parentColumn = "id", entityColumn = "savedWeatherId")
    private Wind wind;
    @Relation(parentColumn = "id", entityColumn = "savedWeatherId")
    private Rain rain;

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

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
