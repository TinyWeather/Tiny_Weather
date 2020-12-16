package org.techtown.tiny_weather;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeatherFragment extends Fragment {
    LocationActivity locationActivity;
    TimeActivity timeActivity;
    WeatherActivity weatherActivity;
    ViewGroup rootView;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView text, text2;
    LinearLayout linearLayout, linearLayout2;

    int weather, minWeather, maxWeather, yWeather, subWeather, idWeather, imgWeather;
    String subWeatherStr;
    TextView weatherText1, weatherText2, weatherText3;
    ImageView weatgerImg1;
    Date date = new Date();
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
    int time = Integer.parseInt(timeFormat.format(date));

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        locationActivity = new LocationActivity(context, getActivity());
        timeActivity = new TimeActivity();
        weatherActivity = new WeatherActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_weather, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.getRootView().findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                text.setText(locationActivity.getTextView());
                text2.setText(timeActivity.getTime());

                NavigationView navigationView = (NavigationView) rootView.getRootView().findViewById(R.id.nav_view);
                View header = navigationView.getHeaderView(0);
                TextView textHeader = (TextView) header.findViewById(R.id.user_location);
                textHeader.setText(locationActivity.getTextView());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 날씨
                        weatherActivity.setWeatherJsonData(locationActivity.getLat(), locationActivity.getLon());
                        weatherActivity.setWeatherJsonData2(locationActivity.getLat(), locationActivity.getLon());
                        weatherActivity.setWeatherJsonData3(locationActivity.getLat(), locationActivity.getLon());
                        weatherActivity.setWeatherJsonData4(locationActivity.getLat(), locationActivity.getLon());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initUI(rootView);
                                initUI2(rootView);
                                initUI3(rootView);
                            }
                        });
                    }
                }).start();

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

        text = (TextView) getActivity().findViewById(R.id.weather_txt_location2);
        text2 = (TextView) getActivity().findViewById(R.id.weather_update_time2);

        text.setText(locationActivity.getTextView());
        text2.setText(timeActivity.getTime());

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 날씨
                weatherActivity.setWeatherJsonData(locationActivity.getLat(), locationActivity.getLon());
                weatherActivity.setWeatherJsonData2(locationActivity.getLat(), locationActivity.getLon());
                weatherActivity.setWeatherJsonData3(locationActivity.getLat(), locationActivity.getLon());
                weatherActivity.setWeatherJsonData4(locationActivity.getLat(), locationActivity.getLon());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initUI(rootView);
                        initUI2(rootView);
                        initUI3(rootView);
                    }
                });
            }
        }).start();
    }

    public void initUI(ViewGroup rootView) {
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

        weatherText1 = (TextView) rootView.getRootView().findViewById(R.id.weather_txt);
        weatherText2 = (TextView) rootView.getRootView().findViewById(R.id.weather_txt2);
        weatherText3 = (TextView) rootView.getRootView().findViewById(R.id.weather_txt3);
        weatgerImg1 = (ImageView) rootView.getRootView().findViewById(R.id.weather_img);

        weatherText1.setText(weather + " ℃");
        weatherText2.setText(subWeatherStr);
        weatherText3.setText("최저 " + minWeather + " ℃ / 최고 " + maxWeather + " ℃");
        weatgerImg1.setImageResource(imgWeather);
    }

    public void initUI2(ViewGroup rootView) {
        linearLayout = (LinearLayout) rootView.getRootView().findViewById(R.id.weather_list1);
        linearLayout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(15, 0, 15, 0);

        for(int i=0; i<48; i++) {
            int timeImg = weatherActivity.getTimeIdWeather(i);
            String timeDt = weatherActivity.getTimeDtWeather(i);
            int timeTemp = weatherActivity.getTimeWeather(i);

            String strDate[] = timeDt.split(" ");
            String strTime[] = strDate[1].split(":");
            int time = Integer.parseInt(strTime[0]);
            int imgWeather = 0;
            if(timeImg < 300) {
                if(time >= 6 && time < 18)
                    imgWeather = R.drawable.storm_day;
                else if(time >= 18 && time < 22)
                    imgWeather = R.drawable.storm;
                else
                    imgWeather = R.drawable.storm_night;
            }
            else if(timeImg < 600) {
                if(time >= 6 && time < 18)
                    imgWeather = R.drawable.rainy_day;
                else if(time >= 18 && time < 22)
                    imgWeather = R.drawable.rainy;
                else
                    imgWeather = R.drawable.rainy_night;
            }
            else if(timeImg < 700) {
                if(time >= 6 && time < 18)
                    imgWeather = R.drawable.snowy_day;
                else if(time >= 18 && time < 22)
                    imgWeather = R.drawable.snowy;
                else
                    imgWeather = R.drawable.snowy_night;
            }
            else if(timeImg < 800) {
                if(time >= 6 && time < 18)
                    imgWeather = R.drawable.windy_day;
                else if(time >= 18 && time < 22)
                    imgWeather = R.drawable.windy;
                else
                    imgWeather = R.drawable.windy_night;
            }
            else if(timeImg == 800) {
                if(time >= 6 && time < 20)
                    imgWeather = R.drawable.sun;
                else
                    imgWeather = R.drawable.moon;
            }
            else if(timeImg < 900) {
                if(time >= 6 && time < 18)
                    imgWeather = R.drawable.cloudy_day;
                else if(time >= 18 && time < 22)
                    imgWeather = R.drawable.cloud;
                else
                    imgWeather = R.drawable.cloudy_night;
            }

            ImageView newImageView = new ImageView(rootView.getContext());
            newImageView.setImageResource(imgWeather);
            newImageView.setLayoutParams(new ViewGroup.LayoutParams(160, 160));
            newImageView.requestLayout();

            TextView newTextView = new TextView(rootView.getContext());
            newTextView.setText(strTime[0] + "시");
            newTextView.setGravity(Gravity.CENTER);
            newTextView.setTextAppearance(R.style.txt_black_15);

            TextView newTextView2 = new TextView(rootView.getContext());
            newTextView2.setText(timeTemp + "℃");
            newTextView2.setGravity(Gravity.CENTER);
            newTextView2.setTextAppearance(R.style.txt_black_15);

            LinearLayout linearLayout1 = new LinearLayout(rootView.getContext());
            linearLayout1.setOrientation(LinearLayout.VERTICAL);
            linearLayout1.addView(newImageView);
            linearLayout1.addView(newTextView);
            linearLayout1.addView(newTextView2);

            linearLayout.addView(linearLayout1, layoutParams);
        }
    }

    public void initUI3(ViewGroup rootView) {
        linearLayout2 = (LinearLayout) rootView.getRootView().findViewById(R.id.weather_list2_list);
        linearLayout2.removeAllViews();
        TextView margin = (TextView) rootView.getRootView().findViewById(R.id.margin);
        TextView textView1 = (TextView) rootView.getRootView().findViewById(R.id.weather_list_title_txt1);
        TextView textView3 = (TextView) rootView.getRootView().findViewById(R.id.weather_list_title_txt3);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 20, 0, 0);
        layoutParams.setMarginStart(margin.getWidth());
        layoutParams.setMarginEnd(margin.getWidth());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.weight = 1;

        for(int i=0; i<7; i++) {
            int sevenImg = weatherActivity.getSevenIdWeather(i);
            String sevenDt = weatherActivity.getSevenDtWeather(i);
            int sevenMin = weatherActivity.getSevenMinWeather(i);
            int sevenMax = weatherActivity.getSevenMaxWeather(i);
            String sevenWeek = "";

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date sevenDate = dateFormat.parse(sevenDt);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(sevenDate);

                switch(calendar.get(Calendar.DAY_OF_WEEK)){
                    case 1:
                        sevenWeek = "일요일"; break;
                    case 2:
                        sevenWeek = "월요일"; break;
                    case 3:
                        sevenWeek = "화요일"; break;
                    case 4:
                        sevenWeek = "수요일"; break;
                    case 5:
                        sevenWeek = "목요일"; break;
                    case 6:
                        sevenWeek = "금요일"; break;
                    case 7:
                        sevenWeek = "토요일"; break;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String strDate[] = sevenDt.split(" ");
            String strDate2[] = strDate[0].split("-");

            int imgWeather = 0;
            if(sevenImg < 300)
                    imgWeather = R.drawable.storm_day;
            else if(sevenImg < 600)
                    imgWeather = R.drawable.rainy_day;
            else if(sevenImg < 700)
                    imgWeather = R.drawable.snowy_day;
            else if(sevenImg < 800)
                    imgWeather = R.drawable.windy_day;
            else if(sevenImg == 800)
                    imgWeather = R.drawable.sun;
            else if(sevenImg < 900)
                    imgWeather = R.drawable.cloudy_day;

            TextView newTextView = new TextView(rootView.getContext());
            newTextView.setText(sevenWeek + " " + strDate2[1] + "." + strDate2[2]);
            newTextView.setGravity(Gravity.CENTER);
            newTextView.setTextAppearance(R.style.txt_black_18);
            newTextView.setLayoutParams(new ViewGroup.LayoutParams(textView1.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));

            ImageView newImageView = new ImageView(rootView.getContext());
            newImageView.setImageResource(imgWeather);
            newImageView.setLayoutParams(new ViewGroup.LayoutParams(140, 140));
            newImageView.requestLayout();

            RelativeLayout relativeLayout = new RelativeLayout(rootView.getContext());
            relativeLayout.setLayoutParams(layoutParams2);
            relativeLayout.setGravity(Gravity.CENTER);
            relativeLayout.addView(newImageView);

            TextView newTextView2 = new TextView(rootView.getContext());
            newTextView2.setText(sevenMin + "℃ / " + sevenMax + "℃");
            newTextView2.setGravity(Gravity.CENTER);
            newTextView2.setTextAppearance(R.style.txt_black_18);
            newTextView2.setLayoutParams(new ViewGroup.LayoutParams(textView3.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));

            LinearLayout linearLayout1 = new LinearLayout(rootView.getContext());
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1.setGravity(Gravity.CENTER);
            linearLayout1.addView(newTextView);
            linearLayout1.addView(relativeLayout);
            linearLayout1.addView(newTextView2);

            linearLayout2.addView(linearLayout1, layoutParams);
        }
    }
}
