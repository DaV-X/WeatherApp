package com.example.weatherapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.models.SettingsData;
import com.example.weatherapp.models.WeatherEntry;

import java.util.List;
import java.util.function.Function;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<WeatherEntry> weatherEntries;
    private Function<String, String> dateFormatter;

    public WeatherAdapter(List<WeatherEntry> weatherEntries, Function<String, String> dateFormatter) {
        this.weatherEntries = weatherEntries;
        this.dateFormatter = dateFormatter;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_entry, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherEntry entry = weatherEntries.get(position);
        String formattedDate = dateFormatter.apply(entry.dt_txt);
        holder.tvDateTime.setText(formattedDate);

        //load settings
        SettingsData settings = SettingsData.getInstance();


        if(settings.temperatureUnit.equals("Celsius")){
            holder.tvTemperature.setText("Temperature: " + entry.main.temp + "°C");
            holder.tvFeelTemp.setText("Feels-Like Temperature: " + entry.main.feels_like + "°C");
        }else{
            holder.tvTemperature.setText("Temperature: " + settings.celsiusToFahrenheit(entry.main.temp) + "°F");
            holder.tvFeelTemp.setText("Feels-Like Temperature: " + settings.celsiusToFahrenheit(entry.main.feels_like) + "°F");
        }

        if(settings.windSpeedUnit.equals("m/s")){
            holder.tvWindSpeed.setText("Wind Speed: " + entry.wind.speed + " m/s");
        }else{
            holder.tvWindSpeed.setText("Wind Speed: " + settings.msToKmh(entry.wind.speed) + " km/h");
        }

        holder.tvPressure.setText("Pressure: " + entry.main.pressure + " hPa");

    }

    @Override
    public int getItemCount() {
        return weatherEntries.size();
    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView tvDateTime, tvTemperature, tvWindSpeed, tvPressure, tvFeelTemp;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvTemperature = itemView.findViewById(R.id.tvTemperature);
            tvWindSpeed = itemView.findViewById(R.id.tvWindSpeed);
            tvPressure = itemView.findViewById(R.id.tvPressure);
            tvFeelTemp = itemView.findViewById(R.id.tvFeelTemp);
        }
    }
}
