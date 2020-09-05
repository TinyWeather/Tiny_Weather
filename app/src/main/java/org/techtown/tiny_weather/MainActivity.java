package org.techtown.tiny_weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements onTabItemSelectedListener {

    MapFragment mapFragment;
    CovidFragment covidFragment;
    HomeFragment homeFragment;
    WeatherFragment weatherFragment;
    DustFragment dustFragment;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = new MapFragment();
        covidFragment = new CovidFragment();
        homeFragment = new HomeFragment();
        weatherFragment = new WeatherFragment();
        dustFragment = new DustFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.map:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragment).commit();
                        return true;
                    case R.id.covid:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, covidFragment).commit();
                        return true;
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.weather:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, weatherFragment).commit();
                        return true;
                    case R.id.dust:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, dustFragment).commit();
                        return true;
                }

                return false;
            }
        });
    }

    @Override
    public void onTabSelected(int position) {
        if(position == 0)
            bottomNavigationView.setSelectedItemId(R.id.map);
        else if(position == 1)
            bottomNavigationView.setSelectedItemId(R.id.covid);
        else if(position == 2)
            bottomNavigationView.setSelectedItemId(R.id.home);
        else if(position == 3)
            bottomNavigationView.setSelectedItemId(R.id.weather);
        else if(position == 4)
            bottomNavigationView.setSelectedItemId(R.id.dust);
    }
}
