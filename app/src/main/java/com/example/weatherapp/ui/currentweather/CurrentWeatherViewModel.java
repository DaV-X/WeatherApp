package com.example.weatherapp.ui.currentweather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.weatherapp.models.CurrentWeatherModel;
import com.example.weatherapp.services.WeatherApiService;

public class CurrentWeatherViewModel extends ViewModel {
    private final MutableLiveData<CurrentWeatherModel> weatherData;
    private WeatherApiService weatherApiService;

    public CurrentWeatherViewModel() {
        weatherApiService = new WeatherApiService();
        weatherData = new MutableLiveData<>();
    }

    public void updateWeatherData(String cityName) {
        CurrentWeatherModel temp = weatherApiService.getWeatherByCityName(cityName, "metric");
        weatherData.setValue(temp);
    }

    public LiveData<CurrentWeatherModel> getWeatherData() {
        return weatherData;
    }
}
