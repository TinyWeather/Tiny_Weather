package org.techtown.tiny_weather;

import android.content.Context;

import androidx.fragment.app.Fragment;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class DustActivity extends Fragment {
    LocationActivity locationActivity;
   // TimeActivity timeActivity;

    String key="kd3zWLkxFKVIuT0XejOXR1qWycWNx03d21q75t5AHS2gIRKGQXQhqtwrvDWy3Huf04BaJZQL2vQHDvEkT8coDw%3D%3D";
    String What = "";
    String sidoName = "";

    String pm10Value, pm25Value, no2Value, o3Value, coValue, so2Value;

    String dataTime, seoulValue, busanValue, daeguValue, incheonValue, gwangjuValue, daejeonValue, ulsanValue, sejongValue;
    String gyeonggiValue, gangwonValue, chungbukValue, chungnamValue, jeonbukValue, jeonnamValue, gyeongbukValue, gyeongnamValue, jejuValue;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        locationActivity = new LocationActivity(context);
    //    timeActivity = new TimeActivity();
    }

    /*미세먼지 수치*/
    public String getpm10Value() {
        return pm10Value;
    }
    public void setpm10Value(String pm10Value) {
        this.pm10Value = pm10Value;
    }
    /*초미세먼지 수치*/
    public String getpm25Value() {
        return pm25Value;
    }
    public void setpm25Value(String pm25Value) {
        this.pm25Value = pm25Value;
    }
    /*이산화질소 수치*/
    public String getno2Value() {
        return no2Value;
    }
    public void setno2Value(String no2Value) {
        this.no2Value = no2Value;
    }
    /*오존 농도*/
    public String geto3Value() {
        return o3Value;
    }
    public void seto3Value(String o3Value) {
        this.o3Value = o3Value;
    }
    /*일산화탄소 수치*/
    public String getcoValue() {
        return coValue;
    }
    public void setcoValue(String coValue) {
        this.coValue = coValue;
    }
    /*아황산가스 농도*/
    public String getso2Value() {
        return so2Value;
    }
    public void setso2Value(String so2Value) {
        this.so2Value = so2Value;
    }

    /*지역*/
    public  String getDataTime(){
        return dataTime;
    }
    public void setDataTime(String dataTime){
        this.dataTime = dataTime;
    }
    public String getSeoul() {
        return seoulValue;
    }
    public void setSeoul(String seoulValue) {
        this.seoulValue = seoulValue;
    }
    public String getBusan() {
        return busanValue;
    }
    public void setBusan(String busanValue) {
        this.busanValue = busanValue;
    }
    public String getDaegu() {
        return daeguValue;
    }
    public void setDaegu(String daeguValue) {
        this.daeguValue = daeguValue;
    }
    public String getIncheon() {
        return incheonValue;
    }
    public void setIncheon(String incheonValue) {
        this.incheonValue = incheonValue;
    }
    public String getGwangju() {
        return gwangjuValue;
    }
    public void setGwangju(String gwangjuValue) {
        this.gwangjuValue = gwangjuValue;
    }
    public String getDaejeon() {
        return daejeonValue;
    }
    public void setDaejeon(String daejeonValue) {
        this.daejeonValue = daejeonValue;
    }
    public String getUlsan() {
        return ulsanValue;
    }
    public void setUlsan(String ulsanValue) {
        this.ulsanValue = ulsanValue;
    }
    public String getSejong() {
        return sejongValue;
    }
    public void setSejong(String sejongValue) {
        this.sejongValue = sejongValue;
    }
    public String getGyeonggi() {
        return gyeonggiValue;
    }
    public void setGyeonggi(String gyeonggiValue) {
        this.gyeonggiValue = gyeonggiValue;
    }
    public String getGangwon() {
        return gangwonValue;
    }
    public void setGangwon(String gangwonValue) {
        this.gangwonValue = gangwonValue;
    }
    public String getChungbuk() {
        return chungbukValue;
    }
    public void setChungbuk(String chungbukValue) {
        this.chungbukValue = chungbukValue;
    }
    public String getChungnam() {
        return chungnamValue;
    }
    public void setChungnam(String chungnamValue) {
        this.chungnamValue = chungnamValue;
    }
    public String getJeonbuk() {
        return jeonbukValue;
    }
    public void setJeonbuk(String jeonbukValue) {
        this.jeonbukValue = jeonbukValue;
    }
    public String getJeonnam() {
        return jeonnamValue;
    }
    public void setJeonnam(String jeonnamValue) {
        this.jeonnamValue = jeonnamValue;
    }
    public String getGyeongbuk() {
        return gyeongbukValue;
    }
    public void setGyeongbuk(String gyeongbukValue) {
        this.gyeongbukValue = gyeongbukValue;
    }
    public String getGyeongnam() {
        return gyeongnamValue;
    }
    public void setGyeongnam(String gyeongnamValue) {
        this.gyeongnamValue = gyeongnamValue;
    }
    public String getJeju() {
        return jejuValue;
    }
    public void setJeju(String jejuValue) {
        this.jejuValue = jejuValue;
    }

    boolean dataTimeCheck=false, mangNameCheck=false, stationNameCheck=false;
    boolean so2ValueCheck=false,  coValueCheck=false,  o3ValueCheck=false,  no2ValueCheck=false, pm10ValueCheck=false, pm25ValueCheck=false;

    boolean itemCodeCheck=false, dataGubunCheck=false;
    boolean seoulCheck=false, busanCheck=false, daeguCheck=false, incheonCheck=false, gwangjuCheck=false, daejeonCheck=false, ulsanCheck=false, gyeonggiCheck=false, gangwonCheck=false;
    boolean chungbukCheck=false, chungnamCheck=false, jeonbukCheck=false, jeonnamCheck=false, gyeongbukCheck=false, gyeongnamCheck=false, jejuCheck=false, sejongCheck=false;

    public void setDustXmlData(String location, String sidoName){

        locationActivity = new LocationActivity(getContext());
        // 현 위치 시 이름

        String queryUrl="http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?"//요청 URL
                + "ServiceKey=" + key // 키
                + "&pageNo=1" //페이지 번호
                + "&numOfRows=1000" //한 페이지 결과 수
                + "&sidoName=" + sidoName //시도 이름
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
            System.out.println("============= DUST1 파싱 시작=============");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
           //             System.out.println("============= 미세먼지 태그 만남=============");
                        if(parser.getName().equals("item")){ // 목록
                            locationCheck = false;
                        }
      /*                  if(parser.getName().equals("dataTime")){ // 측정일
                            dataTimeCheck = true;
                        }*/
                        if(parser.getName().equals("mangName")){ // 측정 망 정보
                            mangNameCheck = true;
                        }
                        if(parser.getName().equals("stationName")){ // 측정소 명 ex)강서구
                            stationNameCheck = true;
                        }
                        if(parser.getName().equals("so2Value")){ // 아황산가스 농도
                            so2ValueCheck = true;
                        }
                        if(parser.getName().equals("coValue")){ // 일산화탄소 수치
                            coValueCheck = true;
                        }
                        if(parser.getName().equals("o3Value")){ // 오존 농도
                            o3ValueCheck = true;
                        }
                        if(parser.getName().equals("no2Value")){ // 이산화질소 수치
                            no2ValueCheck = true;
                        }
                        if(parser.getName().equals("pm10Value")){ // 미세먼지
                            pm10ValueCheck = true;
                        }
                        if(parser.getName().equals("pm25Value")){ // 초미세먼지
                            pm25ValueCheck = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            System.out.println("미세먼지 에러");
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
            //            System.out.println("============= 미세먼지 텍스트 만남=============");
            //            System.out.println("============= 미세먼지  "+parser.getText()+"=============");
                    /*    if(dataTimeCheck) {
                            if(location.contains(parser.getText())){
                                locationCheck = true;
                            }
                            dataTimeCheck = false;
                        }*/
                        if(mangNameCheck) {
                            //System.out.println(parser.getText());
                            mangNameCheck = false;
                        }
                        if(stationNameCheck){
                            if(location.contains(parser.getText())){
                                locationCheck = true;
                            }
                            stationNameCheck = false;
                        }
                        if(so2ValueCheck) {
                            if(locationCheck)
                                setso2Value(parser.getText());
                            so2ValueCheck = false;
                        }
                        if(coValueCheck) {
                            if(locationCheck)
                                setcoValue(parser.getText());
                            coValueCheck = false;
                        }
                        if(o3ValueCheck) {
                            if(locationCheck)
                                seto3Value(parser.getText());
                            o3ValueCheck = false;
                        }
                        if(no2ValueCheck) {
                            if(locationCheck)
                                setno2Value(parser.getText());
                            no2ValueCheck = false;
                        }
                        if(pm10ValueCheck) {
                            if(locationCheck)
                                setpm10Value(parser.getText());
                            pm10ValueCheck = false;
                        }
                        if(pm25ValueCheck) {
                            if(locationCheck)
                                setpm25Value(parser.getText());
                            pm25ValueCheck = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }
           System.out.println("============= DUST1 파싱 끝=============");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("============= DUST1 파싱 에러=============");
        }
    }

    public void setDustXmlData2(String time){

        String queryUrl="http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst?"//요청 URL
                + "ServiceKey=" + key // 키
                + "&pageNo=1" //페이지 번호
                + "&numOfRows=1000" //한 페이지 결과 수
                + "&itemCode="+"PM10" // 	측정항목 구분 (SO2, CO, O3, NO2, PM10, PM25) : 미세먼지
                + "&dataGubun=HOUR"; // 요청 자료 구분 (시간평균 : HOUR, 일평균 : DAILY)
        StringBuffer buffer=new StringBuffer();

        try {
            URL url= new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser parser= factory.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            boolean timeCheck = false;

            System.out.println(queryUrl);
            System.out.println("============= DUST2 파싱 시작=============");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        //             System.out.println("============= 미세먼지 태그 만남=============");
                        if(parser.getName().equals("item")){ // 목록
                            timeCheck = false;
                        }
                        if(parser.getName().equals("dataTime")){ // 측정일
                            dataTimeCheck = true;
                        }
                        if(parser.getName().equals("itemCode")){ // 측정항목
                            itemCodeCheck = true;
                        }
                        if(parser.getName().equals("dataGubun")){ // 자료구분
                            dataGubunCheck = true;
                        }
                        if(parser.getName().equals("seoul")){ // 서울
                            seoulCheck = true;
                        }
                        if(parser.getName().equals("busan")){ // 부산
                            busanCheck = true;
                        }
                        if(parser.getName().equals("daegu")){ // 대구
                            daeguCheck = true;
                        }
                        if(parser.getName().equals("incheon")){ // 인천
                            incheonCheck = true;
                        }
                        if(parser.getName().equals("gwangju")){ // 광주
                            gwangjuCheck = true;
                        }
                        if(parser.getName().equals("daejeon")){ // 대전
                            daejeonCheck = true;
                        }
                        if(parser.getName().equals("ulsan")){ // 울산
                            ulsanCheck = true;
                        }
                        if(parser.getName().equals("gyeonggi")){ // 경기
                            gyeonggiCheck = true;
                        }
                        if(parser.getName().equals("gangwon")){ // 강원
                            gangwonCheck = true;
                        }
                        if(parser.getName().equals("chungbuk")){ // 충북
                            chungbukCheck = true;
                        }
                        if(parser.getName().equals("chungnam")){ // 충남
                            chungnamCheck = true;
                        }
                        if(parser.getName().equals("jeonbuk")){ // 전북
                            jeonbukCheck = true;
                        }
                        if(parser.getName().equals("jeonnam")){ // 전남
                            jeonnamCheck = true;
                        }
                        if(parser.getName().equals("gyeongbuk")){ // 경북
                            gyeongbukCheck = true;
                        }
                        if(parser.getName().equals("gyeongnam")){ // 경남
                            gyeongnamCheck = true;
                        }
                        if(parser.getName().equals("jeju")){ // 제주
                            jejuCheck = true;
                        }
                        if(parser.getName().equals("sejong")){ // 세종
                            sejongCheck = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            System.out.println("미세먼지 에러");
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        //            System.out.println("============= 미세먼지 텍스트 만남=============");
                        //            System.out.println("============= 미세먼지  "+parser.getText()+"=============");
                        if(dataTimeCheck) {
                            System.out.println(parser.getText());
                            if(time.equals(parser.getText())){
                                timeCheck = true;
                            }
                            dataTimeCheck = false;
                        }
                        if(mangNameCheck) {
                            System.out.println(parser.getText());
                            mangNameCheck = false;
                        }
                        if(itemCodeCheck) {
                            System.out.println(parser.getText());
                            itemCodeCheck  = false;
                        }
                        if(dataGubunCheck){
                            System.out.println(parser.getText());
                            dataGubunCheck  = false;
                        }
                        if(seoulCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setSeoul(parser.getText());
                            seoulCheck  = false;
                        }
                        if(busanCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setBusan(parser.getText());
                            busanCheck = false;
                        }
                        if(daeguCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setDaegu(parser.getText());
                            daeguCheck = false;
                        }
                        if(incheonCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setIncheon(parser.getText());
                            incheonCheck = false;
                        }
                        if(gwangjuCheck ) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setGwangju(parser.getText());
                            gwangjuCheck  = false;
                        }
                        if(daejeonCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setDaejeon(parser.getText());
                            daejeonCheck  = false;
                        }
                        if(ulsanCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setUlsan(parser.getText());
                            ulsanCheck = false;
                        }
                        if(gyeonggiCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setGyeonggi(parser.getText());
                            gyeonggiCheck = false;
                        }
                        if(gangwonCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setGangwon(parser.getText());
                            gangwonCheck = false;
                        }
                        if(chungbukCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setChungbuk(parser.getText());
                            chungbukCheck = false;
                        }
                        if(chungnamCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setChungnam(parser.getText());
                            chungnamCheck = false;
                        }
                        if(jeonbukCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setJeonbuk(parser.getText());
                            jeonbukCheck = false;
                        }
                        if(jeonnamCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setJeonnam(parser.getText());
                            jeonnamCheck = false;
                        }
                        if(gyeongbukCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setGyeongbuk(parser.getText());
                            gyeongbukCheck = false;
                        }
                        if(gyeongnamCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setGyeongnam(parser.getText());
                            gyeongnamCheck = false;
                        }
                        if(jejuCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setJeju(parser.getText());
                            jejuCheck = false;
                        }
                        if(sejongCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setSejong(parser.getText());
                            sejongCheck = false;
                        }
                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }
            System.out.println("============= DUST2 파싱 끝=============");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("============= DUST2 파싱 에러=============");
        }
    }

    public void setDustXmlData3(String time, String what){

        String queryUrl="http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst?"//요청 URL
                + "ServiceKey=" + key // 키
                + "&pageNo=1" //페이지 번호
                + "&numOfRows=1000" //한 페이지 결과 수
                + "&itemCode="+ what // 	측정항목 구분 (SO2, CO, O3, NO2, PM10, PM25) : 미세먼지
                + "&dataGubun=HOUR"; // 요청 자료 구분 (시간평균 : HOUR, 일평균 : DAILY)
        StringBuffer buffer=new StringBuffer();

        try {
            URL url= new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser parser= factory.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            boolean timeCheck = false;

            System.out.println(queryUrl);
            System.out.println("============= DUST2 파싱 시작=============");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        //             System.out.println("============= 미세먼지 태그 만남=============");
                        if(parser.getName().equals("item")){ // 목록
                            timeCheck = false;
                        }
                        if(parser.getName().equals("dataTime")){ // 측정일
                            dataTimeCheck = true;
                        }
                        if(parser.getName().equals("itemCode")){ // 측정항목
                            itemCodeCheck = true;
                        }
                        if(parser.getName().equals("dataGubun")){ // 자료구분
                            dataGubunCheck = true;
                        }
                        if(parser.getName().equals("seoul")){ // 서울
                            seoulCheck = true;
                        }
                        if(parser.getName().equals("busan")){ // 부산
                            busanCheck = true;
                        }
                        if(parser.getName().equals("daegu")){ // 대구
                            daeguCheck = true;
                        }
                        if(parser.getName().equals("incheon")){ // 인천
                            incheonCheck = true;
                        }
                        if(parser.getName().equals("gwangju")){ // 광주
                            gwangjuCheck = true;
                        }
                        if(parser.getName().equals("daejeon")){ // 대전
                            daejeonCheck = true;
                        }
                        if(parser.getName().equals("ulsan")){ // 울산
                            ulsanCheck = true;
                        }
                        if(parser.getName().equals("gyeonggi")){ // 경기
                            gyeonggiCheck = true;
                        }
                        if(parser.getName().equals("gangwon")){ // 강원
                            gangwonCheck = true;
                        }
                        if(parser.getName().equals("chungbuk")){ // 충북
                            chungbukCheck = true;
                        }
                        if(parser.getName().equals("chungnam")){ // 충남
                            chungnamCheck = true;
                        }
                        if(parser.getName().equals("jeonbuk")){ // 전북
                            jeonbukCheck = true;
                        }
                        if(parser.getName().equals("jeonnam")){ // 전남
                            jeonnamCheck = true;
                        }
                        if(parser.getName().equals("gyeongbuk")){ // 경북
                            gyeongbukCheck = true;
                        }
                        if(parser.getName().equals("gyeongnam")){ // 경남
                            gyeongnamCheck = true;
                        }
                        if(parser.getName().equals("jeju")){ // 제주
                            jejuCheck = true;
                        }
                        if(parser.getName().equals("sejong")){ // 세종
                            sejongCheck = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            System.out.println("미세먼지 에러");
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        //            System.out.println("============= 미세먼지 텍스트 만남=============");
                        //            System.out.println("============= 미세먼지  "+parser.getText()+"=============");
                        if(dataTimeCheck) {
                            System.out.println(parser.getText());
                            if(time.equals(parser.getText())){
                                timeCheck = true;
                            }
                            dataTimeCheck = false;
                        }
                        if(mangNameCheck) {
                            System.out.println(parser.getText());
                            mangNameCheck = false;
                        }
                        if(itemCodeCheck) {
                            System.out.println(parser.getText());
                            itemCodeCheck  = false;
                        }
                        if(dataGubunCheck){
                            System.out.println(parser.getText());
                            dataGubunCheck  = false;
                        }
                        if(seoulCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setSeoul(parser.getText());
                            seoulCheck  = false;
                        }
                        if(busanCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setBusan(parser.getText());
                            busanCheck = false;
                        }
                        if(daeguCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setDaegu(parser.getText());
                            daeguCheck = false;
                        }
                        if(incheonCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setIncheon(parser.getText());
                            incheonCheck = false;
                        }
                        if(gwangjuCheck ) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setGwangju(parser.getText());
                            gwangjuCheck  = false;
                        }
                        if(daejeonCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setDaejeon(parser.getText());
                            daejeonCheck  = false;
                        }
                        if(ulsanCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setUlsan(parser.getText());
                            ulsanCheck = false;
                        }
                        if(gyeonggiCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setGyeonggi(parser.getText());
                            gyeonggiCheck = false;
                        }
                        if(gangwonCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setGangwon(parser.getText());
                            gangwonCheck = false;
                        }
                        if(chungbukCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setChungbuk(parser.getText());
                            chungbukCheck = false;
                        }
                        if(chungnamCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setChungnam(parser.getText());
                            chungnamCheck = false;
                        }
                        if(jeonbukCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setJeonbuk(parser.getText());
                            jeonbukCheck = false;
                        }
                        if(jeonnamCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setJeonnam(parser.getText());
                            jeonnamCheck = false;
                        }
                        if(gyeongbukCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setGyeongbuk(parser.getText());
                            gyeongbukCheck = false;
                        }
                        if(gyeongnamCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setGyeongnam(parser.getText());
                            gyeongnamCheck = false;
                        }
                        if(jejuCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setJeju(parser.getText());
                            jejuCheck = false;
                        }
                        if(sejongCheck) {
                            System.out.println(parser.getText());
                            if(timeCheck)
                                setSejong(parser.getText());
                            sejongCheck = false;
                        }
                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }
            System.out.println("============= DUST2 파싱 끝=============");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("============= DUST2 파싱 에러=============");
        }
    }
}
