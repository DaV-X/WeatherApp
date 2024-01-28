package com.example.weatherapp.ui.currentweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.weatherapp.databinding.FragmentCurrentweatherBinding;
import com.example.weatherapp.models.SettingsData;
import com.example.weatherapp.ui.settings.SettingsViewModel;

import androidx.lifecycle.ViewModelProvider;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CurrentWeatherFragment extends Fragment {
    private FragmentCurrentweatherBinding binding;
    private EditText editTextCityName;
    private Button buttonSearch;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CurrentWeatherViewModel viewModel = new ViewModelProvider(this).get(CurrentWeatherViewModel.class);
        binding = FragmentCurrentweatherBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        editTextCityName = binding.editTextCityName;
        buttonSearch = binding.buttonSearch;

        //load saved settings
        SettingsData settings = SettingsData.getInstance();
        if(!settings.useCurrentLocation)
            editTextCityName.setText(settings.cityName);

        buttonSearch.setOnClickListener(v -> {
            String cityName = editTextCityName.getText().toString();
            if (!cityName.isEmpty()) {
                viewModel.updateWeatherData(cityName);
            }
        });

        viewModel.getWeatherData().observe(getViewLifecycleOwner(), weatherData -> {
            if (weatherData != null) {
                binding.textViewCityName.setText(weatherData.name);
                if(settings.temperatureUnit.equals("Celsius")){
                    binding.textViewMinMax.setText("Min: " + weatherData.main.temp_min + "°C" + "  " + "Max: " + weatherData.main.temp_max + "°C");
                    binding.textViewTemperature.setText(weatherData.main.temp + "°C");
                }else{
                    binding.textViewMinMax.setText("Min: " + settings.celsiusToFahrenheit(weatherData.main.temp_min) + "°F" + "  " + "Max: " + settings.celsiusToFahrenheit(weatherData.main.temp_max) + "°F");
                    binding.textViewTemperature.setText(settings.celsiusToFahrenheit(weatherData.main.temp) + "°F");
                }

                binding.textViewHumidity.setText(weatherData.main.humidity + "%");

                if(settings.windSpeedUnit.equals("m/s")){
                    binding.textViewWind.setText(weatherData.wind.speed + " m/s");
                }else{
                    binding.textViewWind.setText(settings.msToKmh(weatherData.wind.speed) + " km/h");
                }


                if (weatherData.rain != null && weatherData.rain._1h > 0) {
                    binding.textViewRain.setText(weatherData.rain._1h + " mm");
                } else {
                    binding.textViewRain.setText("0 mm");
                }
                long timestamp = weatherData.dt * 1000L;
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM HH:mm", Locale.getDefault());
                String dateString = formatter.format(new Date(timestamp));
                binding.textViewDateTime.setText(dateString);
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        editTextCityName.setText(SettingsData.getInstance().cityName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
