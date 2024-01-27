package com.example.weatherapp.models;

import java.util.List;

public class CurrentWeather {
    public Coord coord;
    public List<Weather> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    public Rain rain;
    public Clouds clouds;
    public long dt;
    public Sys sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;

    public CurrentWeather(CurrentWeather model){
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
}
