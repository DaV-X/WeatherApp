package com.example.weatherapp.ui.longtermforecast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.databinding.FragmentLongtermforecastBinding;

public class LongTermForecastFragment extends Fragment {

    private FragmentLongtermforecastBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LongTermForecastViewModel viewModel = new ViewModelProvider(this).get(LongTermForecastViewModel.class);
        binding = FragmentLongtermforecastBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;

        viewModel.updateForecastData("Bialystok");


        viewModel.getForecastData().observe(getViewLifecycleOwner(), forecastData -> {
            if (forecastData != null) {
                StringBuilder dane = new StringBuilder();
                //forecastData.cnt jest iloscią pomiarów które są co 3 godziny a jest ich zazwyczaj 40(5dni *24h/3 = 40)
                for (int i = 0; i < forecastData.cnt; i++) {
                    dane.append(forecastData.list.get(i).dt_txt + "\n");
                    dane.append(forecastData.list.get(i).main.temp + " " + forecastData.list.get(i).wind.speed + "\n");
                }
                textView.setText(dane.toString());
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