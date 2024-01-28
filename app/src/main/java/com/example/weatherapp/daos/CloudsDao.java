package com.example.weatherapp.daos;

import androidx.room.*;
import com.example.weatherapp.models.Clouds;

import java.util.List;

@Dao
public interface CloudsDao {
    @Transaction
    @Insert
    public void addClouds(Clouds Clouds);

    @Transaction
    @Update
    public void updateClouds(Clouds Clouds);

    @Transaction
    @Delete
    public void deleteClouds(Clouds Clouds);

    @Query("SELECT * FROM Clouds")
    public List<Clouds> getAllCloudss();

    @Query("SELECT * FROM Clouds WHERE id == :id")
    public Clouds getCloudsById(int id);
}
