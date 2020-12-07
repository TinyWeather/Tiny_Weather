package org.techtown.tiny_weather;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

public class HomeFragment extends Fragment {
    LocationActivity locationActivity;
    TimeActivity timeActivity;
    WeatherActivity weatherActivity;
    CovidActivity covidActivity;

    SwipeRefreshLayout swipeRefreshLayout;
    TextView text, text2, text3;

    // 날씨
    String weather, minWeather, maxWeather, yWeather;
    TextView weatherText1, weatherText2, weatherText3;

    // 코로나
    String covidIncDec, covidIsolIngCnt, covidDate;
    TextView covidText1, covidText2, covidText3;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        locationActivity = new LocationActivity(context);
        timeActivity = new TimeActivity();
        weatherActivity = new WeatherActivity();
        covidActivity = new CovidActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_home, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.getRootView().findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                locationActivity = new LocationActivity(getContext());
                timeActivity = new TimeActivity();

                text.setText(locationActivity.getTextView());
                text2.setText(locationActivity.getTextView2());
                text3.setText(timeActivity.getTime());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 날씨
                        weatherActivity.setWeatherJsonData(locationActivity.getLat(), locationActivity.getLon());
                        weatherActivity.setWeatherJsonData2(locationActivity.getLat(), locationActivity.getLon());
                        weather = weatherActivity.getWeather() + "º";
                        minWeather = "최저 " + weatherActivity.getMinWeather() + "º";
                        maxWeather = "최고 " + weatherActivity.getMaxWeather() + "º";
                        yWeather = weatherActivity.getyWeather();

                        // 코로나
                        covidActivity.setCovidXmlData(locationActivity.getTextView3());
                        covidIncDec = "1일 확진자 : " + covidActivity.getIncDec() + "명";
                        covidIsolIngCnt = "누적 확진자 : " + covidActivity.getIsolIngCnt() + "명";
                        covidDate = "(" + covidActivity.getToday() + ")";

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                weatherText1.setText(weather);
                                weatherText2.setText(yWeather);
                                weatherText3.setText(minWeather + " / " + maxWeather);

                                covidText1.setText(covidIncDec);
                                covidText2.setText(covidIsolIngCnt);
                                covidText3.setText(covidDate);
                            }
                        });
                    }
                }).start();

                NavigationView navigationView = (NavigationView) rootView.getRootView().findViewById(R.id.nav_view);
                View header = navigationView.getHeaderView(0);
                TextView textHeader = (TextView) header.findViewById(R.id.user_location);
                textHeader.setText(locationActivity.getTextView());

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);
            }
        });

        initUI(rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        text = (TextView) getActivity().findViewById(R.id.home_txt_location2);
        text2 = (TextView) getActivity().findViewById(R.id.home_txt_covid2);
        text3 = (TextView) getActivity().findViewById(R.id.home_update_time2);

        weatherText1 = (TextView) getActivity().findViewById(R.id.home_txt_weather);
        weatherText2 = (TextView) getActivity().findViewById(R.id.home_txt_weather2);
        weatherText3 = (TextView) getActivity().findViewById(R.id.home_txt_weather3);

        covidText1 = (TextView) getActivity().findViewById(R.id.home_txt_covid3);
        covidText2 = (TextView) getActivity().findViewById(R.id.home_txt_covid4);
        covidText3 = (TextView) getActivity().findViewById(R.id.home_txt_covid5);

        text.setText(locationActivity.getTextView());
        text2.setText(locationActivity.getTextView2());
        text3.setText(timeActivity.getTime());

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 날씨
                weatherActivity.setWeatherJsonData(locationActivity.getLat(), locationActivity.getLon());
                weatherActivity.setWeatherJsonData2(locationActivity.getLat(), locationActivity.getLon());
                weather = weatherActivity.getWeather() + "º";
                minWeather = "최저 " + weatherActivity.getMinWeather() + "º";
                maxWeather = "최고 " + weatherActivity.getMaxWeather() + "º";
                yWeather = weatherActivity.getyWeather();

                // 코로나
                covidActivity.setCovidXmlData(locationActivity.getTextView3());
                covidIncDec = "1일 확진자 : " + covidActivity.getIncDec() + "명";
                covidIsolIngCnt = "누적 확진자 : " + covidActivity.getIsolIngCnt() + "명";
                covidDate = "(" + covidActivity.getToday() + ")";

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        weatherText1.setText(weather);
                        weatherText2.setText(yWeather);
                        weatherText3.setText(minWeather + " / " + maxWeather);

                        covidText1.setText(covidIncDec);
                        covidText2.setText(covidIsolIngCnt);
                        covidText3.setText(covidDate);
                    }
                });
            }
        }).start();
    }

    public void initUI(ViewGroup rootView) {

    }
}
