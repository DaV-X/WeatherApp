package com.example.weatherapp.models;

public class SettingsData {
    private static SettingsData instance;

    public boolean useCurrentLocation;
    public String cityName;
    public String temperatureUnit;
    public String windSpeedUnit;
    public boolean notificationsEnabled;
    public boolean autoUpdatesEnabled;

    private SettingsData(boolean useCurrentLocation, String cityName, String temperatureUnit, String windSpeedUnit, boolean notificationsEnabled, boolean autoUpdatesEnabled) {
        this.useCurrentLocation = useCurrentLocation;
        this.cityName = cityName;
        this.temperatureUnit = temperatureUnit;
        this.windSpeedUnit = windSpeedUnit;
        this.notificationsEnabled = notificationsEnabled;
        this.autoUpdatesEnabled = autoUpdatesEnabled;
    }

    public static SettingsData getInstance() {
        if (instance == null) {
            instance = new SettingsData(false, "Bialystok", "Celsius", "km/h", false, false);
        }
        return instance;
    }

    public double celsiusToFahrenheit(double celsius) {
        return Math.round((((celsius * 9.0) / 5.0) + 32)*100.0) / 100.0;
    }

    public double msToKmh(double metersPerSecond) {
        return Math.round(metersPerSecond * 3.6 * 100.0) / 100.0;
    }
}
