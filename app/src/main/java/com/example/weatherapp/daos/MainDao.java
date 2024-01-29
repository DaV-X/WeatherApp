package com.example.weatherapp.daos;

import androidx.room.*;
import com.example.weatherapp.models.Coord;
import com.example.weatherapp.models.Main;

import java.util.List;

@Dao
public interface MainDao {
    @Transaction
    @Insert
    public void addMain(Main Main);

    @Transaction
    @Update
    public void updateMain(Main Main);

    @Transaction
    @Delete
    public void deleteMain(Main Main);

    @Query("SELECT * FROM Main")
    public List<Main> getAllMains();

    @Query("SELECT * FROM Main WHERE savedWeatherId == :savedWeatherID")
    public Main getMainBySavedWeatherId(int savedWeatherID);

    @Query("SELECT * FROM Main WHERE id == :id")
    public Main getMainById(int id);
}
