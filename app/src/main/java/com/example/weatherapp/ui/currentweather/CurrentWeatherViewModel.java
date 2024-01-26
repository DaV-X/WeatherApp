package com.example.weatherapp.ui.currentweather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.models.CurrentWeatherModel;
import com.example.weatherapp.services.WeatherApiService;

public class CurrentWeatherViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CurrentWeatherViewModel() {
        WeatherApiService weatherApiService = new WeatherApiService();
        // Pobierz dane na podstawie współrzędnych geograficznych
        CurrentWeatherModel Temp = weatherApiService.getWeatherByCoordinates(53.10, 23.10,"metric");

        String data = "Max Temp: " + Temp.main.temp_max +
                "\nMin Temp: " + Temp.main.temp_min +
                "\nCity Name: " + Temp.name;

        mText = new MutableLiveData<>();
        mText.setValue(data);
    }

    public LiveData<String> getText() {
        return mText;
    }
}