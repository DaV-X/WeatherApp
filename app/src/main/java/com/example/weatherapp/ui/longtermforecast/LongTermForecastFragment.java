package com.example.weatherapp.ui.longtermforecast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.databinding.FragmentLongtermforecastBinding;
import com.example.weatherapp.models.WeatherEntry;
import com.example.weatherapp.ui.adapters.WeatherAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class LongTermForecastFragment extends Fragment {

    private FragmentLongtermforecastBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LongTermForecastViewModel viewModel = new ViewModelProvider(this).get(LongTermForecastViewModel.class);
        binding = FragmentLongtermforecastBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.updateForecastData("Bialystok");

        viewModel.getForecastData().observe(getViewLifecycleOwner(), forecastData -> {
            if (forecastData != null) {
                WeatherAdapter adapter = new WeatherAdapter(forecastData.getList(), this::formatDateTime);
                recyclerView.setAdapter(adapter);
            }
        });

        return root;
    }

    private String formatDateTime(String dateTime) {
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = originalFormat.parse(dateTime);

            SimpleDateFormat newFormat = new SimpleDateFormat("dd MMMM, HH:mm");
            return newFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateTime;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
