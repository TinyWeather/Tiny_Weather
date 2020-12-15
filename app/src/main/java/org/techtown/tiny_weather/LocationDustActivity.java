package org.techtown.tiny_weather;

import androidx.fragment.app.Fragment;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class LocationDustActivity extends Fragment {
    LocationActivity locationActivity;

    String key="kd3zWLkxFKVIuT0XejOXR1qWycWNx03d21q75t5AHS2gIRKGQXQhqtwrvDWy3Huf04BaJZQL2vQHDvEkT8coDw%3D%3D";
    String umdName = "";

    String sidoNameValue, sggNameValue, umdNameValue, tmXValue, tmYValue;
    boolean sidoNameCheck=false, sggNameCheck=false, umdNameCheck=false, tmXCheck=false, tmYCheck=false;

    public String gettmXValue() {
        return tmXValue;
    }
    public void settmXValue(String tmXValue) {
        this.tmXValue = tmXValue;
    }
    public String getTmYValue() {
        return tmYValue;
    }
    public void settmYValue(String tmYValue) {
        this.tmYValue = tmYValue;
    }


    // setLocationDustXmlData(__구 __동)
    public void setLocationDustXmlData(String umdName){

        String queryUrl="http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getTMStdrCrdnt?"//요청 URL
                + "ServiceKey=" + key // 키
                + "&pageNo=1" //페이지 번호
                + "&numOfRows=1000" //한 페이지 결과 수
                + "&umdName=" + umdName; // 읍면동명
        StringBuffer buffer=new StringBuffer();

        try {
            URL url = new URL(queryUrl);

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser parser= factory.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            boolean locationCheck = false;

            System.out.println(queryUrl);
            System.out.println("============= LocationDust 파싱 시작=============");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch(parserEvent) {
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("item")){ // 목록
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
                        if(umdNameCheck){
                            if(umdName.contains(parser.getText())){
                                locationCheck = true;
                            }
                            umdNameCheck = false;
                        }
        /*                if (sidoNameCheck) {
                            if (location.contains(parser.getText())) {
                                locationCheck = true;
                            }
                            sidoNameCheck = false;
                        }*/
        /*                if (sidoNameCheck) {
                            if (locationCheck) {
                                setsidoNameValue(parser.getText());
                            }
                            sidoNameCheck = false;
                        }
                        if (sggNameCheck) {
                            if (locationCheck) {
                                setsggNameValue(parser.getText());
                            }
                            sggNameCheck = false;
                        }
                        if (umdNameCheck) {
                            if (locationCheck){
                                setumdNameValue(parser.getText());
                            }
                            umdNameCheck = false;
                        }*/
                        if (tmXCheck) {
                            if (locationCheck){
                                settmXValue(parser.getText());
                                System.out.println("============= settmXValue  "+parser.getText()+"=============");
                            }
                            tmXCheck = false;
                        }
                        if (tmYCheck) {
                            if (locationCheck){
                               settmYValue(parser.getText());
                                System.out.println("============= settmYValue  "+parser.getText()+"=============");
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
