package com.example.weatherapp.ui.currentweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.weatherapp.databinding.FragmentCurrentweatherBinding;
import com.example.weatherapp.models.SettingsData;
import com.example.weatherapp.ui.settings.SettingsViewModel;

import androidx.lifecycle.ViewModelProvider;
import com.example.weatherapp.DatabaseInsatnce;
import com.example.weatherapp.WeatherDatabase;
import com.example.weatherapp.databinding.FragmentCurrentweatherBinding;
import com.example.weatherapp.models.CurrentWeather;
import com.example.weatherapp.models.SavedWeather;
import com.example.weatherapp.models.SavedWeatherWithWeather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

        WeatherDatabase database = DatabaseInsatnce.getInstance();
        SavedWeather lastAddedSavedWeather = database.getSavedWeatherDao().getLastAddedSavedWeather();

        if (lastAddedSavedWeather != null) {
            CurrentWeather currentWeather = new CurrentWeather(lastAddedSavedWeather, database.getCloudsDao().getCloudsBySavedWeatherId(lastAddedSavedWeather.getId()), database.getCoordDao().getCoordBySavedWeatherId(lastAddedSavedWeather.getId()), database.getMainDao().getMainBySavedWeatherId(lastAddedSavedWeather.getId()), database.getRainDao().getRainBySavedWeatherId(lastAddedSavedWeather.getId()), database.getSysDao().getSysBySavedWeatherId(lastAddedSavedWeather.getId()), database.getWeatherDao().getWeathersBySavedWeatherId(lastAddedSavedWeather.getId()), database.getWindDao().getWindBySavedWeatherId(lastAddedSavedWeather.getId()));
            viewModel.updateWeatherData(currentWeather);
        }

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
                if(settings.temperatureUnit.equals("Celsius")){
                    binding.textViewMinMax.setText("Min: " + weatherData.getMain().getTemp_min() + "°C" + "  " + "Max: " + weatherData.getMain().getTemp_max() + "°C");
                    binding.textViewTemperature.setText(weatherData.getMain().getTemp() + "°C");
                }else{
                    binding.textViewMinMax.setText("Min: " + settings.celsiusToFahrenheit(weatherData.getMain().getTemp_min()) + "°F" + "  " + "Max: " + settings.celsiusToFahrenheit(weatherData.getMain().getTemp_max()) + "°F");
                    binding.textViewTemperature.setText(settings.celsiusToFahrenheit(weatherData.getMain().getTemp()) + "°F");
                }

                binding.textViewHumidity.setText(weatherData.getMain().getHumidity() + "%");

                if(settings.windSpeedUnit.equals("m/s")){
                    binding.textViewWind.setText(weatherData.getWind().getSpeed() + " m/s");
                }else{
                    binding.textViewWind.setText(settings.msToKmh(weatherData.getWind().getSpeed()) + " km/h");
                }


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
