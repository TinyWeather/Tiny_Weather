package org.techtown.tiny_weather;

import android.content.Context;

import androidx.fragment.app.Fragment;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class DustActivity extends Fragment {

    String key="kd3zWLkxFKVIuT0XejOXR1qWycWNx03d21q75t5AHS2gIRKGQXQhqtwrvDWy3Huf04BaJZQL2vQHDvEkT8coDw%3D%3D";
    String What = ""; // PM10, PM25
    String sidoName = ""; // 전국 17개 시 단위
    
    // 현 위치 대기 상태
    String pm10Value, pm25Value, no2Value, o3Value, coValue, so2Value;

    // 전국 대기 상태 : 미세먼지
    String dataTime;
    int seoulValue, busanValue, daeguValue, incheonValue, gwangjuValue, daejeonValue, ulsanValue, sejongValue;
    int gyeonggiValue, gangwonValue, chungbukValue, chungnamValue, jeonbukValue, jeonnamValue, gyeongbukValue, gyeongnamValue, jejuValue;
    
    // 전국 대기 상태 : 초미세먼지
    String dataTime2;
    int seoulValue2, busanValue2, daeguValue2, incheonValue2, gwangjuValue2, daejeonValue2, ulsanValue2, sejongValue2;
    int gyeonggiValue2, gangwonValue2, chungbukValue2, chungnamValue2, jeonbukValue2, jeonnamValue2, gyeongbukValue2, gyeongnamValue2, jejuValue2;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    // 현 위치 대기 상태
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

    // 전국 대기 상태 : 미세먼지
    /*지역*/
    public int getSeoul() {
        return seoulValue;
    }
    public void setSeoul(int seoulValue) {
        this.seoulValue = seoulValue;
    }
    public int getBusan() {
        return busanValue;
    }
    public void setBusan(int busanValue) {
        this.busanValue = busanValue;
    }
    public int getDaegu() {
        return daeguValue;
    }
    public void setDaegu(int daeguValue) {
        this.daeguValue = daeguValue;
    }
    public int getIncheon() {
        return incheonValue;
    }
    public void setIncheon(int incheonValue) {
        this.incheonValue = incheonValue;
    }
    public int getGwangju() {
        return gwangjuValue;
    }
    public void setGwangju(int gwangjuValue) {
        this.gwangjuValue = gwangjuValue;
    }
    public int getDaejeon() {
        return daejeonValue;
    }
    public void setDaejeon(int daejeonValue) {
        this.daejeonValue = daejeonValue;
    }
    public int getUlsan() {
        return ulsanValue;
    }
    public void setUlsan(int ulsanValue) {
        this.ulsanValue = ulsanValue;
    }
    public int getSejong() {
        return sejongValue;
    }
    public void setSejong(int sejongValue) {
        this.sejongValue = sejongValue;
    }
    public int getGyeonggi() {
        return gyeonggiValue;
    }
    public void setGyeonggi(int gyeonggiValue) {
        this.gyeonggiValue = gyeonggiValue;
    }
    public int getGangwon() {
        return gangwonValue;
    }
    public void setGangwon(int gangwonValue) {
        this.gangwonValue = gangwonValue;
    }
    public int getChungbuk() {
        return chungbukValue;
    }
    public void setChungbuk(int chungbukValue) {
        this.chungbukValue = chungbukValue;
    }
    public int getChungnam() {
        return chungnamValue;
    }
    public void setChungnam(int chungnamValue) {
        this.chungnamValue = chungnamValue;
    }
    public int getJeonbuk() {
        return jeonbukValue;
    }
    public void setJeonbuk(int jeonbukValue) {
        this.jeonbukValue = jeonbukValue;
    }
    public int getJeonnam() {
        return jeonnamValue;
    }
    public void setJeonnam(int jeonnamValue) {
        this.jeonnamValue = jeonnamValue;
    }
    public int getGyeongbuk() {
        return gyeongbukValue;
    }
    public void setGyeongbuk(int gyeongbukValue) {
        this.gyeongbukValue = gyeongbukValue;
    }
    public int getGyeongnam() {
        return gyeongnamValue;
    }
    public void setGyeongnam(int gyeongnamValue) {
        this.gyeongnamValue = gyeongnamValue;
    }
    public int getJeju() {
        return jejuValue;
    }
    public void setJeju(int jejuValue) {
        this.jejuValue = jejuValue;
    }


    // 전국 대기 상태 : 초미세먼지
    /*지역*/
    public int getSeoul2() {
        return seoulValue2;
    }
    public void setSeoul2(int seoulValue2) {
        this.seoulValue2 = seoulValue2;
    }
    public int getBusan2() {
        return busanValue2;
    }
    public void setBusan2(int busanValue2) {
        this.busanValue2 = busanValue2;
    }
    public int getDaegu2() {
        return daeguValue2;
    }
    public void setDaegu2(int daeguValue2) {
        this.daeguValue2 = daeguValue2;
    }
    public int getIncheon2() {
        return incheonValue2;
    }
    public void setIncheon2(int incheonValue2) {
        this.incheonValue2 = incheonValue2;
    }
    public int getGwangju2() {
        return gwangjuValue2;
    }
    public void setGwangju2(int gwangjuValue2) {
        this.gwangjuValue2 = gwangjuValue2;
    }
    public int getDaejeon2() {
        return daejeonValue2;
    }
    public void setDaejeon2(int daejeonValue2) {
        this.daejeonValue2 = daejeonValue2;
    }
    public int getUlsan2() {
        return ulsanValue2;
    }
    public void setUlsan2(int ulsanValue2) {
        this.ulsanValue2 = ulsanValue2;
    }
    public int getSejong2() {
        return sejongValue2;
    }
    public void setSejong2(int sejongValue2) {
        this.sejongValue2 = sejongValue2;
    }
    public int getGyeonggi2() {
        return gyeonggiValue2;
    }
    public void setGyeonggi2(int gyeonggiValue2) {
        this.gyeonggiValue2 = gyeonggiValue2;
    }
    public int getGangwon2() {
        return gangwonValue2;
    }
    public void setGangwon2(int gangwonValue2) {
        this.gangwonValue2 = gangwonValue2;
    }
    public int getChungbuk2() {
        return chungbukValue2;
    }
    public void setChungbuk2(int chungbukValue2) {
        this.chungbukValue2 = chungbukValue2;
    }
    public int getChungnam2() {
        return chungnamValue2;
    }
    public void setChungnam2(int chungnamValue2) {
        this.chungnamValue2 = chungnamValue2;
    }
    public int getJeonbuk2() {
        return jeonbukValue2;
    }
    public void setJeonbuk2(int jeonbukValue2) {
        this.jeonbukValue2 = jeonbukValue2;
    }
    public int getJeonnam2() {
        return jeonnamValue2;
    }
    public void setJeonnam2(int jeonnamValue2) {
        this.jeonnamValue2 = jeonnamValue2;
    }
    public int getGyeongbuk2() {
        return gyeongbukValue2;
    }
    public void setGyeongbuk2(int gyeongbukValue2) {
        this.gyeongbukValue2 = gyeongbukValue2;
    }
    public int getGyeongnam2() {
        return gyeongnamValue2;
    }
    public void setGyeongnam2(int gyeongnamValue2) {
        this.gyeongnamValue2 = gyeongnamValue2;
    }
    public int getJeju2() {
        return jejuValue2;
    }
    public void setJeju2(int jejuValue2) {
        this.jejuValue2 = jejuValue2;
    }

    // 현 위치 대기 상태
    boolean mangNameCheck=false, stationNameCheck=false;
    boolean so2ValueCheck=false,  coValueCheck=false,  o3ValueCheck=false,  no2ValueCheck=false, pm10ValueCheck=false, pm25ValueCheck=false;

    // 전국 대기 상태 : 미세먼지
    boolean dataTimeCheck=false, itemCodeCheck=false, dataGubunCheck=false;
    boolean seoulCheck=false, busanCheck=false, daeguCheck=false, incheonCheck=false, gwangjuCheck=false, daejeonCheck=false, ulsanCheck=false, gyeonggiCheck=false, gangwonCheck=false;
    boolean chungbukCheck=false, chungnamCheck=false, jeonbukCheck=false, jeonnamCheck=false, gyeongbukCheck=false, gyeongnamCheck=false, jejuCheck=false, sejongCheck=false;

    // 전국 대기 상태 : 초미세먼지
    boolean dataTimeCheck2=false, itemCodeCheck2=false, dataGubunCheck2=false;
    boolean seoulCheck2=false, busanCheck2=false, daeguCheck2=false, incheonCheck2=false, gwangjuCheck2=false, daejeonCheck2=false, ulsanCheck2=false, gyeonggiCheck2=false, gangwonCheck2=false;
    boolean chungbukCheck2=false, chungnamCheck2=false, jeonbukCheck2=false, jeonnamCheck2=false, gyeongbukCheck2=false, gyeongnamCheck2=false, jejuCheck2=false, sejongCheck2=false;


    // 현 위치 대기 상태 setDustXmlData(종로, 서울)
    public void setDustXmlData(String location, String sidoName){
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
                  /*      if(mangNameCheck) {
                            //System.out.println(parser.getText());
                            mangNameCheck = false;
                        }*/
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

    // 전국 대기 상태 : 미세먼지 setDustXmlData2(2020-12-15 03:00, PM10)
    public void setDustXmlData2(String time, String what){
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
                            //                  System.out.println(parser.getText());
                            if(timeCheck)
                                setSeoul(Integer.parseInt(parser.getText()));
                            seoulCheck  = false;
                        }
                        if(busanCheck) {
                            //                 System.out.println(parser.getText());
                            if(timeCheck)
                                setBusan(Integer.parseInt(parser.getText()));
                            busanCheck = false;
                        }
                        if(daeguCheck) {
                            //                 System.out.println(parser.getText());
                            if(timeCheck)
                                setDaegu(Integer.parseInt(parser.getText()));
                            daeguCheck = false;
                        }
                        if(incheonCheck) {
                            //               System.out.println(parser.getText());
                            if(timeCheck)
                                setIncheon(Integer.parseInt(parser.getText()));
                            incheonCheck = false;
                        }
                        if(gwangjuCheck ) {
                            //                System.out.println(parser.getText());
                            if(timeCheck)
                                setGwangju(Integer.parseInt(parser.getText()));
                            gwangjuCheck  = false;
                        }
                        if(daejeonCheck) {
                            //                System.out.println(parser.getText());
                            if(timeCheck)
                                setDaejeon(Integer.parseInt(parser.getText()));
                            daejeonCheck  = false;
                        }
                        if(ulsanCheck) {
                            //                System.out.println(parser.getText());
                            if(timeCheck)
                                setUlsan(Integer.parseInt(parser.getText()));
                            ulsanCheck = false;
                        }
                        if(gyeonggiCheck) {
                            //                System.out.println(parser.getText());
                            if(timeCheck)
                                setGyeonggi(Integer.parseInt(parser.getText()));
                            gyeonggiCheck = false;
                        }
                        if(gangwonCheck) {
                            //                System.out.println(parser.getText());
                            if(timeCheck)
                                setGangwon(Integer.parseInt(parser.getText()));
                            gangwonCheck = false;
                        }
                        if(chungbukCheck) {
                            //                System.out.println(parser.getText());
                            if(timeCheck)
                                setChungbuk(Integer.parseInt(parser.getText()));
                            chungbukCheck = false;
                        }
                        if(chungnamCheck) {
                            //               System.out.println(parser.getText());
                            if(timeCheck)
                                setChungnam(Integer.parseInt(parser.getText()));
                            chungnamCheck = false;
                        }
                        if(jeonbukCheck) {
                            //                System.out.println(parser.getText());
                            if(timeCheck)
                                setJeonbuk(Integer.parseInt(parser.getText()));
                            jeonbukCheck = false;
                        }
                        if(jeonnamCheck) {
                            //              System.out.println(parser.getText());
                            if(timeCheck)
                                setJeonnam(Integer.parseInt(parser.getText()));
                            jeonnamCheck = false;
                        }
                        if(gyeongbukCheck) {
                            //              System.out.println(parser.getText());
                            if(timeCheck)
                                setGyeongbuk(Integer.parseInt(parser.getText()));
                            gyeongbukCheck = false;
                        }
                        if(gyeongnamCheck) {
                            //                 System.out.println(parser.getText());
                            if(timeCheck)
                                setGyeongnam(Integer.parseInt(parser.getText()));
                            gyeongnamCheck = false;
                        }
                        if(jejuCheck) {
                            //               System.out.println(parser.getText());
                            if(timeCheck)
                                setJeju(Integer.parseInt(parser.getText()));
                            jejuCheck = false;
                        }
                        if(sejongCheck) {
                            //                System.out.println(parser.getText());
                            if(timeCheck)
                                setSejong(Integer.parseInt(parser.getText()));
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

    // 전국 대기 상태 : 초미세먼지 setDustXmlData3(2020-12-15 03:00, PM25)
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
            boolean timeCheck2 = false;

            System.out.println(queryUrl);
            System.out.println("============= DUST3 파싱 시작=============");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        //             System.out.println("============= 미세먼지 태그 만남=============");
                        if(parser.getName().equals("item")){ // 목록
                            timeCheck2 = false;
                        }
                        if(parser.getName().equals("dataTime")){ // 측정일
                            dataTimeCheck2 = true;
                        }
                        if(parser.getName().equals("itemCode")){ // 측정항목
                            itemCodeCheck2 = true;
                        }
                        if(parser.getName().equals("dataGubun")){ // 자료구분
                            dataGubunCheck2 = true;
                        }
                        if(parser.getName().equals("seoul")){ // 서울
                            seoulCheck2 = true;
                        }
                        if(parser.getName().equals("busan")){ // 부산
                            busanCheck2 = true;
                        }
                        if(parser.getName().equals("daegu")){ // 대구
                            daeguCheck2 = true;
                        }
                        if(parser.getName().equals("incheon")){ // 인천
                            incheonCheck2 = true;
                        }
                        if(parser.getName().equals("gwangju")){ // 광주
                            gwangjuCheck2 = true;
                        }
                        if(parser.getName().equals("daejeon")){ // 대전
                            daejeonCheck2 = true;
                        }
                        if(parser.getName().equals("ulsan")){ // 울산
                            ulsanCheck2 = true;
                        }
                        if(parser.getName().equals("gyeonggi")){ // 경기
                            gyeonggiCheck2 = true;
                        }
                        if(parser.getName().equals("gangwon")){ // 강원
                            gangwonCheck2 = true;
                        }
                        if(parser.getName().equals("chungbuk")){ // 충북
                            chungbukCheck2 = true;
                        }
                        if(parser.getName().equals("chungnam")){ // 충남
                            chungnamCheck2 = true;
                        }
                        if(parser.getName().equals("jeonbuk")){ // 전북
                            jeonbukCheck2 = true;
                        }
                        if(parser.getName().equals("jeonnam")){ // 전남
                            jeonnamCheck2 = true;
                        }
                        if(parser.getName().equals("gyeongbuk")){ // 경북
                            gyeongbukCheck2 = true;
                        }
                        if(parser.getName().equals("gyeongnam")){ // 경남
                            gyeongnamCheck2 = true;
                        }
                        if(parser.getName().equals("jeju")){ // 제주
                            jejuCheck2 = true;
                        }
                        if(parser.getName().equals("sejong")){ // 세종
                            sejongCheck2 = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            System.out.println("미세먼지 에러");
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        //            System.out.println("============= 미세먼지 텍스트 만남=============");
                        //            System.out.println("============= 미세먼지  "+parser.getText()+"=============");
                        if(dataTimeCheck2) {
                            System.out.println(parser.getText());
                            if(time.equals(parser.getText())){
                                timeCheck2 = true;
                            }
                            dataTimeCheck2 = false;
                        }
                 /*       if(mangNameCheck) {
                            System.out.println(parser.getText());
                            mangNameCheck = false;
                        }*/
                        if(itemCodeCheck2) {
                            System.out.println(parser.getText());
                            itemCodeCheck2  = false;
                        }
                        if(dataGubunCheck2){
                            System.out.println(parser.getText());
                            dataGubunCheck2  = false;
                        }
                        if(seoulCheck2) {
                            //                  System.out.println(parser.getText());
                            if(timeCheck2)
                                setSeoul2(Integer.parseInt(parser.getText()));
                            seoulCheck2  = false;
                        }
                        if(busanCheck2) {
                            //                 System.out.println(parser.getText());
                            if(timeCheck2)
                                setBusan2(Integer.parseInt(parser.getText()));
                            busanCheck2 = false;
                        }
                        if(daeguCheck2) {
                            //                 System.out.println(parser.getText());
                            if(timeCheck2)
                                setDaegu2(Integer.parseInt(parser.getText()));
                            daeguCheck2 = false;
                        }
                        if(incheonCheck2) {
                            //               System.out.println(parser.getText());
                            if(timeCheck2)
                                setIncheon2(Integer.parseInt(parser.getText()));
                            incheonCheck2 = false;
                        }
                        if(gwangjuCheck2) {
                            //                System.out.println(parser.getText());
                            if(timeCheck2)
                                setGwangju2(Integer.parseInt(parser.getText()));
                            gwangjuCheck2  = false;
                        }
                        if(daejeonCheck2) {
                            //                System.out.println(parser.getText());
                            if(timeCheck2)
                                setDaejeon2(Integer.parseInt(parser.getText()));
                            daejeonCheck2  = false;
                        }
                        if(ulsanCheck2) {
                            //                System.out.println(parser.getText());
                            if(timeCheck2)
                                setUlsan2(Integer.parseInt(parser.getText()));
                            ulsanCheck2 = false;
                        }
                        if(gyeonggiCheck2) {
                            //                System.out.println(parser.getText());
                            if(timeCheck2)
                                setGyeonggi2(Integer.parseInt(parser.getText()));
                            gyeonggiCheck2 = false;
                        }
                        if(gangwonCheck2) {
                            //                System.out.println(parser.getText());
                            if(timeCheck2)
                                setGangwon2(Integer.parseInt(parser.getText()));
                            gangwonCheck2 = false;
                        }
                        if(chungbukCheck2) {
                            //                System.out.println(parser.getText());
                            if(timeCheck2)
                                setChungbuk2(Integer.parseInt(parser.getText()));
                            chungbukCheck2 = false;
                        }
                        if(chungnamCheck2) {
                            //               System.out.println(parser.getText());
                            if(timeCheck2)
                                setChungnam2(Integer.parseInt(parser.getText()));
                            chungnamCheck2 = false;
                        }
                        if(jeonbukCheck2) {
                            //                System.out.println(parser.getText());
                            if(timeCheck2)
                                setJeonbuk2(Integer.parseInt(parser.getText()));
                            jeonbukCheck2 = false;
                        }
                        if(jeonnamCheck2) {
                            //              System.out.println(parser.getText());
                            if(timeCheck2)
                                setJeonnam2(Integer.parseInt(parser.getText()));
                            jeonnamCheck2 = false;
                        }
                        if(gyeongbukCheck2) {
                            //              System.out.println(parser.getText());
                            if(timeCheck2)
                                setGyeongbuk2(Integer.parseInt(parser.getText()));
                            gyeongbukCheck2 = false;
                        }
                        if(gyeongnamCheck2) {
                            //                 System.out.println(parser.getText());
                            if(timeCheck2)
                                setGyeongnam2(Integer.parseInt(parser.getText()));
                            gyeongnamCheck2 = false;
                        }
                        if(jejuCheck2) {
                            //               System.out.println(parser.getText());
                            if(timeCheck2)
                                setJeju2(Integer.parseInt(parser.getText()));
                            jejuCheck2 = false;
                        }
                        if(sejongCheck2) {
                            //                System.out.println(parser.getText());
                            if(timeCheck2)
                                setSejong2(Integer.parseInt(parser.getText()));
                            sejongCheck2 = false;
                        }
                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }
            System.out.println("============= DUST3 파싱 끝=============");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("============= DUST3 파싱 에러=============");
        }
    }
}
