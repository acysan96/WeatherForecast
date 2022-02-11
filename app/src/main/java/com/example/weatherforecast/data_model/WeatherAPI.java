package com.example.weatherforecast.data_model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    String BASE_URL = "https://api.openweathermap.org/";
    @GET("data/2.5/forecast?")
    Call<WeatherWrapper<Weather>> getWeathers(@Query("q") String city, @Query("appid") String key);
}
