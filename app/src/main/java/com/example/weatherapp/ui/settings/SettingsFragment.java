package com.example.weatherapp.ui.settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.R;
import com.example.weatherapp.models.SettingsData;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private EditText editTextCityName;
    private RadioGroup radioGroupLocalization;
    private RadioGroup radioGroupTemperature;
    private RadioGroup radioGroupWindSpeed;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchNotifications;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchAutoUpdates;

    private final SettingsData settings = SettingsData.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        settingsViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        // Initialize components
        editTextCityName = root.findViewById(R.id.editText_cityName);
        radioGroupLocalization = root.findViewById(R.id.radioGroup_localization);
        radioGroupTemperature = root.findViewById(R.id.radioGroup_temperature);
        radioGroupWindSpeed = root.findViewById(R.id.radioGroup_windSpeed);
        switchNotifications = root.findViewById(R.id.switch_notifications);
        switchAutoUpdates = root.findViewById(R.id.switch_auto_updates);

        setupListeners();
        loadSettings();

        return root;
    }

    private void setupListeners() {
        // Localization
        radioGroupLocalization.setOnCheckedChangeListener((group, checkedId) -> {
            boolean useCurrentLocation = checkedId == R.id.radioButton_current;
            editTextCityName.setEnabled(!useCurrentLocation);
            settingsViewModel.saveBooleanSetting("useCurrentLocation", useCurrentLocation);
            settings.useCurrentLocation = useCurrentLocation;
        });

        // City Name
        editTextCityName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Save city name every time it changes
                settingsViewModel.saveStringSetting("cityName", s.toString());
                settings.cityName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed for this implementation
            }
        });

        // Temperature Unit
        radioGroupTemperature.setOnCheckedChangeListener((group, checkedId) -> {
            String unit = checkedId == R.id.radioButton_celsius ? "Celsius" : "Fahrenheit";
            settingsViewModel.saveStringSetting("temperatureUnit", unit);
            settings.temperatureUnit = unit;
        });

        // Wind Speed Unit
        radioGroupWindSpeed.setOnCheckedChangeListener((group, checkedId) -> {
            String unit = checkedId == R.id.radioButton_kmh ? "km/h" : "m/s";
            settingsViewModel.saveStringSetting("windSpeedUnit", unit);
            settings.windSpeedUnit = unit;
        });

        // Notifications and Auto-updates
        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            settingsViewModel.saveBooleanSetting("notifications", isChecked);
            settings.notificationsEnabled = isChecked;
        });
        switchAutoUpdates.setOnCheckedChangeListener((buttonView, isChecked) -> {
            settingsViewModel.saveBooleanSetting("autoUpdates", isChecked);
            settings.autoUpdatesEnabled = isChecked;
        });
    }

    private void loadSettings() {
        // Load and apply saved settings
        boolean useCurrentLocation = settingsViewModel.getBooleanSetting("useCurrentLocation", true);
        radioGroupLocalization.check(useCurrentLocation ? R.id.radioButton_current : R.id.radioButton_enterCity);
        editTextCityName.setEnabled(!useCurrentLocation);

        // Always load the city name
        String savedCityName = settingsViewModel.getStringSetting("cityName", "");
        editTextCityName.setText(savedCityName);

        String temperatureUnit = settingsViewModel.getStringSetting("temperatureUnit", "Celsius");
        radioGroupTemperature.check("Celsius".equals(temperatureUnit) ? R.id.radioButton_celsius : R.id.radioButton_fahrenheit);

        String windSpeedUnit = settingsViewModel.getStringSetting("windSpeedUnit", "km/h");
        radioGroupWindSpeed.check("km/h".equals(windSpeedUnit) ? R.id.radioButton_kmh : R.id.radioButton_ms);

        switchNotifications.setChecked(settingsViewModel.getBooleanSetting("notifications", false));
        switchAutoUpdates.setChecked(settingsViewModel.getBooleanSetting("autoUpdates", false));
    }
}
