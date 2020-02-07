package com.example.mymap.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mymap.data.MarkerInfo;

import java.util.List;


@Dao
public interface MarkerInfoDao {

    @Query("SELECT * FROM markerinfo")
    List<MarkerInfo> getAll();

    @Query("SELECT * FROM markerinfo WHERE id = :id")
    MarkerInfo getById(long id);

    @Insert
    void insert(MarkerInfo markerinfo);
}
