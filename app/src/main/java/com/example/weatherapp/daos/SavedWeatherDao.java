package com.example.weatherapp.daos;

import androidx.room.*;
import com.example.weatherapp.models.SavedWeather;
import com.example.weatherapp.models.SavedWeatherWithWeather;

import java.util.List;

@Dao
public interface SavedWeatherDao {
    @Transaction
    @Insert
    public void addSavedWeather(SavedWeather savedWeather);

    @Transaction
    @Update
    public void updateSavedWeather(SavedWeather savedWeather);

    @Transaction
    @Delete
    public void deleteSavedWeather(SavedWeather savedWeather);

    @Query("SELECT * FROM savedweather")
    public List<SavedWeather> getAllSavedWeathers();

    @Query("SELECT * FROM savedweather WHERE id == :id")
    public SavedWeather getSavedWeatherById(int id);

    @Query("SELECT * FROM savedweather")
    public List<SavedWeatherWithWeather> getSavedWeatherWithWeather();
}
