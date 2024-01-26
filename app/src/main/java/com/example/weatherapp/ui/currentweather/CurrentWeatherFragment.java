package com.example.weatherapp.ui.currentweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.databinding.FragmentCurrentweatherBinding;
import com.example.weatherapp.models.CurrentWeatherModel;
import com.example.weatherapp.services.WeatherApiService;

public class CurrentWeatherFragment extends Fragment {

    private FragmentCurrentweatherBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CurrentWeatherViewModel homeViewModel =
                new ViewModelProvider(this).get(CurrentWeatherViewModel.class);

        binding = FragmentCurrentweatherBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}