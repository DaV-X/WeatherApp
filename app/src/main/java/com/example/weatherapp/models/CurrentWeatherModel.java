package com.example.weatherapp.models;

import java.util.List;

public class CurrentWeatherModel {
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

    public CurrentWeatherModel(CurrentWeatherModel model){
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
    public class Coord {
        public double lon;
        public double lat;
    }
    public class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
    }
    public class Main {
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int humidity;
        public int sea_level;
        public int grnd_level;
    }
    public class Wind {
        public double speed;
        public double deg;
        public double gust;
    }
    public class Rain {
        public double _1h;

    }
    public class Clouds {
        public int all;
    }
    public class Sys {
        public int type;
        public int id;
        public String country;
        public long sunrise;
        public long sunset;
    }
}
