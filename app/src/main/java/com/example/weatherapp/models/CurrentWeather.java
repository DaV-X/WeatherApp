package com.example.weatherapp.models;

import lombok.ToString;

import java.util.List;

@ToString
public class CurrentWeather {
    private int id;
    private Coord coord;
    private List<Weather> weather;
    private Wind wind;
    private Main main;
    private Sys sys;
    private Rain rain;
    private Clouds clouds;
    private long dt;
    private int visibility;
    private int timezone;
    private int cod;
    private String name;
    private String base;

    public CurrentWeather(CurrentWeather model) {
        coord = model.coord;
        weather = model.weather;
        base = model.base;
        main = model.main;
        visibility = model.visibility;
        wind = model.wind;
        rain = model.rain;
        clouds = model.clouds;
        dt = model.dt;
        sys = model.sys;
        timezone = model.timezone;
        id = model.id;
        name = model.name;
        cod = model.cod;
    }

    public CurrentWeather(SavedWeather savedWeather, Clouds clouds, Coord coord, Main main, Rain rain, Sys sys, List<Weather> weather, Wind wind) {
        this.coord = coord;
        this.clouds = clouds;
        this.main = main;
        this.rain = rain;
        this.sys = sys;
        this.weather = weather;
        this.wind = wind;
        this.cod = savedWeather.getCod();
        this.dt = savedWeather.getDt();
        this.visibility = savedWeather.getVisibility();
        this.name = savedWeather.getName();
        this.base = savedWeather.getBase();
        this.timezone = savedWeather.getTimezone();
        this.id = savedWeather.getId();
    }


    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
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

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
