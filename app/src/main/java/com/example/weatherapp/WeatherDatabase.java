package com.example.weatherapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.weatherapp.daos.*;
import com.example.weatherapp.models.*;

@Database(entities = {Clouds.class, Coord.class, Main.class, Rain.class, SavedWeather.class, Sys.class, Weather.class, Wind.class}, version = 2)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract CloudsDao getCloudsDao();

    public abstract CoordDao getCoordDao();

    public abstract MainDao getMainDao();

    public abstract RainDao getRainDao();

    public abstract SavedWeatherDao getSavedWeatherDao();

    public abstract SysDao getSysDao();

    public abstract WeatherDao getWeatherDao();

    public abstract WindDao getWindDao();

    public static final String SCHEMA_EXPORT_DIRECTORY = "/data/data/com.example.weatherapp";

    public static final String SCHEMA_FILE_NAME = "weather_database_schema.json";
}
