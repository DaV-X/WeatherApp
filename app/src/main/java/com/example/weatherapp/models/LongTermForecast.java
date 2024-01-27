package com.example.weatherapp.models;

import java.util.List;

public class LongTermForecast {
    public String cod;
    public int message;
    public int cnt;
    public List<WeatherEntry> list;
    public City city;
}
