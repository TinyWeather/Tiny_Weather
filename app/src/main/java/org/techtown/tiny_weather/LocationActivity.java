package org.techtown.tiny_weather;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity {
    private Context context;
    private Activity activity;
    private static final int REQUEST_CODE_LOCATION = 2;
    private LocationManager locationManager;

    public LocationActivity(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void setTextView() {
        //사용자의 위치 수신을 위한 세팅
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        //사용자의 현재 위치
        Location userLocation = getMyLocation();
        if( userLocation != null ) {
            double latitude = userLocation.getLatitude();
            double longitude = userLocation.getLongitude();
            System.out.println("★★★ 현재 내 위치값 : "+latitude+","+longitude+" ★★★");
            String address = getAddress(activity.getBaseContext(), latitude, longitude);

            System.out.println("★★★ 현재 내 위치값 : "+address+" ★★★");
            TextView textView = (TextView) activity.findViewById(R.id.txt_location2);
            if(!address.equals("주소를 가져올 수 없습니다.") && !address.equals("현재 위치를 확인 할 수 없습니다.")) {
                String strAddress[] = address.split(" ");
                textView.setText(strAddress[2] + " " + strAddress[3]);
            }
            else {
                textView.setText("위치 조회 불가");
            }
        }
    }

    /* 사용자의 위치를 수신 */
    public Location getMyLocation() {
        Location currentLocation = null;
        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("★★★ 사용자에게 권한을 요청해야함 ★★★");
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_CODE_LOCATION);
            getMyLocation(); //이건 써도되고 안써도 되지만, 전 권한 승인하면 즉시 위치값 받아오려고 썼습니다!
        }
        else {
            System.out.println("★★★ 권한요청 안해도됨 ★★★");

            // 수동으로 위치 구하기
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
            if (currentLocation != null) {
                double lng = currentLocation.getLongitude();
                double lat = currentLocation.getLatitude();
            }
        }
        return currentLocation;
    }

    /* 위도,경도로 주소구하기 */
    public static String getAddress(Context mContext, double lat, double lng) {
        String nowAddress ="현재 위치를 확인 할 수 없습니다.";
        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;
        try {
            if (geocoder != null) {
                //세번째 파라미터는 좌표에 대해 주소를 리턴 받는 갯수로
                //한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 최대갯수 설정
                address = geocoder.getFromLocation(lat, lng, 1);

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
}
