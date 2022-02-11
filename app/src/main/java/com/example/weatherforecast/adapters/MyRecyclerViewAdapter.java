package com.example.weatherforecast.adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforecast.R;
import com.example.weatherforecast.data_model.Weather;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private Activity context;
    private int layoutID;
    private ArrayList<Weather> weatherArrayList;
    private MyItemClickLListener delegation;

    final String RAIN = "Rain";
    final String CLOUDS = "Clouds";
    final String CLEAR = "Clear";

    public MyRecyclerViewAdapter(Activity context, int layoutID, ArrayList<Weather> weatherArrayList) {
        this.context = context;
        this.layoutID = layoutID;
        this.weatherArrayList = weatherArrayList;
    }

    public void setDelegation(MyItemClickLListener delegation) {
        this.delegation = delegation;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        CardView view = (CardView) layoutInflater.inflate(viewType, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Get data from the personList at position
        Weather weather = weatherArrayList.get(position);

        ImageView image = new ImageView(context);
        if(weather.getWeatherItems().get(0).equals(CLOUDS)){
            Drawable drawable = context.getResources().getDrawable(R.drawable.clounds, context.getTheme());
            image.setImageDrawable(drawable);
            holder.imgWeather.setImageDrawable(image.getDrawable());
        }
        else if(weather.getWeatherItems().get(0).equals(RAIN)){
            Drawable drawable = context.getResources().getDrawable(R.drawable.rain, context.getTheme());
            image.setImageDrawable(drawable);
            holder.imgWeather.setImageDrawable(image.getDrawable());
        }
        else if(weather.getWeatherItems().get(0).equals(CLEAR)){
            Drawable drawable = context.getResources().getDrawable(R.drawable.clear, context.getTheme());
            image.setImageDrawable(drawable);
            holder.imgWeather.setImageDrawable(image.getDrawable());
        }else {
            Drawable drawable = context.getResources().getDrawable(R.drawable.unknown, context.getTheme());
            image.setImageDrawable(drawable);
            holder.imgWeather.setImageDrawable(image.getDrawable());
        }

        holder.edtNhietDo.setText("Max: " + (int) (weather.getMain().getNhietDoMax()+0.5f) + " Now: " + (int) (weather.getMain().getNhietDo()+0.5f) + " Min: " + (int) (weather.getMain().getNhietDoMin()+0.5f));
        holder.edtTocDoGio.setText(weather.getWind().getTocDoGio() + " km/h");
        holder.edtTamNhin.setText(weather.getTamNhinXa() + " m");
        holder.edtThoiGian.setText(weather.getThoiGian());

        // Event processing
        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(delegation != null){
//                    delegation.getWeatherInfomation(weather);
                }else {
//                    Toast.makeText(context, "You must delegation before!", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return weatherArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layoutID;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgWeather;
        TextView edtNhietDo;
        TextView edtTocDoGio;
        TextView edtTamNhin;
        TextView edtThoiGian;
        View.OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgWeather = itemView.findViewById(R.id.imgWeather);
            imgWeather.setOnClickListener(this);
            edtNhietDo = itemView.findViewById(R.id.edt1);
            edtNhietDo.setOnClickListener(this);
            edtTocDoGio = itemView.findViewById(R.id.edt2);
            edtTocDoGio.setOnClickListener(this);
            edtTamNhin = itemView.findViewById(R.id.edt3);
            edtTamNhin.setOnClickListener(this);
            edtThoiGian = itemView.findViewById(R.id.edt4);
            edtThoiGian.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onClickListener != null){
                onClickListener.onClick(v);
            }
        }
    }
    public interface MyItemClickLListener{
        public void getWeatherInfomation(Weather weather);
    }
}
