package com.example.weatherapp.services;

import com.example.weatherapp.models.CurrentWeather;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherApiService {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "63757802efd6b27f2b1590b9202d964b";
    private WeatherApiInterface weatherApiInterface;

    public WeatherApiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApiInterface = retrofit.create(WeatherApiInterface.class);
    }

    public CurrentWeather getWeatherByCityName(String cityName, String units) {
        final CurrentWeather[] currentWeatherData = new CurrentWeather[1];

        CompletableFuture<CurrentWeather> futureWeather = CompletableFuture.supplyAsync(() -> {
            try {
                return weatherApiInterface.getWeatherByCity(cityName, API_KEY, units).execute().body();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        futureWeather.thenAccept(currentWeatherModel -> currentWeatherData[0] = currentWeatherModel)
                .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    return null;
                });

        futureWeather.join();
        return currentWeatherData[0];
    }

    public interface WeatherApiInterface {
        @GET("weather")
        Call<CurrentWeather> getWeatherByCity(
                @Query("q") String cityName,
                @Query("appid") String apiKey,
                @Query("units") String units
        );

        @GET("weather")
        Call<CurrentWeather> getWeatherByCoordinates(
                @Query("lat") double latitude,
                @Query("lon") double longitude,
                @Query("appid") String apiKey,
                @Query("units") String units
        );
    }
}
