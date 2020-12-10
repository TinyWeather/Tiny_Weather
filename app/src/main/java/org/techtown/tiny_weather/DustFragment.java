package org.techtown.tiny_weather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DustFragment extends Fragment {
    LocationActivity locationActivity;
    DustActivity dustActivity;
    TimeActivity timeActivity;

    ViewGroup rootView;
    ScrollView scrollViewA, scrollViewB;

    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout linearLayout, linearLayout2;

    TextView text1, text2;

    // 미세먼지
    int imgDust;
    String dustpm10Value;
    //ImageView dustImg;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        locationActivity = new LocationActivity(context);
        timeActivity = new TimeActivity();
        dustActivity = new DustActivity();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_dust, container, false);

        scrollViewA = rootView.getRootView().findViewById(R.id.dust_scroll);
        scrollViewB = rootView.getRootView().findViewById(R.id.dust_list2_scroll);

        scrollViewB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    scrollViewA.requestDisallowInterceptTouchEvent(false);
                }
                else {
                    scrollViewA.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.getRootView().findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                locationActivity = new LocationActivity(getContext());
                timeActivity = new TimeActivity();

                text1.setText(locationActivity.getTextView3());
                text2.setText(timeActivity.getTime());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                         dustActivity.setDustXmlData(locationActivity.getTextView5());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initUI(rootView);
                            }
                        });
                    }
                }).start();

                NavigationView navigationView = (NavigationView) rootView.getRootView().findViewById(R.id.nav_view);
                View header = navigationView.getHeaderView(0);
                TextView textHeader = (TextView) header.findViewById(R.id.user_location);
                textHeader.setText(locationActivity.getTextView3());

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, 500);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        text1 = (TextView) getActivity().findViewById(R.id.dust_txt_location2);
        text2 = (TextView) getActivity().findViewById(R.id.dust_update_time2);

        text1.setText(locationActivity.getTextView3());
        text2.setText(timeActivity.getTime());

        new Thread(new Runnable() {
            @Override
            public void run() {
                dustActivity.setDustXmlData(locationActivity.getTextView5());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initUI(rootView);
                    }
                });
            }
        }).start();
    }

    // 미세먼지 얼굴
    public void initUI(ViewGroup rootView) {

        dustpm10Value = dustActivity.getpm10Value(); // 미세먼지 수치
        System.out.println("DUST_F"+dustpm10Value+"====");

        int pm10Value = Integer.parseInt(dustpm10Value);
        if( 0 <= pm10Value &&  pm10Value < 16 ){
            imgDust = R.drawable.dust8;
        }
        else if( 16 <= pm10Value &&  pm10Value < 31 ){
            imgDust = R.drawable.dust7;
        }
        else if( 31 <= pm10Value &&  pm10Value < 41 ){
            imgDust = R.drawable.dust6;
        }
        else if( 41 <= pm10Value &&  pm10Value < 51 ){
            imgDust = R.drawable.dust5;
        }
        else if( 51 <= pm10Value &&  pm10Value < 76 ){
            imgDust = R.drawable.dust4;
        }
        else if( 76 <= pm10Value &&  pm10Value < 101 ){
            imgDust = R.drawable.dust3;
        }
        else if( 101 <= pm10Value &&  pm10Value < 151 ){
            imgDust = R.drawable.dust2;
        }
        else if( 151 <= pm10Value){
            imgDust = R.drawable.dust1;
        }

        ImageView dustImg = (ImageView) getActivity().findViewById(R.id.dust_img);
        dustImg.setImageResource(imgDust);
    }


    // 현 위치 대기 상태
    public void initUI2(ViewGroup rootView) {

    }
}
