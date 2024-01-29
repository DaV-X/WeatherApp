package com.example.weatherapp.models;

import java.util.List;

import lombok.ToString;

@ToString
public class LongTermForecast {
    private String cod;
    private int message;
    private int cnt;
    private List<WeatherEntry> list;
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherEntry> getList() {
        return list;
    }

    public void setList(List<WeatherEntry> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
