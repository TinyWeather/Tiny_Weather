package org.techtown.tiny_weather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public class CovidFragment extends Fragment {
    LocationActivity locationActivity;
    TimeActivity timeActivity;
    CovidActivity covidActivity;

    ScrollView scrollViewA, scrollViewB;

    ViewGroup rootView;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView text, text2, text3;

    LinearLayout linearLayout, linearLayout2;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        locationActivity = new LocationActivity(context);
        timeActivity = new TimeActivity();
        covidActivity = new CovidActivity();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_covid, container, false);

        scrollViewA = rootView.getRootView().findViewById(R.id.covid_scroll);
        scrollViewB = rootView.getRootView().findViewById(R.id.covid_list2_scroll);

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
            public void onRefresh() {
                locationActivity = new LocationActivity(getContext());
                timeActivity = new TimeActivity();

                text.setText(locationActivity.getTextView2());
                text2.setText(locationActivity.getTextView2());
                text3.setText(timeActivity.getTime());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        covidActivity.setCovidXmlData("합계");
                        covidActivity.setCovidXmlData2(locationActivity.getTextView3());
                        covidActivity.setCovidXmlData3();

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

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        text = (TextView) getActivity().findViewById(R.id.covid_txt_location2);
        text2 = (TextView) getActivity().findViewById(R.id.covid_graph_text);
        text3 = (TextView) getActivity().findViewById(R.id.covid_update_time2);

        text.setText(locationActivity.getTextView2());
        text2.setText(locationActivity.getTextView2());
        text3.setText(timeActivity.getTime());

        new Thread(new Runnable() {
            @Override
            public void run() {
                covidActivity.setCovidXmlData("합계");
                covidActivity.setCovidXmlData2(locationActivity.getTextView3());
                covidActivity.setCovidXmlData3();

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
        int img;
        if (Integer.parseInt(covidActivity.getIncDec()) == 0)
            img = R.drawable.dust8;
        else if(Integer.parseInt(covidActivity.getIncDec()) <= 2)
            img = R.drawable.dust7;
        else if(Integer.parseInt(covidActivity.getIncDec()) <= 5)
            img = R.drawable.dust6;
        else if(Integer.parseInt(covidActivity.getIncDec()) <= 7)
            img = R.drawable.dust5;
        else if(Integer.parseInt(covidActivity.getIncDec()) <= 10)
            img = R.drawable.dust4;
        else if(Integer.parseInt(covidActivity.getIncDec()) <= 20)
            img = R.drawable.dust3;
        else if(Integer.parseInt(covidActivity.getIncDec()) <= 50)
            img = R.drawable.dust2;
        else
            img = R.drawable.dust1;

        ImageView imageView = (ImageView) rootView.getRootView().findViewById(R.id.covid_img);
        imageView.setImageResource(img);

        TextView textView = (TextView) rootView.getRootView().findViewById(R.id.covid_txt);
        textView.setText("전국 확진자 " + covidActivity.getIncDec() + "명");
    }

    public void initUI2(ViewGroup rootView) {
        int img;

        linearLayout = (LinearLayout) rootView.getRootView().findViewById(R.id.covid_list1);
        linearLayout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(15, 0, 15, 0);

        for(int i=9; i>=0; i--) {
            if (Integer.parseInt(covidActivity.getArrIncDec2(i)) == 0)
                img = R.drawable.dust8;
            else if(Integer.parseInt(covidActivity.getArrIncDec2(i)) <= 5)
                img = R.drawable.dust7;
            else if(Integer.parseInt(covidActivity.getArrIncDec2(i)) <= 10)
                img = R.drawable.dust6;
            else if(Integer.parseInt(covidActivity.getArrIncDec2(i)) <= 25)
                img = R.drawable.dust5;
            else if(Integer.parseInt(covidActivity.getArrIncDec2(i)) <= 50)
                img = R.drawable.dust4;
            else if(Integer.parseInt(covidActivity.getArrIncDec2(i)) <= 75)
                img = R.drawable.dust3;
            else if(Integer.parseInt(covidActivity.getArrIncDec2(i)) <= 100)
                img = R.drawable.dust2;
            else
                img = R.drawable.dust1;

            ImageView newImageView = new ImageView(rootView.getContext());
            newImageView.setImageResource(img);
            newImageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
            newImageView.requestLayout();

            TextView newTextView = new TextView(rootView.getContext());
            newTextView.setText(covidActivity.getArrIncDec2(i) + "명");
            newTextView.setGravity(Gravity.CENTER);
            newTextView.setTextAppearance(R.style.txt_black_18);

            TextView newTextView2 = new TextView(rootView.getContext());
            String strDate[] = covidActivity.getArrStdDay2(i).split(" ");
            newTextView2.setText(strDate[1] + " " + strDate[2]);
            newTextView2.setGravity(Gravity.CENTER);
            newTextView2.setTextAppearance(R.style.txt_black_14);

            LinearLayout linearLayout1 = new LinearLayout(rootView.getContext());
            linearLayout1.setOrientation(LinearLayout.VERTICAL);
            linearLayout1.addView(newImageView);
            linearLayout1.addView(newTextView);
            linearLayout1.addView(newTextView2);

            linearLayout.addView(linearLayout1, layoutParams);
        }
    }

    public void initUI3(ViewGroup rootView) {
        linearLayout2 = (LinearLayout) rootView.getRootView().findViewById(R.id.covid_list2);
        linearLayout2.removeAllViews();
        TextView textView1 = (TextView) rootView.getRootView().findViewById(R.id.covid_txt1);
        TextView textView2 = (TextView) rootView.getRootView().findViewById(R.id.covid_txt2);
        TextView textView3 = (TextView) rootView.getRootView().findViewById(R.id.covid_txt3);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(50, 0, 20, 0);

        for(int i=18; i>=0; i--) {
            TextView newTextView = new TextView(rootView.getContext());
            newTextView.setText(covidActivity.getArrGubun3(i));
            newTextView.setGravity(Gravity.CENTER);
            newTextView.setTextAppearance(R.style.txt_black_25);
            newTextView.setLayoutParams(new ViewGroup.LayoutParams(textView1.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView newTextView2 = new TextView(rootView.getContext());
            newTextView2.setText(covidActivity.getArrIncDec3(i) + "명");
            newTextView2.setGravity(Gravity.CENTER);
            newTextView2.setTextAppearance(R.style.txt_black_25);
            newTextView2.setLayoutParams(new ViewGroup.LayoutParams(textView2.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView newTextView3 = new TextView(rootView.getContext());
            newTextView3.setText(covidActivity.getArrDefCnt3(i) + "명");
            newTextView3.setGravity(Gravity.CENTER);
            newTextView3.setTextAppearance(R.style.txt_black_25);
            newTextView3.setLayoutParams(new ViewGroup.LayoutParams(textView3.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));

            LinearLayout linearLayout1 = new LinearLayout(rootView.getContext());
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1.addView(newTextView);
            linearLayout1.addView(newTextView2);
            linearLayout1.addView(newTextView3);

            linearLayout2.addView(linearLayout1, layoutParams2);
        }
    }
}
