package org.techtown.tiny_weather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CovidActivity {
    String incDec, isolIngCnt;

    String[] arrIncDec2 = new String[20];
    String[] arrStdDay2 = new String[20];

    String[] arrIncDec3 = new String[20];
    String[] arrDefCnt3 = new String[20];
    String[] arrGubun3 = new String[20];

    String key="kQNmA0Ae96Q8Qdn8vXhpevTmRN%2Bamxndg9jl%2F780hj6pOaVJuac7pW0%2Bwt8YiFgKW5c7hhAxutoaNOG6GmnAAQ%3D%3D";

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM월dd일");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
    Calendar calendar = Calendar.getInstance();
    int time = Integer.parseInt(timeFormat.format(calendar.getTime()));
    String today, today2, today_10;

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

    public String getArrIncDec2(int num) {
        return arrIncDec2[num];
    }

    public void setArrIncDec2(int num, String arrIncDec2) {
        this.arrIncDec2[num] = arrIncDec2;
    }

    public String getArrStdDay2(int num) {
        return arrStdDay2[num];
    }

    public void setArrStdDay2(int num, String arrStdDay2) {
        this.arrStdDay2[num] = arrStdDay2;
    }

    public String getArrIncDec3(int num) {
        return arrIncDec3[num];
    }

    public void setArrIncDec3(int num, String arrIncDec3) {
        this.arrIncDec3[num] = arrIncDec3;
    }

    public String getArrDefCnt3(int num) {
        return arrDefCnt3[num];
    }

    public void setArrDefCnt3(int num, String arrDefCnt3) {
        this.arrDefCnt3[num] = arrDefCnt3;
    }

    public String getArrGubun3(int num) {
        return arrGubun3[num];
    }

    public void setArrGubun3(int num, String arrGubun3) {
        this.arrGubun3[num] = arrGubun3;
    }

    boolean createDtCheck=false, deathCntCheck=false, defCntCheck=false, gubunCheck=false, gubunCnCheck=false;
    boolean gubunEnCheck=false, incDecCheck=false, isolClearCntCheck=false, isolIngCntCheck=false, localOccCntCheck=false;
    boolean overFlowCntCheck=false, qurRateCheck=false, seqCheck=false, stdDayCheck=false, updateDtCheck=false;

    public void setCovidXmlData(String location) {
        if(time < 10) {
            calendar = Calendar.getInstance();
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
            System.out.println("=============Covid 파싱 시작1=============");
            System.out.println(today);

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
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
                        if(createDtCheck) {
                            createDtCheck = false;
                        }
                        if(deathCntCheck) {
                            deathCntCheck = false;
                        }
                        if(defCntCheck) {
                            defCntCheck = false;
                        }
                        if(gubunCheck) {
                            if(location.contains(parser.getText()))
                                locationCheck = true;
                            gubunCheck = false;
                        }
                        if(gubunCnCheck) {
                            gubunCnCheck = false;
                        }
                        if(gubunEnCheck) {
                            gubunEnCheck = false;
                        }
                        if(incDecCheck) {
                            if(locationCheck)
                                setIncDec(parser.getText());
                            incDecCheck = false;
                        }
                        if(isolClearCntCheck) {
                            isolClearCntCheck = false;
                        }
                        if(isolIngCntCheck) {
                            if(locationCheck)
                                setIsolIngCnt(parser.getText());
                            isolIngCntCheck = false;
                        }
                        if(localOccCntCheck) {
                            localOccCntCheck = false;
                        }
                        if(overFlowCntCheck) {
                            overFlowCntCheck = false;
                        }
                        if(qurRateCheck) {
                            qurRateCheck = false;
                        }
                        if(seqCheck) {
                            seqCheck = false;
                        }
                        if(stdDayCheck) {
                            stdDayCheck = false;
                        }
                        if(updateDtCheck) {
                            updateDtCheck = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }

            System.out.println("=============Covid 파싱 끝1=============");
        } catch (Exception e) {
            System.out.println("=============Covid 파싱 에러1=============");
        }
    }

    public void setCovidXmlData2(String location) {
        calendar = Calendar.getInstance();
        if(time < 10)
            calendar.add(Calendar.DATE, -1);
        today = dateFormat.format(calendar.getTime());
        today2 = dateFormat2.format(calendar.getTime());

        calendar.add(Calendar.DATE, -10);
        today_10 = dateFormat.format(calendar.getTime());

        String queryUrl="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?"//요청 URL
                + "ServiceKey=" + key
                + "&pageNo=1"
                + "&numOfRows=1000"
                + "&startCreateDt=" + today_10
                + "&endCreateDt=" + today;
        StringBuffer buffer=new StringBuffer();

        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser parser= factory.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            boolean locationCheck = false;
            System.out.println("=============Covid 파싱 시작2=============");

            int arrNum = 0;

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
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
                        if(createDtCheck) {
                            createDtCheck = false;
                        }
                        if(deathCntCheck) {
                            deathCntCheck = false;
                        }
                        if(defCntCheck) {
                            defCntCheck = false;
                        }
                        if(gubunCheck) {
                            if(location.contains(parser.getText()))
                                locationCheck = true;
                            gubunCheck = false;
                        }
                        if(gubunCnCheck) {
                            gubunCnCheck = false;
                        }
                        if(gubunEnCheck) {
                            gubunEnCheck = false;
                        }
                        if(incDecCheck) {
                            if(locationCheck)
                                setArrIncDec2(arrNum, parser.getText());
                            incDecCheck = false;
                        }
                        if(isolClearCntCheck) {
                            isolClearCntCheck = false;
                        }
                        if(isolIngCntCheck) {
                            isolIngCntCheck = false;
                        }
                        if(localOccCntCheck) {
                            localOccCntCheck = false;
                        }
                        if(overFlowCntCheck) {
                            overFlowCntCheck = false;
                        }
                        if(qurRateCheck) {
                            qurRateCheck = false;
                        }
                        if(seqCheck) {
                            seqCheck = false;
                        }
                        if(stdDayCheck) {
                            if(locationCheck) {
                                setArrStdDay2(arrNum, parser.getText());
                                arrNum++;
                            }
                            stdDayCheck = false;
                        }
                        if(updateDtCheck) {
                            updateDtCheck = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }

            System.out.println("=============Covid 파싱 끝2=============");
        } catch (Exception e) {
            System.out.println("=============Covid 파싱 에러2=============");
            System.out.println(e.getMessage());
        }
    }

    public void setCovidXmlData3() {
        calendar = Calendar.getInstance();
        if(time < 10)
            calendar.add(Calendar.DATE, -1);
        today = dateFormat.format(calendar.getTime());

        String queryUrl="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?"//요청 URL
                + "ServiceKey=" + key
                + "&pageNo=1"
                + "&numOfRows=1000"
                + "&startCreateDt=" + today
                + "&endCreateDt=" + today;

        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser parser= factory.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("=============Covid 파싱 시작3=============");

            int arrNum2 = 0;

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("item")){
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
                        if(createDtCheck) {
                            createDtCheck = false;
                        }
                        if(deathCntCheck) {
                            deathCntCheck = false;
                        }
                        if(defCntCheck) {
                            setArrDefCnt3(arrNum2, parser.getText());
                            defCntCheck = false;
                        }
                        if(gubunCheck) {
                            setArrGubun3(arrNum2, parser.getText());
                            gubunCheck = false;
                        }
                        if(gubunCnCheck) {
                            gubunCnCheck = false;
                        }
                        if(gubunEnCheck) {
                            gubunEnCheck = false;
                        }
                        if(incDecCheck) {
                            setArrIncDec3(arrNum2, parser.getText());
                            arrNum2++;
                            incDecCheck = false;
                        }
                        if(isolClearCntCheck) {
                            isolClearCntCheck = false;
                        }
                        if(isolIngCntCheck) {
                            isolIngCntCheck = false;
                        }
                        if(localOccCntCheck) {
                            localOccCntCheck = false;
                        }
                        if(overFlowCntCheck) {
                            overFlowCntCheck = false;
                        }
                        if(qurRateCheck) {
                            qurRateCheck = false;
                        }
                        if(seqCheck) {
                            seqCheck = false;
                        }
                        if(stdDayCheck) {
                            stdDayCheck = false;
                        }
                        if(updateDtCheck) {
                            updateDtCheck = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }

            System.out.println("=============Covid 파싱 끝3=============");
        } catch (Exception e) {
            System.out.println("=============Covid 파싱 에러3=============");
            System.out.println(e.getMessage());
        }
    }
}
