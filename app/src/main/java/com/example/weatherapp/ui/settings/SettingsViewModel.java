package com.example.weatherapp.ui.settings;
import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.weatherapp.models.SettingsData;


public class SettingsViewModel extends AndroidViewModel {
    private final SharedPreferences sharedPreferences;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        sharedPreferences = application.getSharedPreferences("AppSettings", Application.MODE_PRIVATE);
    }

    public void saveBooleanSetting(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBooleanSetting(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void saveStringSetting(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getStringSetting(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void loadSettings(){
        SettingsData settings = SettingsData.getInstance();
        settings.useCurrentLocation = sharedPreferences.getBoolean("useCurrentLocation", true);
        settings.cityName = sharedPreferences.getString("cityName", "");
        settings.temperatureUnit = sharedPreferences.getString("temperatureUnit", "Celsius");
        settings.windSpeedUnit = sharedPreferences.getString("windSpeedUnit", "km/h");
        settings.notificationsEnabled = sharedPreferences.getBoolean("notifications", false);
        settings.autoUpdatesEnabled = sharedPreferences.getBoolean("autoUpdates", false);
    }
}
