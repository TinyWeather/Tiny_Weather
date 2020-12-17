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

    String station;
    public void setStationValue(String station) {
        this.station = station;
    }
    public String getStationValue() {
        return station;
    }

    String key="kd3zWLkxFKVIuT0XejOXR1qWycWNx03d21q75t5AHS2gIRKGQXQhqtwrvDWy3Huf04BaJZQL2vQHDvEkT8coDw%3D%3D";

    // TM 좌표 기반 가장 가까운 측정소 찾기
    public void setDustStationXmlData(String tmX, String tmY){
        String queryUrl="http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList?"
                + "ServiceKey=" + key // 키
                + "&tmX=" + tmX // TM_X 좌표
                + "&tmY=" + tmY; // TM_Y 좌표
        StringBuffer buffer=new StringBuffer();

        try {
            System.out.println("====== 측정소(DustStationActivity) 파싱 시작 ======");
            URL url = new URL(queryUrl);
            System.out.println(queryUrl);

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(String.valueOf(url));

            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList childs = doc.getElementsByTagName("stationName");
            Node child = childs.item(0);
            Node childvalue = child.getFirstChild();

            String childValue = childvalue.getNodeValue();
            setStationValue(childValue);

            System.out.println("====== 측정소 : "+childValue+" ======");

            System.out.println("====== 측정소(DustStationActivity) 파싱 끝 ======");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("====== 측정소(DustStationActivity) 파싱 에러 ======");
        }
    }
}