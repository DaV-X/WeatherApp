package com.example.weatherapp.ui.currentweather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CurrentWeatherViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CurrentWeatherViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}