package com.example.mymap.presenter;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "markerinfo")
public class MarkerInfo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Double latitude;
    private Double longitude;
    private String country;
    private String administrativeArea;
    private String dateWeather;
    private Double maxTemp;
    private Double minTemp;
    private String dayIconPhrase;
    private String nightIconPhrase;
    private String key;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAdministrativeArea() {
        return administrativeArea;
    }

    public void setAdministrativeArea(String administrativeArea) {
        this.administrativeArea = administrativeArea;
    }

    public String getDateWeather() {

        return dateWeather;
    }

    public void setDateWeather(String dateWeather) {
        this.dateWeather = dateWeather;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public String getDayIconPhrase() {
        return dayIconPhrase;
    }

    public void setDayIconPhrase(String dayIconPhrase) {
        this.dayIconPhrase = dayIconPhrase;
    }

    public String getNightIconPhrase() {
        return nightIconPhrase;
    }

    public void setNightIconPhrase(String nightIconPhrase) {
        this.nightIconPhrase = nightIconPhrase;
    }

}
