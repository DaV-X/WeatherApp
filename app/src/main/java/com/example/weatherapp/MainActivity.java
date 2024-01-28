package com.example.weatherapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.models.Coord;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private WeatherDatabase weatherDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weatherDatabase = Room.databaseBuilder(getApplicationContext(), WeatherDatabase.class, "weather_database").fallbackToDestructiveMigration().addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        // Handle onCreate
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        // Handle onOpen
                    }
                }).allowMainThreadQueries() // For demonstration purposes only; use AsyncTask or background thread for database operations
                .build();
        Coord coord = weatherDatabase.getCoordDao().getCoordById(1);
        weatherDatabase.getCoordDao().deleteCoord(coord);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_currentweather, R.id.nav_longtermforecast, R.id.nav_slideshow).setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        try {
//            HistoryService.load();
//            HistoryService.save();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}