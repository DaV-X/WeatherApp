package com.example.weatherapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseInsatnce {
    private static WeatherDatabase weatherDatabase = null;

    private DatabaseInsatnce() {
    }

    public static WeatherDatabase getInstance() {
        if (weatherDatabase == null) throw new RuntimeException("Not initialized DATABASE");
        return weatherDatabase;
    }

    public static void Initialize(Context context) {
        weatherDatabase = Room.databaseBuilder(context, WeatherDatabase.class, "weather_database").fallbackToDestructiveMigration().addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        // Handle onCreate
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        // Handle onOpen
                    }
                }).allowMainThreadQueries() // For demonstration purposes only; use AsyncTask or background thread for database operations
                .build();
    }
}
