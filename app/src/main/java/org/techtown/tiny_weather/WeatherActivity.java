package org.techtown.tiny_weather;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeatherActivity {
    String weather, minWeather, maxWeather, yWeather;

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

    public String getyWeather() {
        return yWeather;
    }

    public void setyWeather(String yWeather) {
        this.yWeather = yWeather;
    }

    public void setWeatherJsonData(double lat, double lon) {
        SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        long timestamp = time.getTime()/1000;

        String queryUrl="https://api.openweathermap.org/data/2.5/onecall?"//요청 URL
                + "appid=" + key
                + "&lat=" + lat
                + "&lon=" + lon
                + "&dt=" + timestamp
                + "&lang=kr&units=metric&exclude=minutely,hourly,alerts";

        try {
            System.out.println(queryUrl);
            URL url = new URL(queryUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            BufferedReader rd;

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            rd.close();
            conn.disconnect();

            System.out.println("=============Weather 파싱 시작1=============");

            JsonParser jParser = new JsonParser();
            JsonObject jObject1 = (JsonObject) jParser.parse(sb.toString());
            JsonObject jObject2 = (JsonObject) jParser.parse(String.valueOf(jObject1.get("current")));
            setWeather(String.valueOf(jObject2.get("temp")));

            JsonArray jObject3 = (JsonArray) jParser.parse(String.valueOf(jObject1.get("daily")));
            JsonObject jObject4 = (JsonObject) jParser.parse(String.valueOf(jObject3.get(1)));
            JsonObject jObject5 = (JsonObject) jParser.parse(String.valueOf(jObject4.get("temp")));
            setMinWeather(String.valueOf(jObject5.get("min")));
            setMaxWeather(String.valueOf(jObject5.get("max")));

            System.out.println("=============Weather 파싱 끝1=============");
        } catch (Exception e) {
            System.out.println("=============Weather 파싱 에러1=============");
            System.out.println(e.getMessage());
        }
    }

    public void setWeatherJsonData2(double lat, double lon) {
        SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date time = cal.getTime();
        long timestamp = time.getTime()/1000;

        String queryUrl="http://api.openweathermap.org/data/2.5/onecall/timemachine?"//요청 URL
                + "appid=" + key
                + "&lat=" + lat
                + "&lon=" + lon
                + "&dt=" + timestamp
                + "&lang=kr&units=metric";

        try {
            URL url = new URL(queryUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            BufferedReader rd;

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            rd.close();
            conn.disconnect();

            System.out.println("=============Weather 파싱 시작2=============");


            JsonParser jParser = new JsonParser();
            JsonObject jObject1 = (JsonObject) jParser.parse(sb.toString());
            JsonObject jObject2 = (JsonObject) jParser.parse(String.valueOf(jObject1.get("current")));
            setyWeather(String.valueOf(jObject2.get("temp")));

            System.out.println("=============Weather 파싱 끝2=============");
        }
        catch (Exception e) {
            System.out.println("=============Weather 파싱 에러2=============");
            System.out.println(e.getMessage());
        }
    }
}
