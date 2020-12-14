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
import android.widget.ImageView;
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
    DustActivity dustActivity;

    SwipeRefreshLayout swipeRefreshLayout;
    TextView text, text2, text3;

    // 날씨
    int weather, minWeather, maxWeather, yWeather, subWeather, idWeather, imgWeather;
    String subWeatherStr;
    TextView weatherText1, weatherText2, weatherText3;
    ImageView weatgerImg1;
    Date date = new Date();
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
    int time = Integer.parseInt(timeFormat.format(date));

    // 코로나
    String covidIncDec, covidIsolIngCnt, covidDate;
    TextView covidText1, covidText2, covidText3;

    // 미세먼지
    int imgDust;
    String dustpm10Value;
    ImageView dustImg;
    TextView dustText1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        locationActivity = new LocationActivity(context);
        timeActivity = new TimeActivity();
        weatherActivity = new WeatherActivity();
        covidActivity = new CovidActivity();
        dustActivity = new DustActivity();
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
                        weather = weatherActivity.getWeather();
                        minWeather = weatherActivity.getMinWeather();
                        maxWeather = weatherActivity.getMaxWeather();
                        yWeather = weatherActivity.getyWeather();
                        subWeather = weather - yWeather;
                        if(subWeather == 0)
                            subWeatherStr = "어제와 같아요";
                        else if(subWeather < 0)
                            subWeatherStr = "어제보다 " + (-subWeather) + " ℃ 낮아요";
                        else if(subWeather > 0)
                            subWeatherStr = "어제보다 " + subWeather + " ℃ 높아요";

                        // 코로나
                        covidActivity.setCovidXmlData(locationActivity.getTextView3());
                        covidIncDec = "1일 확진자 : " + covidActivity.getIncDec() + "명";
                        covidIsolIngCnt = "누적 확진자 : " + covidActivity.getIsolIngCnt() + "명";
                        covidDate = "(" + covidActivity.getToday() + ")";

                        // 미세먼지
                        dustActivity.setDustXmlData(locationActivity.getTextView5(), locationActivity.getTextView3());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                weatherText1.setText(weather + " ℃");
                                weatherText2.setText(subWeatherStr);
                                weatherText3.setText("최저 " + minWeather + " ℃ / 최고 " + maxWeather + " ℃");

                                covidText1.setText(covidIncDec);
                                covidText2.setText(covidIsolIngCnt);
                                covidText3.setText(covidDate);

                                dustText1.setText(dustpm10Value);
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
        weatgerImg1 = (ImageView) getActivity().findViewById(R.id.home_img_weather);

        covidText1 = (TextView) getActivity().findViewById(R.id.home_txt_covid3);
        covidText2 = (TextView) getActivity().findViewById(R.id.home_txt_covid4);
        covidText3 = (TextView) getActivity().findViewById(R.id.home_txt_covid5);

        dustText1 = (TextView) getActivity().findViewById(R.id.home_txt_dust);
        dustImg = (ImageView) getActivity().findViewById(R.id.home_img_dust);

        text.setText(locationActivity.getTextView());
        text2.setText(locationActivity.getTextView2());
        text3.setText(timeActivity.getTime());

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 날씨
                weatherActivity.setWeatherJsonData(locationActivity.getLat(), locationActivity.getLon());
                weatherActivity.setWeatherJsonData2(locationActivity.getLat(), locationActivity.getLon());
                weather = weatherActivity.getWeather();
                minWeather = weatherActivity.getMinWeather();
                maxWeather = weatherActivity.getMaxWeather();
                yWeather = weatherActivity.getyWeather();
                idWeather = weatherActivity.getIdWeather();

                subWeather = weather - yWeather;
                if(subWeather == 0)
                    subWeatherStr = "어제와 같아요";
                else if(subWeather < 0)
                    subWeatherStr = "어제보다 " + (-subWeather) + " ℃ 낮아요";
                else if(subWeather > 0)
                    subWeatherStr = "어제보다 " + subWeather + " ℃ 높아요";

                if(idWeather < 300) {
                    if(time >= 6 && time < 18)
                        imgWeather = R.drawable.storm_day;
                    else if(time >= 18 && time < 22)
                        imgWeather = R.drawable.storm;
                    else
                        imgWeather = R.drawable.storm_night;
                }
                else if(idWeather < 600) {
                    if(time >= 6 && time < 18)
                        imgWeather = R.drawable.rainy_day;
                    else if(time >= 18 && time < 22)
                        imgWeather = R.drawable.rainy;
                    else
                        imgWeather = R.drawable.rainy_night;
                }
                else if(idWeather < 700) {
                    if(time >= 6 && time < 18)
                        imgWeather = R.drawable.snowy_day;
                    else if(time >= 18 && time < 22)
                        imgWeather = R.drawable.snowy;
                    else
                        imgWeather = R.drawable.snowy_night;
                }
                else if(idWeather < 800) {
                    if(time >= 6 && time < 18)
                        imgWeather = R.drawable.windy_day;
                    else if(time >= 18 && time < 22)
                        imgWeather = R.drawable.windy;
                    else
                        imgWeather = R.drawable.windy_night;
                }
                else if(idWeather == 800) {
                    if(time >= 6 && time < 20)
                        imgWeather = R.drawable.sun;
                    else
                        imgWeather = R.drawable.moon;
                }
                else if(idWeather < 900) {
                    if(time >= 6 && time < 18)
                        imgWeather = R.drawable.cloudy_day;
                    else if(time >= 18 && time < 22)
                        imgWeather = R.drawable.cloud;
                    else
                        imgWeather = R.drawable.cloudy_night;
                }

                // 코로나
                covidActivity.setCovidXmlData(locationActivity.getTextView3());
                covidIncDec = "1일 확진자 : " + covidActivity.getIncDec() + "명";
                covidIsolIngCnt = "누적 확진자 : " + covidActivity.getIsolIngCnt() + "명";
                covidDate = "(" + covidActivity.getToday() + ")";

                // 미세먼지
                dustActivity.setDustXmlData(locationActivity.getTextView5(), locationActivity.getTextView3());
                dustpm10Value = dustActivity.getpm10Value(); // 미세먼지 수치

                int pm10Value = Integer.parseInt(dustpm10Value);
                if( 0 <= pm10Value &&  pm10Value < 16 ){
                    imgDust = R.drawable.dust8;
                    dustpm10Value = "최고 좋음";
                }
                else if( 16 <= pm10Value &&  pm10Value < 31 ){
                    imgDust = R.drawable.dust7;
                    dustpm10Value = "좋음";
                }
                else if( 31 <= pm10Value &&  pm10Value < 41 ){
                    imgDust = R.drawable.dust6;
                    dustpm10Value = "양호";
                }
                else if( 41 <= pm10Value &&  pm10Value < 51 ){
                    imgDust = R.drawable.dust5;
                    dustpm10Value = "보통";
                }
                else if( 51 <= pm10Value &&  pm10Value < 76 ){
                    imgDust = R.drawable.dust4;
                    dustpm10Value = "나쁨";
                }
                else if( 76 <= pm10Value &&  pm10Value < 101 ){
                    imgDust = R.drawable.dust3;
                    dustpm10Value = "상당히 나쁨";
                }
                else if( 101 <= pm10Value &&  pm10Value < 151 ){
                    imgDust = R.drawable.dust2;
                    dustpm10Value = "매우 나쁨";
                }
                else if( 151 <= pm10Value){
                    imgDust = R.drawable.dust1;
                    dustpm10Value = "최악";
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        weatherText1.setText(weather + " ℃");
                        weatherText2.setText(subWeatherStr);
                        weatherText3.setText("최저 " + minWeather + " ℃ / 최고 " + maxWeather + " ℃");
                        weatgerImg1.setImageResource(imgWeather);

                        covidText1.setText(covidIncDec);
                        covidText2.setText(covidIsolIngCnt);
                        covidText3.setText(covidDate);

                        dustText1.setText(dustpm10Value);
                        dustImg.setImageResource(imgDust);
                    }
                });
            }
        }).start();
    }

    public void initUI(ViewGroup rootView) {

    }
}
