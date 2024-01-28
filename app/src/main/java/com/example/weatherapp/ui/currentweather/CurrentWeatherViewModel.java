package com.example.weatherapp.ui.currentweather;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.models.CurrentWeather;
import com.example.weatherapp.services.WeatherApiService;

public class CurrentWeatherViewModel extends ViewModel {
    private final MutableLiveData<CurrentWeather> weatherData;
    private WeatherApiService weatherApiService;

    public CurrentWeatherViewModel() {
        weatherApiService = new WeatherApiService();
        weatherData = new MutableLiveData<>();
    }


    public void updateWeatherData(String cityName) {
        try {
            weatherData.setValue(weatherApiService.getWeatherByCityName(cityName, "metric"));
        } catch (Exception e) {
            Log.e("Gather_weather", e.toString());
        }
    }

    public LiveData<CurrentWeather> getWeatherData() {
        return weatherData;
    }
}
