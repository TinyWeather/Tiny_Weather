package org.techtown.tiny_weather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeatherActivity {
    String weather, minWeather, maxWeather;

    String key="3983b899e4b52f928412c4202fb6461a";

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getMinWeather() {
        return minWeather;
    }

    public void setMinWeather(String minWeather) {
        this.minWeather = minWeather;
    }

    public String getMaxWeather() {
        return maxWeather;
    }

    public void setMaxWeather(String maxWeather) {
        this.maxWeather = maxWeather;
    }

    public void setWeatherXmlData(double lat, double lon) {
        String queryUrl="api.openweathermap.org/data/2.5/weather?"//요청 URL
                + "appid=" + key
                + "&lat=" + lat
                + "&lon=" + lon
                + "&lang=kr&units=metric&mode=xml";
        StringBuffer buffer=new StringBuffer();

        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser parser= factory.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("=============파싱 시작=============");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("temperature")){
                            setWeather(parser.getAttributeValue(null, "value"));
                            setMinWeather(parser.getAttributeValue(null, "min"));
                            setMaxWeather(parser.getAttributeValue(null, "max"));
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
            System.out.println(e.getMessage());
        }
    }
}
