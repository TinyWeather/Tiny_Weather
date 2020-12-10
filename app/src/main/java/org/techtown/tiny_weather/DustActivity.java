package org.techtown.tiny_weather;

import android.content.Context;

import androidx.fragment.app.Fragment;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class DustActivity extends Fragment {
    LocationActivity locationActivity;

    String key="kd3zWLkxFKVIuT0XejOXR1qWycWNx03d21q75t5AHS2gIRKGQXQhqtwrvDWy3Huf04BaJZQL2vQHDvEkT8coDw%3D%3D";

    String pm10Value, pm10Grade1h;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        locationActivity = new LocationActivity(context);
    }

    /*미세먼지 수치*/
    public String getpm10Value() {
        return pm10Value;
    }
    public void setpm10Value(String pm10Value) {
        this.pm10Value = pm10Value;
    }

    boolean dataTimeCheck=false, mangNameCheck=false, stationNameCheck=false, so2ValueCheck=false,  coValueCheck=false,  o3ValueCheck=false,  no2ValueCheck=false;
    boolean pm10ValueCheck=false,pm10Value24Check=false,  pm25ValueCheck=false,  pm25Value24Check=false,  khaiValueCheck=false;
    boolean khaiGradeCheck=false, so2GradeCheck=false,  coGradeCheck=false,  o3GradeCheck=false,  no2GradeCheck=false;
    boolean pm10GradeCheck=false, pm25GradeCheck=false,  pm10Grade1hCheck=false,  pm25Grade1hCheck=false;

    public void setDustXmlData(String location){

        locationActivity = new LocationActivity(getContext());
        // 현 위치 시 이름
        //String sidoName = locationActivity.getTextView3();

        String queryUrl="http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?"//요청 URL
                + "ServiceKey=" + key // 키
                + "&pageNo=1" //페이지 번호
                + "&numOfRows=1000" //한 페이지 결과 수
                + "&sidoName=" + "서울" //시도 이름
                + "&searchCondition=DAILY" //요청 데이터기간 시간: hour 하루 : daily
                + "&ver=1.3"; // 버전
        StringBuffer buffer=new StringBuffer();

        try {
            URL url= new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser parser= factory.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            boolean locationCheck = false;

            System.out.println(queryUrl);
            System.out.println("============= 미세먼지 파싱 시작=============");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
           //             System.out.println("============= 미세먼지 태그 만남=============");
                        if(parser.getName().equals("item")){ // 목록
                            locationCheck = false;
                        }
                        if(parser.getName().equals("dataTime")){ // 측정일
                            dataTimeCheck = true;
                        }
                        if(parser.getName().equals("mangName")){ // 측정 망 정보
                            mangNameCheck = true;
                        }
                        if(parser.getName().equals("stationName")){ // 측정소 명 ex)강서구
                            stationNameCheck = true;
                        }
                        if(parser.getName().equals("so2Value")){ //
                            so2ValueCheck = true;
                        }
                        if(parser.getName().equals("coValue")){ //
                            coValueCheck = true;
                        }
                        if(parser.getName().equals("o3Value")){ //
                            o3ValueCheck = true;
                        }
                        if(parser.getName().equals("no2Value")){ //
                            no2ValueCheck = true;
                        }
                        if(parser.getName().equals("pm10Value")){ // 미세먼지
                            pm10ValueCheck = true;
                        }
                        if(parser.getName().equals("pm10Value24")){ //미세먼지(PM10) 24시간예측이동농도
                            pm10Value24Check = true;
                        }
                        if(parser.getName().equals("pm25Value")){ // 초미세먼지
                            pm25ValueCheck = true;
                        }
                        if(parser.getName().equals("pm25Value24")){ // 초미세먼지 24시간예측이동농도
                            pm25Value24Check = true;
                        }
                        if(parser.getName().equals("khaiValue")){ //
                            khaiValueCheck = true;
                        }
                        if(parser.getName().equals("khaiGrade")){ //
                            khaiGradeCheck = true;
                        }
                        if(parser.getName().equals("so2Grade")){ //
                            so2GradeCheck = true;
                        }
                        if(parser.getName().equals("coGrade")){ //
                            coGradeCheck = true;
                        }
                        if(parser.getName().equals("o3Grade")){ //
                            o3GradeCheck = true;
                        }
                        if(parser.getName().equals("no2Grade")){ //
                            no2GradeCheck = true;
                        }
                        if(parser.getName().equals("pm10Grade")){ // 미세먼지 24시간 등급
                            pm10GradeCheck = true;
                        }
                        if(parser.getName().equals("pm25Grade")){ // 초미세먼지 24시간 등급
                            pm25GradeCheck = true;
                        }
                        if(parser.getName().equals("pm10Grade1h")){ // 미세먼지 1시간 등급
                            pm10Grade1hCheck = true;
                        }
                        if(parser.getName().equals("pm25Grade1h")){ // 초미세먼지 1시간 등급
                            pm25Grade1hCheck = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            System.out.println("에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
            //            System.out.println("============= 미세먼지 텍스트 만남=============");
            //            System.out.println("============= 미세먼지  "+parser.getText()+"=============");
                        if(dataTimeCheck) {
                            System.out.println(parser.getText());
                            dataTimeCheck = false;
                        }
                        if(mangNameCheck) {
                            System.out.println(parser.getText());
                            mangNameCheck = false;
                        }
                        if(stationNameCheck){
                            if(location.contains(parser.getText())){
                                locationCheck = true;
                            }
                            System.out.println(parser.getText());
                            stationNameCheck = false;
                        }
                        if(so2ValueCheck) {
                            System.out.println(parser.getText());
                            so2ValueCheck = false;
                        }
                        if(coValueCheck) {
                            System.out.println(parser.getText());
                            coValueCheck = false;
                        }
                        if(o3ValueCheck) {
                            System.out.println(parser.getText());
                            o3ValueCheck = false;
                        }
                        if(no2ValueCheck) {
                            System.out.println(parser.getText());
                            no2ValueCheck = false;
                        }
                        if(pm10ValueCheck) {
                            System.out.println(parser.getText());
                            if(locationCheck)
                                setpm10Value(parser.getText());
                            pm10ValueCheck = false;
                        }
                        if(pm10Value24Check) {
                            System.out.println(parser.getText());
                            pm10Value24Check = false;
                        }
                        if(pm25ValueCheck) {
                            System.out.println(parser.getText());
                            pm25ValueCheck = false;
                        }
                        if(pm25Value24Check) {
                            System.out.println(parser.getText());
                            pm25Value24Check = false;
                        }
                        if(khaiValueCheck) {
                            System.out.println(parser.getText());
                            khaiValueCheck = false;
                        }
                        if(khaiGradeCheck) {
                            System.out.println(parser.getText());
                            khaiGradeCheck = false;
                        }
                        if(so2GradeCheck) {
                            System.out.println(parser.getText());
                            so2GradeCheck = false;
                        }
                        if(coGradeCheck) {
                            System.out.println(parser.getText());
                            coGradeCheck = false;
                        }
                        if(o3GradeCheck) {
                            System.out.println(parser.getText());
                            o3GradeCheck = false;
                        }
                        if(no2GradeCheck) {
                            System.out.println(parser.getText());
                            no2GradeCheck = false;
                        }
                        if(pm10GradeCheck) {
                            System.out.println(parser.getText());
                            pm10GradeCheck = false;
                        }
                        if(pm25GradeCheck) {
                            System.out.println(parser.getText());
                            pm25GradeCheck = false;
                        }
                        if(pm10Grade1hCheck) {
                            System.out.println(parser.getText());
                            pm10Grade1hCheck = false;
                        }
                        if(pm25Grade1hCheck) {
                            System.out.println(parser.getText());
                            pm25Grade1hCheck = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }

        //    System.out.println("============= 미세먼지 파싱 끝=============");
        } catch (Exception e) {
            e.printStackTrace();
        //    System.out.println("============= 미세먼지 파싱 에러=============");
        }
    }
}
