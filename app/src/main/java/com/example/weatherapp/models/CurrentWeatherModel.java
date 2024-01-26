package com.example.weatherapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CurrentWeatherModel {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

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
    @Getter
    class Coord {
        private double lon;
        private double lat;
    }
    @Getter
    class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }
    @Getter
    class Main {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;
        private int sea_level;
        private int grnd_level;
    }
    @Getter
    class Wind {
        private double speed;
        private double deg;
        private double gust;
    }
    @Getter
    class Rain {
        private double _1h;
    }
    @Getter
    class Clouds {
        private int all;
    }
    @Getter
    class Sys {
        private int type;
        private int id;
        private String country;
        private long sunrise;
        private long sunset;
    }
}
