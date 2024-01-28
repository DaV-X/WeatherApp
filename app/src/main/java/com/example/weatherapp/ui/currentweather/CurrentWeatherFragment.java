package com.example.weatherapp.ui.currentweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.databinding.FragmentCurrentweatherBinding;

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

        buttonSearch.setOnClickListener(v -> {
            String cityName = editTextCityName.getText().toString();
            if (!cityName.isEmpty()) {
                try {
                    viewModel.updateWeatherData(cityName);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        viewModel.getWeatherData().observe(getViewLifecycleOwner(), weatherData -> {
            if (weatherData != null) {
                binding.textViewCityName.setText(weatherData.getName());
                binding.textViewMinMax.setText("Min: " + weatherData.getMain().getTemp_min() + "°C" + "  " + "Max: " + weatherData.getMain().getTemp_max() + "°C");
                binding.textViewTemperature.setText(weatherData.getMain().getTemp() + "°C");
                binding.textViewHumidity.setText(weatherData.getMain().getHumidity() + "%");
                binding.textViewWind.setText(weatherData.getWind().getSpeed() + " m/s");
                if (weatherData.getRain() != null && weatherData.getRain().get_1h() > 0) {
                    binding.textViewRain.setText(weatherData.getRain().get_1h() + " mm");
                } else {
                    binding.textViewRain.setText("0 mm");
                }
                long timestamp = weatherData.getDt() * 1000L;
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM HH:mm", Locale.getDefault());
                String dateString = formatter.format(new Date(timestamp));
                binding.textViewDateTime.setText(dateString);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
