package org.techtown.tiny_weather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    Switch switchBtn;

    int imgDust, imgDust1, imgDust2, imgDust3, imgDust4, imgDust5;
    String Dustpm10ValueText, Dustpm25ValueText;
    String Dustno2ValueText, Dusto3ValueText, DustcoValueText, Dustso2ValueText;
    int dustno2Value, dusto3Value, dustcoValue, dustso2Value, dustpm10Value, dustpm25Value;

    ArrayList<Integer> arrayList1, arrayList2, arrayList3;
    ArrayList<String> arrayListPlace = new ArrayList<String>(
            Arrays.asList("서울", "부산", "대구", "인천", "광주", "대전", "울산", "세종", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주")
    );

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
                } else {
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

                text1.setText(locationActivity.getTextView5());
                text2.setText(timeActivity.getTime());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dustActivity.setDustXmlData(locationActivity.getTextView5(), locationActivity.getTextView3());
                   //     dustActivity.setDustXmlData2(timeActivity.getTime2());
                        dustActivity.setDustXmlData2(timeActivity.getTime2(),"PM10");
                        dustActivity.setDustXmlData3(timeActivity.getTime2(),"PM25");
                        //dustActivity.setDustXmlData2("seoul");

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                        if (isChecked) {
                                            initUI0(rootView); // 초미세먼지 상단
                                            initUI1(rootView); // 미세먼지 하단
                                            initUI2(rootView); // 초미세먼지
                                            initUI3(rootView); // 이산화질소
                                            initUI4(rootView); // 오존
                                            initUI5(rootView); // 일산화탄소
                                            initUI6(rootView); // 아황산가스
                                            initUI8(rootView); // 전국 대기 상태 : 초미세먼지

                                        } else {
                                            initUI(rootView); // 미세먼지 상단
                                            initUI1(rootView); // 미세먼지 하단
                                            initUI2(rootView); // 초미세먼지
                                            initUI3(rootView); // 이산화질소
                                            initUI4(rootView); // 오존
                                            initUI5(rootView); // 일산화탄소
                                            initUI6(rootView); // 아황산가스
                                            initUI7(rootView); // 전국 대기 상태 : 미세먼지
                                        }
                                    }
                                });
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

        text1 = (TextView) getActivity().findViewById(R.id.dust_txt_location2);
        text2 = (TextView) getActivity().findViewById(R.id.dust_update_time2);

        text1.setText(locationActivity.getTextView5());
        text2.setText(timeActivity.getTime());

        switchBtn = (Switch) getActivity().findViewById(R.id.dust_switch);
        switchBtn.setChecked(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dustActivity.setDustXmlData(locationActivity.getTextView5(), locationActivity.getTextView3());
              //  dustActivity.setDustXmlData2(timeActivity.getTime2());
                dustActivity.setDustXmlData2(timeActivity.getTime2(),"PM10");
                dustActivity.setDustXmlData3(timeActivity.getTime2(),"PM25");
                //dustActivity.setDustXmlData2("seoul");

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        initUI(rootView); // 미세먼지 상단
                        initUI1(rootView); // 미세먼지 하단
                        initUI2(rootView); // 초미세먼지
                        initUI3(rootView); // 이산화질소
                        initUI4(rootView); // 오존
                        initUI5(rootView); // 일산화탄소
                        initUI6(rootView); // 아황산가스
                        initUI7(rootView); // 전국 대기 상태 : 미세먼지

                        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    initUI0(rootView); // 초미세먼지 상단
                                    initUI1(rootView); // 미세먼지 하단
                                    initUI2(rootView); // 초미세먼지
                                    initUI3(rootView); // 이산화질소
                                    initUI4(rootView); // 오존
                                    initUI5(rootView); // 일산화탄소
                                    initUI6(rootView); // 아황산가스
                                    initUI8(rootView); // 전국 대기 상태 : 초미세먼지
                                } else {
                                    initUI(rootView); // 미세먼지 상단
                                    initUI1(rootView); // 미세먼지 하단
                                    initUI2(rootView); // 초미세먼지
                                    initUI3(rootView); // 이산화질소
                                    initUI4(rootView); // 오존
                                    initUI5(rootView); // 일산화탄소
                                    initUI6(rootView); // 아황산가스
                                    initUI7(rootView); // 전국 대기 상태 : 미세먼지
                                }
                            }
                        });
                    }
                });
            }
        }).start();
    }

    // 상단 얼굴 : 미세먼지
    public void initUI(ViewGroup rootView) {
        int pm10Value = Integer.parseInt(dustActivity.getpm10Value()); // 미세먼지 수치
        // 미세먼지
       // int pm10Value = dustpm10Value;
        if (0 <= pm10Value && pm10Value < 16) {
            imgDust = R.drawable.dust8;
            Dustpm10ValueText = "최고 좋음";
        } else if (16 <= pm10Value && pm10Value < 31) {
            imgDust = R.drawable.dust7;
            Dustpm10ValueText = "좋음";
        } else if (31 <= pm10Value && pm10Value < 41) {
            imgDust = R.drawable.dust6;
            Dustpm10ValueText = "양호";
        } else if (41 <= pm10Value && pm10Value < 51) {
            imgDust = R.drawable.dust5;
            Dustpm10ValueText = "보통";
        } else if (51 <= pm10Value && pm10Value < 76) {
            imgDust = R.drawable.dust4;
            Dustpm10ValueText = "나쁨";
        } else if (76 <= pm10Value && pm10Value < 101) {
            imgDust = R.drawable.dust3;
            Dustpm10ValueText = "상당히 나쁨";
        } else if (101 <= pm10Value && pm10Value < 151) {
            imgDust = R.drawable.dust2;
            Dustpm10ValueText = "매우 나쁨";
        } else if (151 <= pm10Value) {
            imgDust = R.drawable.dust1;
            Dustpm10ValueText = "최악";
        }
        ImageView dust_img = (ImageView) getActivity().findViewById(R.id.dust_img); // 미세먼지 상단 사진
        dust_img.setImageResource(imgDust);
    }

    // 현 위치 대기 상태 : 미세먼지
    public void initUI1(ViewGroup rootView) {
        dustpm10Value = Integer.parseInt(dustActivity.getpm10Value()); // 미세먼지 수치

        // 미세먼지
        int pm10Value = dustpm10Value;
        if (0 <= pm10Value && pm10Value < 16) {
            imgDust = R.drawable.dust8;
            Dustpm10ValueText = "최고 좋음";
        } else if (16 <= pm10Value && pm10Value < 31) {
            imgDust = R.drawable.dust7;
            Dustpm10ValueText = "좋음";
        } else if (31 <= pm10Value && pm10Value < 41) {
            imgDust = R.drawable.dust6;
            Dustpm10ValueText = "양호";
        } else if (41 <= pm10Value && pm10Value < 51) {
            imgDust = R.drawable.dust5;
            Dustpm10ValueText = "보통";
        } else if (51 <= pm10Value && pm10Value < 76) {
            imgDust = R.drawable.dust4;
            Dustpm10ValueText = "나쁨";
        } else if (76 <= pm10Value && pm10Value < 101) {
            imgDust = R.drawable.dust3;
            Dustpm10ValueText = "상당히 나쁨";
        } else if (101 <= pm10Value && pm10Value < 151) {
            imgDust = R.drawable.dust2;
            Dustpm10ValueText = "매우 나쁨";
        } else if (151 <= pm10Value) {
            imgDust = R.drawable.dust1;
            Dustpm10ValueText = "최악";
        }
        ImageView dust_day1_img = (ImageView) getActivity().findViewById(R.id.dust_day1_img); // 미세먼지 사진
        TextView dust_day1_grade = (TextView) getActivity().findViewById(R.id.dust_day1_grade); // 미세먼지 등급
        TextView dust_day1_value = (TextView) getActivity().findViewById(R.id.dust_day1_value); // 미세먼지 수치

        dust_day1_img.setImageResource(imgDust);
        dust_day1_grade.setText(Dustpm10ValueText);
        dust_day1_value.setText(dustpm10Value + " ㎍/㎥");
    }

    // 상단 얼굴 : 초미세먼지
    public void initUI0(ViewGroup rootView) {
        dustpm25Value = Integer.parseInt(dustActivity.getpm25Value()); // 초미세먼지 수치
        // 초미세먼지
        int pm125Value = dustpm25Value;
        if (0 <= pm125Value && pm125Value < 9) {
            imgDust1 = R.drawable.dust8;
            Dustpm25ValueText = "최고 좋음";
        } else if (9 <= pm125Value && pm125Value < 16) {
            imgDust1 = R.drawable.dust7;
            Dustpm25ValueText = "좋음";
        } else if (16 <= pm125Value && pm125Value < 21) {
            imgDust1 = R.drawable.dust6;
            Dustpm25ValueText = "양호";
        } else if (21 <= pm125Value && pm125Value < 26) {
            imgDust1 = R.drawable.dust5;
            Dustpm25ValueText = "보통";
        } else if (26 <= pm125Value && pm125Value < 38) {
            imgDust1 = R.drawable.dust4;
            Dustpm25ValueText = "나쁨";
        } else if (38 <= pm125Value && pm125Value < 51) {
            imgDust1 = R.drawable.dust3;
            Dustpm25ValueText = "상당히 나쁨";
        } else if (51 <= pm125Value && pm125Value < 76) {
            imgDust1 = R.drawable.dust2;
            Dustpm25ValueText = "매우 나쁨";
        } else if (76 <= pm125Value) {
            imgDust1 = R.drawable.dust1;
            Dustpm25ValueText = "최악";
        }
        ImageView dust_img = (ImageView) getActivity().findViewById(R.id.dust_img); // 미세먼지 상단 사진

        dust_img.setImageResource(imgDust1);
    }

    // 현 위치 대기 상태 : 초미세먼지
    public void initUI2(ViewGroup rootView) {
        dustpm25Value = Integer.parseInt(dustActivity.getpm25Value()); // 초미세먼지 수치
        // 초미세먼지
        int pm125Value = dustpm25Value;
        if (0 <= pm125Value && pm125Value < 9) {
            imgDust1 = R.drawable.dust8;
            Dustpm25ValueText = "최고 좋음";
        } else if (9 <= pm125Value && pm125Value < 16) {
            imgDust1 = R.drawable.dust7;
            Dustpm25ValueText = "좋음";
        } else if (16 <= pm125Value && pm125Value < 21) {
            imgDust1 = R.drawable.dust6;
            Dustpm25ValueText = "양호";
        } else if (21 <= pm125Value && pm125Value < 26) {
            imgDust1 = R.drawable.dust5;
            Dustpm25ValueText = "보통";
        } else if (26 <= pm125Value && pm125Value < 38) {
            imgDust1 = R.drawable.dust4;
            Dustpm25ValueText = "나쁨";
        } else if (38 <= pm125Value && pm125Value < 51) {
            imgDust1 = R.drawable.dust3;
            Dustpm25ValueText = "상당히 나쁨";
        } else if (51 <= pm125Value && pm125Value < 76) {
            imgDust1 = R.drawable.dust2;
            Dustpm25ValueText = "매우 나쁨";
        } else if (76 <= pm125Value) {
            imgDust1 = R.drawable.dust1;
            Dustpm25ValueText = "최악";
        }
        ImageView dust_day2_img = (ImageView) getActivity().findViewById(R.id.dust_day2_img); // 초미세먼지 사진
        TextView dust_day2_grade = (TextView) getActivity().findViewById(R.id.dust_day2_grade); // 초미세먼지 등급
        TextView dust_day2_value = (TextView) getActivity().findViewById(R.id.dust_day2_value); // 초미세먼지 수치

        dust_day2_img.setImageResource(imgDust1);
        dust_day2_grade.setText(Dustpm25ValueText);
        dust_day2_value.setText(dustpm25Value + " ㎍/㎥");
    }

    // 현 위치 대기 상태 : 이산화질소 수치
    public void initUI3(ViewGroup rootView) {
     //   dustno2Value = Integer.parseInt(dustActivity.getno2Value()); // 이산화질소 수치
        float no2Value = Float.parseFloat(dustActivity.getno2Value()); // 이산화질소 수치
        // 이산화질소 수치
    //    float no2Value = (float) dustno2Value;
        if (0 <= no2Value && no2Value < 0.02) {
            imgDust2 = R.drawable.dust8;
            Dustno2ValueText = "최고 좋음";
        } else if (0.02 <= no2Value && no2Value < 0.03) {
            imgDust2 = R.drawable.dust7;
            Dustno2ValueText = "좋음";
        } else if (0.03 <= no2Value && no2Value < 0.05) {
            imgDust2 = R.drawable.dust6;
            Dustno2ValueText = "양호";
        } else if (0.05 <= no2Value && no2Value < 0.06) {
            imgDust2 = R.drawable.dust5;
            Dustno2ValueText = "보통";
        } else if (0.06 <= no2Value && no2Value < 0.13) {
            imgDust2 = R.drawable.dust4;
            Dustno2ValueText = "나쁨";
        } else if (0.13 <= no2Value && no2Value < 0.2) {
            imgDust2 = R.drawable.dust3;
            Dustno2ValueText = "상당히 나쁨";
        } else if (0.2 <= no2Value && no2Value < 1.1) {
            imgDust2 = R.drawable.dust2;
            Dustno2ValueText = "매우 나쁨";
        } else if (1.1 <= no2Value && no2Value < 2) {
            imgDust2 = R.drawable.dust1;
            Dustno2ValueText = "최악";
        }
        ImageView dust_day3_img = (ImageView) getActivity().findViewById(R.id.dust_day3_img); // 이산화질소 수치 사진
        TextView dust_day3_grade = (TextView) getActivity().findViewById(R.id.dust_day3_grade); // 이산화질소 수치 등급
        TextView dust_day3_value = (TextView) getActivity().findViewById(R.id.dust_day3_value); // 이산화질소 수치

        dust_day3_img.setImageResource(imgDust2);
        dust_day3_grade.setText(Dustno2ValueText);
        dust_day3_value.setText(no2Value + " ppm");
    }

    // 현 위치 대기 상태 : 오존 농도
    public void initUI4(ViewGroup rootView) {
        float o3Value = Float.parseFloat(dustActivity.geto3Value()); //  농도
        // 오존 농도
     //   float o3Value = (float)dusto3Value;
        if (0 <= o3Value && o3Value < 0.02) {
            imgDust3 = R.drawable.dust8;
            Dusto3ValueText = "최고 좋음";
        } else if (0.02 <= o3Value && o3Value < 0.03) {
            imgDust3 = R.drawable.dust7;
            Dusto3ValueText = "좋음";
        } else if (0.03 <= o3Value && o3Value < 0.06) {
            imgDust3 = R.drawable.dust6;
            Dusto3ValueText = "양호";
        } else if (0.06 <= o3Value && o3Value < 0.09) {
            imgDust3 = R.drawable.dust5;
            Dusto3ValueText = "보통";
        } else if (0.09 <= o3Value && o3Value < 0.12) {
            imgDust3 = R.drawable.dust4;
            Dusto3ValueText = "나쁨";
        } else if (0.12 <= o3Value && o3Value < 0.15) {
            imgDust3 = R.drawable.dust3;
            Dusto3ValueText = "상당히 나쁨";
        } else if (0.15 <= o3Value && o3Value < 0.38) {
            imgDust3 = R.drawable.dust2;
            Dusto3ValueText = "매우 나쁨";
        } else if (0.38 <= o3Value) {
            imgDust3 = R.drawable.dust1;
            Dusto3ValueText = "최악";
        }
        ImageView dust_day4_img = (ImageView) getActivity().findViewById(R.id.dust_day4_img); // 오존 농도 사진
        TextView dust_day4_grade = (TextView) getActivity().findViewById(R.id.dust_day4_grade); // 오존 농도 등급
        TextView dust_day4_value = (TextView) getActivity().findViewById(R.id.dust_day4_value); // 오존 농도

        dust_day4_img.setImageResource(imgDust3);
        dust_day4_grade.setText(Dusto3ValueText);
        dust_day4_value.setText(o3Value + " ppm");
    }

    // 현 위치 대기 상태 : 일산화탄소
    public void initUI5(ViewGroup rootView) {
        float coValue = Float.parseFloat(dustActivity.getcoValue()); // 일산화탄소 수치
        // 일산화탄소
   //     float coValue = (float) dustcoValue;
        if (0 <= coValue && coValue < 1) {
            imgDust4 = R.drawable.dust8;
            DustcoValueText = "최고 좋음";
        } else if (1 <= coValue && coValue < 2) {
            imgDust4 = R.drawable.dust7;
            DustcoValueText = "좋음";
        } else if (2 <= coValue && coValue < 5.5) {
            imgDust4 = R.drawable.dust6;
            DustcoValueText = "양호";
        } else if (5.5 <= coValue && coValue < 9) {
            imgDust4 = R.drawable.dust5;
            DustcoValueText = "보통";
        } else if (9 <= coValue && coValue < 12) {
            imgDust4 = R.drawable.dust4;
            DustcoValueText = "나쁨";
        } else if (12 <= coValue && coValue < 15) {
            imgDust4 = R.drawable.dust3;
            DustcoValueText = "상당히 나쁨";
        } else if (15 <= coValue && coValue < 32) {
            imgDust4 = R.drawable.dust2;
            DustcoValueText = "매우 나쁨";
        } else if (32 <= coValue) {
            imgDust4 = R.drawable.dust1;
            DustcoValueText = "최악";
        }
        ImageView dust_day5_img = (ImageView) getActivity().findViewById(R.id.dust_day5_img); // 일산화탄소 사진
        TextView dust_day5_grade = (TextView) getActivity().findViewById(R.id.dust_day5_grade); // 일산화탄소 등급
        TextView dust_day5_value = (TextView) getActivity().findViewById(R.id.dust_day5_value); // 일산화탄소 수치

        dust_day5_img.setImageResource(imgDust4);
        dust_day5_grade.setText(DustcoValueText);
        dust_day5_value.setText(coValue + " ppm");
    }

    // 현 위치 대기 상태 : 아황산가스
    public void initUI6(ViewGroup rootView) {
        float so2Value = Float.parseFloat(dustActivity.getso2Value()); // 아황산가스 농도
        // 아황산가스
     //   float so2Value = (float) dustso2Value;
        if (0 <= so2Value && so2Value < 0.01) {
            imgDust5 = R.drawable.dust8;
            Dustso2ValueText = "최고 좋음";
        } else if (0.01 <= so2Value && so2Value < 0.02) {
            imgDust5 = R.drawable.dust7;
            Dustso2ValueText = "좋음";
        } else if (0.02 <= so2Value && so2Value < 0.04) {
            imgDust5 = R.drawable.dust6;
            Dustso2ValueText = "양호";
        } else if (0.04 <= so2Value && so2Value < 0.05) {
            imgDust5 = R.drawable.dust5;
            Dustso2ValueText = "보통";
        } else if (0.05 <= so2Value && so2Value < 0.1) {
            imgDust5 = R.drawable.dust4;
            Dustso2ValueText = "나쁨";
        } else if (0.1 <= so2Value && so2Value < 0.15) {
            imgDust5 = R.drawable.dust3;
            Dustso2ValueText = "상당히 나쁨";
        } else if (0.15 <= so2Value && so2Value < 0.6) {
            imgDust5 = R.drawable.dust2;
            Dustso2ValueText = "매우 나쁨";
        } else if (0.6 <= so2Value) {
            imgDust5 = R.drawable.dust1;
            Dustso2ValueText = "최악";
        }
        ImageView dust_day6_img = (ImageView) getActivity().findViewById(R.id.dust_day6_img); // 아황산가스 사진
        TextView dust_day6_grade = (TextView) getActivity().findViewById(R.id.dust_day6_grade); // 아황산가스 농도 등급
        TextView dust_day6_value = (TextView) getActivity().findViewById(R.id.dust_day6_value); // 아황산가스 농도

        dust_day6_img.setImageResource(imgDust5);
        dust_day6_grade.setText(Dustso2ValueText);
        dust_day6_value.setText(so2Value + " ppm");
    }

    // 전국 대기 상태 : 미세먼지
    public void initUI7(ViewGroup rootView) {

        TextView dust_list_title_txt = (TextView) getActivity().findViewById(R.id.dust_list_title_txt);
        dust_list_title_txt.setText("전국 대기 상태 : 미세먼지");

        arrayList1 = new ArrayList(){
            {
                add(dustActivity.getSeoul());
                add(dustActivity.getBusan());
                add(dustActivity.getDaegu());
                add(dustActivity.getIncheon());
                add(dustActivity.getGangwon());
                add(dustActivity.getDaejeon());
                add(dustActivity.getUlsan());
                add(dustActivity.getGyeonggi());
                add(dustActivity.getGangwon());
                add(dustActivity.getChungbuk());
                add(dustActivity.getChungnam());
                add(dustActivity.getJeonbuk());
                add(dustActivity.getJeonnam());
                add(dustActivity.getGyeongbuk());
                add(dustActivity.getGyeongnam());
                add(dustActivity.getJeju());
                add(dustActivity.getSejong());
            }
        };

        linearLayout = (LinearLayout) rootView.getRootView().findViewById(R.id.dust_list2);
        linearLayout.removeAllViews();
        // TextView textView1 = (TextView) rootView.getRootView().findViewById(R.id.dust_list_title_txt);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(50, 25, 50, 25);

        for ( int i=0 ; i < arrayList1.size(); i++ ) {

            int pm10Value = arrayList1.get(i);
            //  String pm10 = dustActivity.getBusan();
         //   int pm10Int = Integer.parseInt(pm10);
            if (0 <= pm10Value && pm10Value < 16) {
                imgDust = R.drawable.dust8;
                Dustpm10ValueText = "최고 좋음";
            } else if (16 <= pm10Value && pm10Value < 31) {
                imgDust = R.drawable.dust7;
                Dustpm10ValueText = "좋음";
            } else if (31 <= pm10Value && pm10Value < 41) {
                imgDust = R.drawable.dust6;
                Dustpm10ValueText = "양호";
            } else if (41 <= pm10Value && pm10Value < 51) {
                imgDust = R.drawable.dust5;
                Dustpm10ValueText = "보통";
            } else if (51 <= pm10Value && pm10Value < 76) {
                imgDust = R.drawable.dust4;
                Dustpm10ValueText = "나쁨";
            } else if (76 <= pm10Value && pm10Value < 101) {
                imgDust = R.drawable.dust3;
                Dustpm10ValueText = "상당히 나쁨";
            } else if (101 <= pm10Value && pm10Value < 151) {
                imgDust = R.drawable.dust2;
                Dustpm10ValueText = "매우 나쁨";
            } else if (151 <= pm10Value) {
                imgDust = R.drawable.dust1;
                Dustpm10ValueText = "최악";
            }

            TextView newTextView = new TextView(rootView.getContext());
            //   newTextView.setText(dustActivity.getSeoul());
            newTextView.setText(Dustpm10ValueText);
            newTextView.setTextAppearance(R.style.txt_black_18);
            //     newTextView.setText(arrayList1.get(i));
            //newTextView.setText(Dustpm10ValueText);
          //  newTextView.setTextColor(0xAA1e6de0);
            //newTextView.setGravity(Gravity.CENTER);
            // newTextView.setLayoutParams(new ViewGroup.LayoutParams(textView1.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));


            TextView newTextView2 = new TextView(rootView.getContext());
            newTextView2.setText(pm10Value +" ㎍/㎥");
            newTextView2.setTextAppearance(R.style.txt_black_18);
           // newTextView2.setTextColor(R.id.dust_list_title_txt);

            ImageView imageView1 = new ImageView(rootView.getContext());
            imageView1.setImageResource(imgDust);
            imageView1.setLayoutParams(new ViewGroup.LayoutParams(140, 140));
            imageView1.requestLayout();


            TextView newTextView3 = new TextView(rootView.getContext());
            newTextView3.setText(arrayListPlace.get(i));
            newTextView3.setTextAppearance(R.style.txt_black_18);
           // newTextView3.setTextColor(R.id.dust_list_title_txt);

            LinearLayout linearLayout1 = new LinearLayout(rootView.getContext());
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1.addView(newTextView3); // 지역
            linearLayout1.addView(imageView1); // 사진
            linearLayout1.addView(newTextView); // 등급
            linearLayout1.addView(newTextView2); // 수치

            linearLayout.addView(linearLayout1, layoutParams);

        }
    }

    // 전국 대기 상태 : 초미세먼지
    public void initUI8(ViewGroup rootView) {
        TextView dust_list_title_txt = (TextView) getActivity().findViewById(R.id.dust_list_title_txt);
        dust_list_title_txt.setText("전국 대기 상태 : 초미세먼지");

        arrayList2 = new ArrayList(){
            {
                add(dustActivity.getSeoul2());
                add(dustActivity.getBusan2());
                add(dustActivity.getDaegu2());
                add(dustActivity.getIncheon2());
                add(dustActivity.getGangwon2());
                add(dustActivity.getDaejeon2());
                add(dustActivity.getUlsan2());
                add(dustActivity.getGyeonggi2());
                add(dustActivity.getGangwon2());
                add(dustActivity.getChungbuk2());
                add(dustActivity.getChungnam2());
                add(dustActivity.getJeonbuk2());
                add(dustActivity.getJeonnam2());
                add(dustActivity.getGyeongbuk2());
                add(dustActivity.getGyeongnam2());
                add(dustActivity.getJeju2());
                add(dustActivity.getSejong2());
            }
        };

        linearLayout = (LinearLayout) rootView.getRootView().findViewById(R.id.dust_list2);
        linearLayout.removeAllViews();
        // TextView textView1 = (TextView) rootView.getRootView().findViewById(R.id.dust_list_title_txt);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(50, 25, 50, 25);

        for ( int i=0; i < arrayList2.size(); i++ ) {

            int pm25Value = arrayList2.get(i);
            if (0 <= pm25Value && pm25Value < 9) {
                imgDust1 = R.drawable.dust8;
                Dustpm25ValueText = "최고 좋음";
            } else if (9 <= pm25Value && pm25Value < 16) {
                imgDust1 = R.drawable.dust7;
                Dustpm25ValueText = "좋음";
            } else if (16 <= pm25Value && pm25Value < 21) {
                imgDust1 = R.drawable.dust6;
                Dustpm25ValueText = "양호";
            } else if (21 <= pm25Value && pm25Value < 26) {
                imgDust1 = R.drawable.dust5;
                Dustpm25ValueText = "보통";
            } else if (26 <= pm25Value && pm25Value < 38) {
                imgDust1 = R.drawable.dust4;
                Dustpm25ValueText = "나쁨";
            } else if (38 <= pm25Value && pm25Value < 51) {
                imgDust1 = R.drawable.dust3;
                Dustpm25ValueText = "상당히 나쁨";
            } else if (51 <= pm25Value && pm25Value < 76) {
                imgDust1 = R.drawable.dust2;
                Dustpm25ValueText = "매우 나쁨";
            } else if (76 <= pm25Value) {
                imgDust1 = R.drawable.dust1;
                Dustpm25ValueText = "최악";
            }

            TextView newTextView = new TextView(rootView.getContext());
            //   newTextView.setText(dustActivity.getSeoul());
            newTextView.setText(Dustpm25ValueText);
            newTextView.setTextAppearance(R.style.txt_black_18);
            //     newTextView.setText(arrayList1.get(i));
            //newTextView.setText(Dustpm10ValueText);
            //  newTextView.setTextColor(0xAA1e6de0);
            //newTextView.setGravity(Gravity.CENTER);
            // newTextView.setLayoutParams(new ViewGroup.LayoutParams(textView1.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));


            TextView newTextView2 = new TextView(rootView.getContext());
            newTextView2.setText(pm25Value + " ㎍/㎥");
            newTextView2.setTextAppearance(R.style.txt_black_18);
            // newTextView2.setTextColor(R.id.dust_list_title_txt);

            ImageView imageView1 = new ImageView(rootView.getContext());
            imageView1.setImageResource(imgDust1);
            imageView1.setLayoutParams(new ViewGroup.LayoutParams(140, 140));
            imageView1.requestLayout();


            TextView newTextView3 = new TextView(rootView.getContext());
            newTextView3.setText(arrayListPlace.get(i));
            newTextView3.setTextAppearance(R.style.txt_black_18);
            // newTextView3.setTextColor(R.id.dust_list_title_txt);

            LinearLayout linearLayout1 = new LinearLayout(rootView.getContext());
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1.addView(newTextView3); // 지역
            linearLayout1.addView(imageView1); // 사진
            linearLayout1.addView(newTextView); // 등급
            linearLayout1.addView(newTextView2); // 수치

            linearLayout.addView(linearLayout1, layoutParams);
        }
    }
}
