package com.example.weatherapp.ui.longtermforecast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LongTermForecastViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LongTermForecastViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}