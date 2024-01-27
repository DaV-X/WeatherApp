package com.example.weatherapp.ui.longtermforecast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.weatherapp.models.LongTermForecast;
import com.example.weatherapp.services.WeatherApiService;

public class LongTermForecastViewModel extends ViewModel {
    private final MutableLiveData<LongTermForecast> weatherData;
    private WeatherApiService weatherApiService;

    public LongTermForecastViewModel() {
        weatherApiService = new WeatherApiService();
        weatherData = new MutableLiveData<>();
    }

    public void updateForecastData(String cityName) {
        LongTermForecast temp = weatherApiService.getForecastByCityName(cityName, "metric");
        weatherData.setValue(temp);
    }

    public LiveData<LongTermForecast> getForecastData() {
        return weatherData;
    }
}