package com.example.weatherapp.models;

import java.util.List;

public class WeatherEntry {
    public long dt;
    public Main main;
    public List<Weather> weather;
    public Clouds clouds;
    public Wind wind;
    public int visibility;
    public double pop;
    public Rain rain;
    public Sys sys;
    public String dt_txt;
}
