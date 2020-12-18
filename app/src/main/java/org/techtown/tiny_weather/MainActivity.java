package org.techtown.tiny_weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements onTabItemSelectedListener {
    FragmentManager fragmentManager;

    MapFragment mapFragment;
    CovidFragment covidFragment;
    HomeFragment homeFragment;
    WeatherFragment weatherFragment;
    DustFragment dustFragment;

    BottomNavigationView bottomNavigationView;

    private DrawerLayout drawerLayout;
    private View drawerView;

    private DrawerLayout mDrawerLayout;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 액션바 + 네비게이션 드로우
        setActionBar();

        fragmentManager = getSupportFragmentManager();

        homeFragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.map:
                        if(mapFragment == null) {
                            mapFragment = new MapFragment();
                            fragmentManager.beginTransaction().add(R.id.container, mapFragment).commit();
                        }

                        if(mapFragment != null) fragmentManager.beginTransaction().show(mapFragment).commit();
                        if(covidFragment != null) fragmentManager.beginTransaction().hide(covidFragment).commit();
                        if(homeFragment != null) fragmentManager.beginTransaction().hide(homeFragment).commit();
                        if(weatherFragment != null) fragmentManager.beginTransaction().hide(weatherFragment).commit();
                        if(dustFragment != null) fragmentManager.beginTransaction().hide(dustFragment).commit();
                        return true;
                    case R.id.covid:
                        if(covidFragment == null) {
                            covidFragment = new CovidFragment();
                            fragmentManager.beginTransaction().add(R.id.container, covidFragment).commit();
                        }

                        if(mapFragment != null) fragmentManager.beginTransaction().hide(mapFragment).commit();
                        if(covidFragment != null) fragmentManager.beginTransaction().show(covidFragment).commit();
                        if(homeFragment != null) fragmentManager.beginTransaction().hide(homeFragment).commit();
                        if(weatherFragment != null) fragmentManager.beginTransaction().hide(weatherFragment).commit();
                        if(dustFragment != null) fragmentManager.beginTransaction().hide(dustFragment).commit();
                        return true;
                    case R.id.home:
                        if(homeFragment == null) {
                            homeFragment = new HomeFragment();
                            fragmentManager.beginTransaction().add(R.id.container, homeFragment).commit();
                        }

                        if(mapFragment != null) fragmentManager.beginTransaction().hide(mapFragment).commit();
                        if(covidFragment != null) fragmentManager.beginTransaction().hide(covidFragment).commit();
                        if(homeFragment != null) fragmentManager.beginTransaction().show(homeFragment).commit();
                        if(weatherFragment != null) fragmentManager.beginTransaction().hide(weatherFragment).commit();
                        if(dustFragment != null) fragmentManager.beginTransaction().hide(dustFragment).commit();
                        return true;
                    case R.id.weather:
                        if(weatherFragment == null) {
                            weatherFragment = new WeatherFragment();
                            fragmentManager.beginTransaction().add(R.id.container, weatherFragment).commit();
                        }

                        if(mapFragment != null) fragmentManager.beginTransaction().hide(mapFragment).commit();
                        if(covidFragment != null) fragmentManager.beginTransaction().hide(covidFragment).commit();
                        if(homeFragment != null) fragmentManager.beginTransaction().hide(homeFragment).commit();
                        if(weatherFragment != null) fragmentManager.beginTransaction().show(weatherFragment).commit();
                        if(dustFragment != null) fragmentManager.beginTransaction().hide(dustFragment).commit();
                        return true;
                    case R.id.dust:
                        if(dustFragment == null) {
                            dustFragment = new DustFragment();
                            fragmentManager.beginTransaction().add(R.id.container, dustFragment).commit();
                        }

                        if(mapFragment != null) fragmentManager.beginTransaction().hide(mapFragment).commit();
                        if(covidFragment != null) fragmentManager.beginTransaction().hide(covidFragment).commit();
                        if(homeFragment != null) fragmentManager.beginTransaction().hide(homeFragment).commit();
                        if(weatherFragment != null) fragmentManager.beginTransaction().hide(weatherFragment).commit();
                        if(dustFragment != null) fragmentManager.beginTransaction().show(dustFragment).commit();
                        return true;
                }

                return false;
            }
        });
    }

    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.mipmap.menu); //뒤로가기 버튼 이미지 지정

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.profile){
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.push){
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.gps){
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                }else if(id == R.id.notice){//공지사항
                    Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                    startActivity(intent);
                }else if(id == R.id.inquiry){//문의하기
                    Intent intent = new Intent(getApplicationContext(), InquiryActivity.class);
                    startActivity(intent);
                }else if(id == R.id.guide){//가이드북
                    Intent intent = new Intent(getApplicationContext(), GuideActivity.class);
                    startActivity(intent);
                }else if(id == R.id.donation){//개발자 지원하기
                    Intent intent = new Intent(getApplicationContext(), DonationActivity.class);
                    startActivity(intent);
                }

                return true;
            }
        });

        View header = navigationView.getHeaderView(0);
        TextView text = (TextView) header.findViewById(R.id.user_location);
        LocationActivity locationActivity = new LocationActivity(getApplicationContext(), MainActivity.this);
        text.setText(locationActivity.getTextView());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
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
