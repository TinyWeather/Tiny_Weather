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
    SwipeRefreshLayout swipeRefreshLayout;
    TextView text, text2, text3;

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

        locationActivity = new LocationActivity(getContext());
        timeActivity = new TimeActivity();

        text = (TextView) getActivity().findViewById(R.id.txt_location2);
        text2 = (TextView) getActivity().findViewById(R.id.txt_covid2);
        text3 = (TextView) getActivity().findViewById(R.id.update_time2);

        text.setText(locationActivity.getTextView());
        text2.setText(locationActivity.getTextView2());
        text3.setText(timeActivity.getTime());
    }

    public void initUI(ViewGroup rootView) {

    }
}
