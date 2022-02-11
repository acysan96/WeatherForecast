package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.weatherforecast.adapters.MyRecyclerViewAdapter;
import com.example.weatherforecast.data_model.Weather;
import com.example.weatherforecast.data_model.WeatherAPI;
import com.example.weatherforecast.data_model.WeatherWrapper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    // Khai bao bien
    ArrayList<Weather> list;
    WeatherAPI weatherAPI;
    String city;
    private MyRecyclerViewAdapter weaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<Weather>();
        weaAdapter = new MyRecyclerViewAdapter(this, R.layout.recyclerview_item, list);
        RecyclerView recyclerView = findViewById(R.id.list);
        Spinner spinner = findViewById(R.id.spThanhPho);
//        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinner_thanhpho));
        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(this, R.layout.spin, R.id.text ,getResources().getStringArray(R.array.spinner_thanhpho));
        spinner.setAdapter(spAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = (String) parent.getSelectedItem();
                getWeatherData(city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(weaAdapter);
    }

    private void getWeatherData(String city){
        list.clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherAPI = retrofit.create(WeatherAPI.class);
        Call<WeatherWrapper<Weather>> call = weatherAPI.getWeathers(city, "8951d09b5580f4f349d866e30678ccb5");
        call.enqueue(new Callback<WeatherWrapper<Weather>>() {
            @Override
            public void onResponse(Call<WeatherWrapper<Weather>> call, Response<WeatherWrapper<Weather>> response) {
                if (response.isSuccessful()){
                    WeatherWrapper<Weather> weathers = response.body();
                    assert weathers != null;
                    list.addAll(weathers.items);
                    weaAdapter.notifyDataSetChanged();
                    Log.d("test", list.size() + "");
                }
            }

            @Override
            public void onFailure(Call<WeatherWrapper<Weather>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private MyRecyclerViewAdapter.MyItemClickLListener itemClickListener = new MyRecyclerViewAdapter.MyItemClickLListener() {

        @Override
        public void getWeatherInfomation(Weather weather) {
            Toast.makeText(MainActivity.this, weather.toString(), Toast.LENGTH_SHORT).show();
        }
    };
}