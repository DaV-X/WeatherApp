package com.example.weatherapp.services;

import android.util.Log;
import com.example.weatherapp.models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HistoryService {
    private static HistoryService historyService = null;
    private static FileOutputStream fos = null;
    private static FileInputStream fis = null;
    private static String dir;

    private HistoryService(FileOutputStream Fos, FileInputStream Fis, String Dir) {
        fos = Fos;
        dir = Dir;
        fis = Fis;
    }

    public static void initService(FileOutputStream fos, FileInputStream fis, String dir) {
        if (historyService == null)
            historyService = new HistoryService(fos, fis, dir);
    }

    public static HistoryService getInstance() throws Exception {
        if (historyService != null)
            return historyService;
        throw new Exception("History service not initiated");
    }

    public void append(CurrentWeather currentWeatherData) throws Exception {
        try {
            Log.d("appending", "Appending to history file at: " + dir + "/history.txt");
            fos.write(currentWeatherData.toString().getBytes());
            fos.write('\n');
        } catch (Exception e) {
            Log.e("appending", "append failure: " + e);
        }
    }

    public static void load() throws IOException {
        try {
            HashMap<String, CurrentWeather> weatherList = new HashMap<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;

            while ((line = reader.readLine()) != null) {
                CurrentWeather currentWeather = currentWeatherFromString(line);
                if (currentWeather != null) {
                    weatherList.put(currentWeather.getName(), currentWeather);
                }
            }

            // Process the loaded weather data as needed (e.g., display, store in a variable, etc.)
            for (CurrentWeather weather : weatherList.values()) {
                Log.d("loading", "Loaded weather data: " + weather.toString());
            }

            reader.close();
        } catch (IOException e) {
            Log.e("loading", "Load failure: " + e);
            throw e; // Rethrow the exception to handle it appropriately in the calling code
        }
    }

    private static CurrentWeather currentWeatherFromString(String line) {
        Log.d("test", "currentWeatherFromString: " + line);


        return null;
    }

    public static void save() throws IOException {
        fos.close();
    }

}
