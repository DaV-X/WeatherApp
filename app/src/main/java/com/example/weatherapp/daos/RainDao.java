package com.example.weatherapp.daos;

import androidx.room.*;
import com.example.weatherapp.models.Rain;

import java.util.List;

@Dao
public interface RainDao {
    @Transaction
    @Insert
    public void addRain(Rain Rain);

    @Transaction
    @Update
    public void updateRain(Rain Rain);

    @Transaction
    @Delete
    public void deleteRain(Rain Rain);

    @Query("SELECT * FROM Rain")
    public List<Rain> getAllRains();

    @Query("SELECT * FROM Rain WHERE savedWeatherId == :savedWeatherID")
    public Rain getRainBySavedWeatherId(int savedWeatherID);

    @Query("SELECT * FROM Rain WHERE id == :id")
    public Rain getRainById(int id);
}
