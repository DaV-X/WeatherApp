package com.example.weatherapp.daos;

import androidx.room.*;
import com.example.weatherapp.models.Coord;
import com.example.weatherapp.models.Sys;

import java.util.List;

@Dao
public interface SysDao {
    @Transaction
    @Insert
    public void addSys(Sys Sys);

    @Transaction
    @Update
    public void updateSys(Sys Sys);

    @Transaction
    @Delete
    public void deleteSys(Sys Sys);

    @Query("SELECT * FROM Sys")
    public List<Sys> getAllSyss();
    @Query("SELECT * FROM Sys WHERE savedWeatherId == :savedWeatherID")
    public Sys getSysBySavedWeatherId(int savedWeatherID);
    @Query("SELECT * FROM Sys WHERE id == :id")
    public Sys getSysById(int id);
}
