package com.example.weatherforecast.data_model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherWrapper<T> {
    @SerializedName("list")
    public ArrayList<T> items;
}
