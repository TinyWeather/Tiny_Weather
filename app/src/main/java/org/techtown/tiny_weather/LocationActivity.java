package org.techtown.tiny_weather;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends Service implements LocationListener {
    private final Context mContext;
    int count = 0;
    boolean isGPSEnable = false;

    boolean isNetWorkEnable = false;

    boolean isGetLocation = false;

    Location location;
    double lat;
    double lon;

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    private static final long MIN_DISTANCE_UPDATE = 10;
    private static final long MIN_TIME_UPDATE = 1000 * 10 * 1;

    protected LocationManager locationManager;

    public LocationActivity(Context mContext) {
        this.mContext = mContext;
        getLocation();
    }

    @SuppressLint("MissingPermission")
    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);


            isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            isNetWorkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


            if (!isGPSEnable && !isNetWorkEnable) {
            } else {
                this.isGetLocation = true;
                if (isNetWorkEnable) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, MIN_DISTANCE_UPDATE, this);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            lat = location.getLatitude();
                            lon = location.getLongitude();
                        }
                    }
                }
                if (isGPSEnable) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, MIN_DISTANCE_UPDATE, this);
                    if (location == null) {

                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                lat = location.getLatitude();
                                lon = location.getLongitude();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public double getLatitude() {
        if (location != null)
            lat = location.getLatitude();
        return lat;
    }

    public double getLongitude() {
        if (location != null)
            lon = location.getLongitude();
        return lon;
    }

    public boolean isGetLocation() {
        return this.isGetLocation;
    }

    public void stopUsingGPS() {
        if (locationManager != null)
            locationManager.removeUpdates(LocationActivity.this);
    }

    public String getTextView() {
        locationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
        String getAddress = "위치 조회 불가";
        //사용자의 현재 위치
        if( location != null ) {
            System.out.println("★★★ 현재 내 위치값 : "+lat+","+lon+" ★★★");
            String address = getAddress(mContext, lat, lon);

            System.out.println("★★★ 현재 내 위치값 : "+address+" ★★★");
            if(!address.equals("주소를 가져올 수 없습니다.") && !address.equals("현재 위치를 확인 할 수 없습니다.")) {
                if(address.contains(","))
                    address = address.replace(",", "");

                String strAddress[] = address.split(" ");

                if(strAddress.length > 4)
                    if(address.contains("대한민국"))
                        getAddress = strAddress[2] + " " + strAddress[3];
                    else
                        getAddress = strAddress[3] + " " + strAddress[2];
                else
                    getAddress = address;
            }
            else {
                getAddress = "위치 조회 불가";
            }
        }

        return getAddress;
    }

    public String getTextView2() {
        locationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
        String getAddress = "위치 조회 불가";
        //사용자의 현재 위치
        if( location != null ) {
            System.out.println("★★★ 현재 내 위치값 : "+lat+","+lon+" ★★★");
            String address = getAddress(mContext, lat, lon);

            System.out.println("★★★ 현재 내 위치값 : "+address+" ★★★");
            if(!address.equals("주소를 가져올 수 없습니다.") && !address.equals("현재 위치를 확인 할 수 없습니다.")) {
                if(address.contains(","))
                    address = address.replace(",", "");

                String strAddress[] = address.split(" ");

                if(strAddress.length > 4)
                    if(address.contains("대한민국"))
                        getAddress = strAddress[1];
                    else
                        getAddress = strAddress[4];
                else
                    getAddress = address;
            }
            else {
                getAddress = "위치 조회 불가";
            }
        }

        return getAddress;
    }

    public static String getAddress(Context mContext, double lat, double lon) {
        String nowAddress ="현재 위치를 확인 할 수 없습니다.";
        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;
        try {
            if (geocoder != null) {
                //세번째 파라미터는 좌표에 대해 주소를 리턴 받는 갯수로
                //한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 최대갯수 설정
                address = geocoder.getFromLocation(lat, lon, 1);

                if (address != null && address.size() > 0) {
                    // 주소 받아오기
                    String currentLocationAddress = address.get(0).getAddressLine(0).toString();
                    nowAddress  = currentLocationAddress;
                }
            }
        } catch (IOException e) {
            nowAddress = "주소를 가져올 수 없습니다.";
            System.out.println("주소를 가져올 수 없습니다.");
            e.printStackTrace();
        }
        return nowAddress;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
