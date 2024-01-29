package com.example.weatherapp.services;

import com.example.weatherapp.DatabaseInsatnce;
import com.example.weatherapp.WeatherDatabase;
import com.example.weatherapp.models.*;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WeatherApiService {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "63757802efd6b27f2b1590b9202d964b";
    private WeatherApiInterface weatherApiInterface;

    public WeatherApiService() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
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

        futureWeather.thenAccept(currentWeatherModel -> currentWeatherData[0] = currentWeatherModel).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });

        futureWeather.join();
        CurrentWeather current = currentWeatherData[0];
        saveToDb(current);
        return current;
    }

    private void saveToDb(CurrentWeather current) {
        WeatherDatabase db = DatabaseInsatnce.getInstance();
        SavedWeather savedWeather = new SavedWeather(current);
        Clouds clouds = current.getClouds();
        Coord coord = current.getCoord();
        Main main = current.getMain();
        Rain rain = current.getRain();
        Wind wind = current.getWind();
        Sys sys = current.getSys();
        List<Weather> weathers = current.getWeather();
        db.getSavedWeatherDao().addSavedWeather(savedWeather);
        savedWeather = db.getSavedWeatherDao().getLastAddedSavedWeather();
        int id = savedWeather.getId();

        clouds.setSavedWeatherId(id);
        coord.setSavedWeatherId(id);
        main.setSavedWeatherId(id);
        if (rain != null)
            rain.setSavedWeatherId(id);
        wind.setSavedWeatherId(id);
        sys.setSavedWeatherId(id);
        weathers.forEach(w -> w.setSavedWeatherId(id));

        clouds.setId(0);
        coord.setId(0);
        main.setId(0);
        if (rain != null)
            rain.setId(0);
        wind.setId(0);
        sys.setId(0);
        weathers.forEach(w -> w.setId(0));

        db.getCloudsDao().addClouds(clouds);
        db.getCoordDao().addCoord(coord);
        db.getMainDao().addMain(main);
        db.getWindDao().addWind(wind);
        db.getSysDao().addSys(sys);

        if (rain != null) {
            db.getRainDao().addRain(rain);
        }
    }

    public CurrentWeather getWeatherByCoordinates(double lat, double lon, String units) {
        final CurrentWeather[] currentWeatherData = new CurrentWeather[1];

        CompletableFuture<CurrentWeather> futureWeather = CompletableFuture.supplyAsync(() -> {
            try {
                return weatherApiInterface.getWeatherByCoordinates(lat, lon, API_KEY, units).execute().body();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        futureWeather.thenAccept(currentWeatherModel -> currentWeatherData[0] = currentWeatherModel).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });

        futureWeather.join();
        return currentWeatherData[0];
    }

    public LongTermForecast getForecastByCityName(String cityName, String units) {
        final LongTermForecast[] longTermForecastData = new LongTermForecast[1];

        CompletableFuture<LongTermForecast> futureWeather = CompletableFuture.supplyAsync(() -> {
            try {
                return weatherApiInterface.getForecastByCity(cityName, API_KEY, units).execute().body();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        futureWeather.thenAccept(currentWeatherModel -> longTermForecastData[0] = currentWeatherModel).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });

        futureWeather.join();
        return longTermForecastData[0];
    }

    public interface WeatherApiInterface {
        @GET("weather")
        Call<CurrentWeather> getWeatherByCity(@Query("q") String cityName, @Query("appid") String apiKey, @Query("units") String units);

        @GET("weather")
        Call<CurrentWeather> getWeatherByCoordinates(@Query("lat") double latitude, @Query("lon") double longitude, @Query("appid") String apiKey, @Query("units") String units);

        @GET("forecast")
        Call<LongTermForecast> getForecastByCity(@Query("q") String cityName, @Query("appid") String apiKey, @Query("units") String units);
    }
}
