package org.techtown.tiny_weather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CovidActivity {
    String incDec, isolIngCnt;
    String key="kQNmA0Ae96Q8Qdn8vXhpevTmRN%2Bamxndg9jl%2F780hj6pOaVJuac7pW0%2Bwt8YiFgKW5c7hhAxutoaNOG6GmnAAQ%3D%3D";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM월dd일");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
    Calendar calendar = Calendar.getInstance();
    int time = Integer.parseInt(timeFormat.format(calendar.getTime()));
    String today, today2;

    public String getIncDec() {
        return incDec;
    }

    public void setIncDec(String incDec) {
        this.incDec = incDec;
    }

    public String getIsolIngCnt() {
        return isolIngCnt;
    }

    public void setIsolIngCnt(String isolIngCnt) {
        this.isolIngCnt = isolIngCnt;
    }

    public String getToday() {
        return today2;
    }

    boolean createDtCheck=false, deathCntCheck=false, defCntCheck=false, gubunCheck=false, gubunCnCheck=false;
    boolean gubunEnCheck=false, incDecCheck=false, isolClearCntCheck=false, isolIngCntCheck=false, localOccCntCheck=false;
    boolean overFlowCntCheck=false, qurRateCheck=false, seqCheck=false, stdDayCheck=false, updateDtCheck=false;

    public void setCovidXmlData(String location) {
        if(time < 10) {
            calendar.add(Calendar.DATE, -1);
        }
        today = dateFormat.format(calendar.getTime());
        today2 = dateFormat2.format(calendar.getTime());

        String queryUrl="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?"//요청 URL
                + "ServiceKey=" + key
                + "&pageNo=1"
                + "&numOfRows=1000"
                + "&startCreateDt=" + today
                + "&endCreateDt=" + today;
        StringBuffer buffer=new StringBuffer();

        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser parser= factory.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            boolean locationCheck = false;
            System.out.println("=============파싱 시작=============");
            System.out.println(today);

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        System.out.println("=============태그 만남=============");
                        if(parser.getName().equals("item")){
                            locationCheck = false;
                        }
                        if(parser.getName().equals("createDt")){ // 등록 일시
                            createDtCheck = true;
                        }
                        if(parser.getName().equals("deathCnt")){ // 사망자 수
                            deathCntCheck = true;
                        }
                        if(parser.getName().equals("defCnt")){ // 확진자 수
                            defCntCheck = true;
                        }
                        if(parser.getName().equals("gubun")){ // 지역
                            gubunCheck = true;
                        }
                        if(parser.getName().equals("gubunCn")){ // 지역(중문)
                            gubunCnCheck = true;
                        }
                        if(parser.getName().equals("gubunEn")){ //지역(영문)
                            gubunEnCheck = true;
                        }
                        if(parser.getName().equals("incDec")){ // 전일 대비 증감 수
                            incDecCheck = true;
                        }
                        if(parser.getName().equals("isolClearCnt")){ // 격리 해제 수
                            isolClearCntCheck = true;
                        }
                        if(parser.getName().equals("isolIngCnt")){ // 확진자 - 격리 중 발생 수
                            isolIngCntCheck = true;
                        }
                        if(parser.getName().equals("localOccCnt")){ // 확진자 - 지역발생 수
                            localOccCntCheck = true;
                        }
                        if(parser.getName().equals("overFlowCnt")){ // 확진자 - 해외유입 수
                            overFlowCntCheck = true;
                        }
                        if(parser.getName().equals("qurRate")){ // 10만명당 발생률
                            qurRateCheck = true;
                        }
                        if(parser.getName().equals("seq")){ // 게시글 번호
                            seqCheck = true;
                        }
                        if(parser.getName().equals("stdDay")){ // 기준일
                            stdDayCheck = true;
                        }
                        if(parser.getName().equals("updateDt")){ // 수정일
                            updateDtCheck = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            System.out.println("에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        System.out.println("=============텍스트 만남=============");
                        System.out.println("============="+parser.getText()+"=============");
                        if(createDtCheck) {
                            System.out.println(parser.getText());
                            createDtCheck = false;
                        }
                        if(deathCntCheck) {
                            System.out.println(parser.getText());
                            deathCntCheck = false;
                        }
                        if(defCntCheck) {
                            System.out.println(parser.getText());
                            defCntCheck = false;
                        }
                        if(gubunCheck) {
                            System.out.println(parser.getText());
                            if(location.contains(parser.getText()))
                                locationCheck = true;
                            gubunCheck = false;
                        }
                        if(gubunCnCheck) {
                            System.out.println(parser.getText());
                            gubunCnCheck = false;
                        }
                        if(gubunEnCheck) {
                            System.out.println(parser.getText());
                            gubunEnCheck = false;
                        }
                        if(incDecCheck) {
                            System.out.println(parser.getText());
                            if(locationCheck)
                                setIncDec(parser.getText());
                            incDecCheck = false;
                        }
                        if(isolClearCntCheck) {
                            System.out.println(parser.getText());
                            isolClearCntCheck = false;
                        }
                        if(isolIngCntCheck) {
                            System.out.println(parser.getText());
                            if(locationCheck)
                                setIsolIngCnt(parser.getText());
                            isolIngCntCheck = false;
                        }
                        if(localOccCntCheck) {
                            System.out.println(parser.getText());
                            localOccCntCheck = false;
                        }
                        if(overFlowCntCheck) {
                            System.out.println(parser.getText());
                            overFlowCntCheck = false;
                        }
                        if(qurRateCheck) {
                            System.out.println(parser.getText());
                            qurRateCheck = false;
                        }
                        if(seqCheck) {
                            System.out.println(parser.getText());
                            seqCheck = false;
                        }
                        if(stdDayCheck) {
                            System.out.println(parser.getText());
                            stdDayCheck = false;
                        }
                        if(updateDtCheck) {
                            System.out.println(parser.getText());
                            updateDtCheck = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }

            System.out.println("=============파싱 끝=============");
        } catch (Exception e) {
            System.out.println("=============파싱 에러=============");
        }
    }
}
