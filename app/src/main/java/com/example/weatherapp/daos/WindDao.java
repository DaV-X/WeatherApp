package com.example.weatherapp.daos;

import androidx.room.*;
import com.example.weatherapp.models.Wind;

import java.util.List;

@Dao
public interface WindDao {
    @Transaction
    @Insert
    public void addWind(Wind Wind);

    @Transaction
    @Update
    public void updateWind(Wind Wind);

    @Transaction
    @Delete
    public void deleteWind(Wind Wind);

    @Query("SELECT * FROM Wind")
    public List<Wind> getAllWinds();

    @Query("SELECT * FROM Wind WHERE id == :id")
    public Wind getWindById(int id);
}
