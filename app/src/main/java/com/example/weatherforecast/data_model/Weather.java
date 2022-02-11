package com.example.weatherforecast.data_model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Weather {
    @SerializedName("main")
    Main main;
    @SerializedName("weather")
    ArrayList<WeatherItem> weatherItems = new ArrayList<WeatherItem>();
    @SerializedName("wind")
    Wind wind;

    @SerializedName("visibility")
    int tamNhinXa;
    @SerializedName("dt_txt")
    String thoiGian;

    public Weather(Main main, ArrayList<WeatherItem> weatherItems, Wind wind, int tamNhinXa, String thoiGian) {
        this.main = main;
        this.weatherItems = weatherItems;
        this.wind = wind;
        this.tamNhinXa = tamNhinXa;
        this.thoiGian = thoiGian;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ArrayList<WeatherItem> getWeatherItems() {
        return weatherItems;
    }

    public void setWeatherItems(ArrayList<WeatherItem> weatherItems) {
        this.weatherItems = weatherItems;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getTamNhinXa() {
        return tamNhinXa;
    }

    public void setTamNhinXa(int tamNhinXa) {
        this.tamNhinXa = tamNhinXa;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    //Define sub-classes
    public static class WeatherItem {
        @SerializedName("main")
        String weather;

        public WeatherItem(String weather) {
            this.weather = weather;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }
    }
    public static class Main {
        @SerializedName("temp")
        float nhietDo;
        @SerializedName("temp_max")
        float nhietDoMax;
        @SerializedName("temp_min")
        float nhietDoMin;

        public Main(float nhietDo, float nhietDoMax, float nhietDoMin) {
            this.nhietDo = nhietDo;
            this.nhietDoMax = nhietDoMax;
            this.nhietDoMin = nhietDoMin;
        }

        public float getNhietDo() {
            return (nhietDo - 273.15f);
        }

        public void setNhietDo(float nhietDo) {
            this.nhietDo = nhietDo;
        }

        public float getNhietDoMax() {
            return (nhietDoMax - 273.15f);
        }

        public void setNhietDoMax(float nhietDoMax) {
            this.nhietDoMax = nhietDoMax;
        }

        public float getNhietDoMin() {
            return (nhietDoMin - 273.15f);
        }

        public void setNhietDoMin(float nhietDoMin) {
            this.nhietDoMin = nhietDoMin;
        }
    }
    public static class Wind {
        @SerializedName("speed")
        float tocDoGio;

        public Wind(float tocDoGio) {
            this.tocDoGio = tocDoGio;
        }

        public float getTocDoGio() {
            return tocDoGio;
        }

        public void setTocDoGio(float tocDoGio) {
            this.tocDoGio = tocDoGio;
        }
    }
}
