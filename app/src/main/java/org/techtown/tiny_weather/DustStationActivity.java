package org.techtown.tiny_weather;

import android.content.ClipData;
import android.widget.Toast;

import com.google.android.gms.measurement.module.Analytics;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.lang.annotation.Documented;
import java.net.URL;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DustStationActivity {

   /* String arrayListValue;
    public String getArrayListValue() {
        return arrayListValue;
    }
    public void setArrayListValue(String arrayListValue) {
        this.arrayListValue = arrayListValue;
    }*/

    ArrayList<String> stationList = new ArrayList<String>();
    public void setStationList(ArrayList<String> stationList)
    {
        this.stationList = stationList;
    }
    public ArrayList<String> getStationList() {
        return stationList;
    }
    boolean stationNameCheck=false;
    String stationValue;

    String key="kd3zWLkxFKVIuT0XejOXR1qWycWNx03d21q75t5AHS2gIRKGQXQhqtwrvDWy3Huf04BaJZQL2vQHDvEkT8coDw%3D%3D";

    public void setDustStationXmlData(String tmX, String tmY){
        String queryUrl="http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getTMStdrCrdnt?"//요청 URL
                + "ServiceKey=" + key // 키
                + "&tmX=" + tmX // TM_X 좌표
                + "&tmY=" + tmY; // TM_Y 좌표
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
                        if (parser.getName().equals("item")) {
                            totalCheck = true;
                        }
                        if (parser.getName().equals("message")) {
                            System.out.println("DustLocationActivity 에러");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (stationNameCheck) {
                            if (totalCheck) {
                                stationValue = parser.getText();
                                System.out.println("============= stationValue  " + parser.getText() + "=============");
                            }
                            stationNameCheck = false;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            stationList.add(stationValue);
                        }
                        break;
                }
                parserEvent = parser.next();
            }
            System.out.println("============= DustStationActivity 파싱 끝=============");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("============= DustStationActivity 파싱 에러=============");
        }
    }
}