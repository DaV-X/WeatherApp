package com.example.weatherapp.daos;

import androidx.room.*;
import com.example.weatherapp.models.Coord;
import com.example.weatherapp.models.Weather;

import java.util.List;

@Dao
public interface WeatherDao {
    @Transaction
    @Insert
    public void addWeather(Weather Weather);

    @Transaction
    @Update
    public void updateWeather(Weather Weather);

    @Transaction
    @Delete
    public void deleteWeather(Weather Weather);

    @Query("SELECT * FROM Weather")
    public List<Weather> getAllWeathers();

    @Query("SELECT * FROM Weather WHERE savedWeatherId == :savedWeatherID")
    public List<Weather> getWeathersBySavedWeatherId(int savedWeatherID);

    @Query("SELECT * FROM Weather WHERE id == :id")
    public Weather getWeatherById(int id);
}
