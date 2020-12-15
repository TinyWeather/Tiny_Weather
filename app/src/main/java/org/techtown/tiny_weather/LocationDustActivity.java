package org.techtown.tiny_weather;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class LocationDustActivity extends Fragment {
    LocationActivity locationActivity;

    String key="kd3zWLkxFKVIuT0XejOXR1qWycWNx03d21q75t5AHS2gIRKGQXQhqtwrvDWy3Huf04BaJZQL2vQHDvEkT8coDw%3D%3D";
    String umdName = "";

    // totalCount
    String totalCountalue;
    boolean totalCountCheck=false;
    public String gettotalCountValue() {
        return totalCountalue;
    }
    public void settotalCountValue(String totalCountalue) {
        this.totalCountalue = totalCountalue;
    }

    // TM 좌표 변환
    String tmXValue, tmYValue;
    boolean sidoNameCheck=false, sggNameCheck=false, umdNameCheck=false, tmXCheck=false, tmYCheck=false;
    public String getTmXValue() {
        return tmXValue;
    }
    public void setTmXValue(String tmXValue) {
        this.tmXValue = tmXValue;
    }
    public String getTmYValue() {
        return tmYValue;
    }
    public void setTmYValue(String tmYValue) {
        this.tmYValue = tmYValue;
    }

    // setLocationDustXmlData(__구 __동 : 목1동 => 목 검색) : totalCount 체크
    // totalCount 1개 : __구 ___동 (location.getTextView()) / 2개 이상 : __구 __(숫자)(동) 지운 것 (location.getTextView6())
    public void setLocationDustXmlData(String where){
        String queryUrl="http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getTMStdrCrdnt?"//요청 URL
                + "ServiceKey=" + key // 키
                + "&pageNo=1" //페이지 번호
                + "&numOfRows=1000" //한 페이지 결과 수
                + "&umdName=" + where; // 읍면동명
        StringBuffer buffer=new StringBuffer();

        try {
            URL url = new URL(queryUrl);

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser parser= factory.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            boolean totalCheck = false;

            System.out.println(queryUrl);
            System.out.println("============= LocationDust 파싱 시작=============");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch(parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("umdName")) {
                            totalCheck = true;
                        }
                        if (parser.getName().equals("totalCount")) {
                            totalCountCheck = true;
                        }
                        if (parser.getName().equals("message")) {
                            System.out.println("DustLocationActivity 에러");
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(umdNameCheck){
                            if(umdName.contains(parser.getText())){
                                totalCheck = true;
                            }
                            umdNameCheck = false;
                        }
                        if (totalCountCheck) {
                            if (totalCheck){
                                settotalCountValue(parser.getText());
                                System.out.println("============= settotalCount"+parser.getText()+"=============");
                            }
                            totalCountCheck = false;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }
            System.out.println("============= LocationDust 파싱 끝=============");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("============= LocationDust 파싱 에러=============");
        }
    }

    // setLocationDustXmlData(totalCount==1 : textView() / totalCount!=1 : textView7())
    public void setLocationDustXmlData2(String umdName) {

       String queryUrl = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getTMStdrCrdnt?"//요청 URL
               + "ServiceKey=" + key // 키
               + "&pageNo=1" //페이지 번호
               + "&numOfRows=1000" //한 페이지 결과 수
               + "&umdName=" + umdName; // 읍면동명
       StringBuffer buffer = new StringBuffer();

       try {
           URL url = new URL(queryUrl);

           XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
           XmlPullParser parser = factory.newPullParser();
           parser.setInput(url.openStream(), null);

           int parserEvent = parser.getEventType();
           boolean locationCheck = false;

           System.out.println(queryUrl);
           System.out.println("============= LocationDust 파싱 시작=============");

           while (parserEvent != XmlPullParser.END_DOCUMENT) {
               switch (parserEvent) {
                   case XmlPullParser.START_TAG:
                       if (parser.getName().equals("item")) { // 목록
                           locationCheck = false;
                       }
                       if (parser.getName().equals("sidoName")) { // 시도
                           sidoNameCheck = true;
                       }
                       if (parser.getName().equals("sggName")) { // 시군구
                           sggNameCheck = true;
                       }
                       if (parser.getName().equals("umdName")) { // 읍면동
                           umdNameCheck = true;
                       }
                       if (parser.getName().equals("tmX")) { // TM측정방식 X좌표
                           tmXCheck = true;
                       }
                       if (parser.getName().equals("tmY")) { // TM측정방식 Y좌표
                           tmYCheck = true;
                       }
                       if (parser.getName().equals("message")) {
                           System.out.println("DustLocationActivity 에러");
                       }
                       break;

                   case XmlPullParser.TEXT://parser가 내용에 접근했을때
                       if (umdNameCheck) {
                           if (umdName.contains(parser.getText())) {
                               locationCheck = true;
                           }
                           umdNameCheck = false;
                       }
                       if (tmXCheck) {
                           if (locationCheck) {
                               setTmXValue(parser.getText());
                               System.out.println("============= setTmXValue  " + parser.getText() + "=============");
                           }
                           tmXCheck = false;
                       }
                       if (tmYCheck) {
                           if (locationCheck) {
                               setTmYValue(parser.getText());
                               System.out.println("============= setTmYValue  " + parser.getText() + "=============");
                           }
                           tmYCheck = false;
                       }
                       break;
                   case XmlPullParser.END_TAG:
                       break;
               }
               parserEvent = parser.next();
           }
           System.out.println("============= LocationDust 파싱 끝=============");
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("============= LocationDust 파싱 에러=============");
       }
   }
}