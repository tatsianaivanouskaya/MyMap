package com.example.mymap.data;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "markerinfo")
public class MarkerInfo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String latitude;
    private String longitude;
    private String country;
    private String administrativeArea;
    private String dateWeather;
    private String maxTemp;
    private String minTemp;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
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

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
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

    @NonNull
    @Override
    public String toString() {
        return "широта: " + this.latitude + "\n"
                + "долгота: " + this.longitude + "\n"
                + "страна: " + this.country + "\n"
                + "        " + this.administrativeArea + "\n"
                + "дата: " + this.dateWeather + "\n"
                + "днем: " + this.maxTemp + " " + this.dayIconPhrase + "\n"
                + "ночью: " + this.minTemp + " " + this.nightIconPhrase;
    }
}
