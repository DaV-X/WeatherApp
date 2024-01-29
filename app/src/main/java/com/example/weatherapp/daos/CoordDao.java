package com.example.weatherapp.daos;

import androidx.room.*;
import com.example.weatherapp.models.Coord;

import java.util.List;

@Dao
public interface CoordDao {
    @Transaction
    @Insert
    public void addCoord(Coord Coord);

    @Transaction
    @Update
    public void updateCoord(Coord Coord);

    @Transaction
    @Delete
    public void deleteCoord(Coord Coord);

    @Query("SELECT * FROM Coord")
    public List<Coord> getAllCoords();

    @Query("SELECT * FROM Coord WHERE savedWeatherId == :savedWeatherID")
    public Coord getCoordBySavedWeatherId(int savedWeatherID);

    @Query("SELECT * FROM Coord WHERE id == :id")
    public Coord getCoordById(int id);
}
