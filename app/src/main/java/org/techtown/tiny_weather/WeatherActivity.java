package org.techtown.tiny_weather;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeatherActivity {
    int weather, minWeather, maxWeather, yWeather, idWeather;
    int[] timeWeather = new int[48];
    String[] timeDtWeather = new String[48];
    int[] timeIdWeather = new int[48];
    int[] sevenMinWeather = new int[7];
    int[] sevenMaxWeather = new int[7];
    String[] sevenDtWeather = new String[7];
    int[] sevenIdWeather = new int[7];

    String key="3983b899e4b52f928412c4202fb6461a";

    public int getWeather() {
        return weather;
    }

    public void setWeather(int weather) {
        this.weather = weather;
    }

    public int getMinWeather() {
        return minWeather;
    }

    public void setMinWeather(int minWeather) {
        this.minWeather = minWeather;
    }

    public int getMaxWeather() {
        return maxWeather;
    }

    public void setMaxWeather(int maxWeather) {
        this.maxWeather = maxWeather;
    }

    public int getyWeather() {
        return yWeather;
    }

    public void setyWeather(int yWeather) {
        this.yWeather = yWeather;
    }

    public int getIdWeather() {
        return idWeather;
    }

    public void setIdWeather(int idWeather) {
        this.idWeather = idWeather;
    }

    public int getTimeWeather(int number) {
        return timeWeather[number];
    }

    public void setTimeWeather(int number, int timeWeather) {
        this.timeWeather[number] = timeWeather;
    }

    public String getTimeDtWeather(int number) {
        return timeDtWeather[number];
    }

    public void setTimeDtWeather(int number, String timeDtWeather) {
        this.timeDtWeather[number] = timeDtWeather;
    }

    public int getTimeIdWeather(int number) {
        return timeIdWeather[number];
    }

    public void setTimeIdWeather(int number, int timeIdWeather) {
        this.timeIdWeather[number] = timeIdWeather;
    }

    public int getSevenMinWeather(int number) {
        return sevenMinWeather[number];
    }

    public void setSevenMinWeather(int number, int sevenMinWeather) {
        this.sevenMinWeather[number] = sevenMinWeather;
    }

    public int getSevenMaxWeather(int number) {
        return sevenMaxWeather[number];
    }

    public void setSevenMaxWeather(int number, int sevenMaxWeather) {
        this.sevenMaxWeather[number] = sevenMaxWeather;
    }

    public String getSevenDtWeather(int number) {
        return sevenDtWeather[number];
    }

    public void setSevenDtWeather(int number, String sevenDtWeather) {
        this.sevenDtWeather[number] = sevenDtWeather;
    }

    public int getSevenIdWeather(int number) {
        return sevenIdWeather[number];
    }

    public void setSevenIdWeather(int number, int sevenIdWeather) {
        this.sevenIdWeather[number] = sevenIdWeather;
    }

    // 오늘 날씨, 최저/최고, 아이콘
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
            JsonObject jObject = (JsonObject) jParser.parse(sb.toString());

            JsonObject temp_jObject1 = (JsonObject) jParser.parse(String.valueOf(jObject.get("current")));

            String temp = String.valueOf(temp_jObject1.get("temp"));
            int temp_round = Math.toIntExact(Math.round(Double.parseDouble(temp)));
            setWeather(temp_round);

            JsonArray m_jObject1 = (JsonArray) jParser.parse(String.valueOf(jObject.get("daily")));
            JsonObject m_jObject2 = (JsonObject) jParser.parse(String.valueOf(m_jObject1.get(0)));
            JsonObject m_jObject3 = (JsonObject) jParser.parse(String.valueOf(m_jObject2.get("temp")));

            String min = String.valueOf(m_jObject3.get("min"));
            int min_round = Math.toIntExact(Math.round(Double.parseDouble(min)));
            setMinWeather(min_round);

            String max = String.valueOf(m_jObject3.get("max"));
            int max_round = Math.toIntExact(Math.round(Double.parseDouble(max)));
            setMaxWeather(max_round);

            JsonObject id_jObject1 = (JsonObject) jParser.parse(String.valueOf(jObject.get("current")));
            JsonArray id_jObject2 = (JsonArray) jParser.parse(String.valueOf(id_jObject1.get("weather")));
            JsonObject id_jObject3 = (JsonObject) jParser.parse(String.valueOf(id_jObject2.get(0)));

            String id = String.valueOf(id_jObject3.get("id"));
            int id_int = Integer.parseInt(id);
            setIdWeather(id_int);

            System.out.println("=============Weather 파싱 끝1=============");
        } catch (Exception e) {
            System.out.println("=============Weather 파싱 에러1=============");
            System.out.println(e.getMessage());
        }
    }

    // 전날 날씨
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
            String temp = String.valueOf(jObject2.get("temp"));
            int temp_round = Math.toIntExact(Math.round(Double.parseDouble(temp)));
            setyWeather(temp_round);

            System.out.println("=============Weather 파싱 끝2=============");
        }
        catch (Exception e) {
            System.out.println("=============Weather 파싱 에러2=============");
            System.out.println(e.getMessage());
        }
    }

    // 시간별 날씨, 아이콘
    public void setWeatherJsonData3(double lat, double lon) {
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

            System.out.println("=============Weather 파싱 시작3=============");

            JsonParser jParser = new JsonParser();
            JsonObject jObject = (JsonObject) jParser.parse(sb.toString());

            JsonArray jObject1 = (JsonArray) jParser.parse(String.valueOf(jObject.get("hourly")));

            for(int i=0; i<timeWeather.length; i++) {
                JsonObject jObject2 = (JsonObject) jParser.parse(String.valueOf(jObject1.get(i)));
                JsonArray jObject3 = (JsonArray) jParser.parse(String.valueOf(jObject2.get("weather")));
                JsonObject jObject4 = (JsonObject) jParser.parse(String.valueOf(jObject3.get(0)));

                String temp = String.valueOf(jObject2.get("temp"));
                int temp_round = Math.toIntExact(Math.round(Double.parseDouble(temp)));
                setTimeWeather(i, temp_round);

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dt = String.valueOf(jObject2.get("dt"));
                long dt_long = Long.parseLong(dt) * 1000;
                String dt_String = format.format(dt_long);
                setTimeDtWeather(i, dt_String);

                String id = String.valueOf(jObject4.get("id"));
                int id_int = Integer.parseInt(id);
                setTimeIdWeather(i, id_int);
            }

            System.out.println("=============Weather 파싱 끝3=============");
        } catch (Exception e) {
            System.out.println("=============Weather 파싱 에러3=============");
            System.out.println(e.getMessage());
        }
    }

    // 7일간 최저/최고, 아이콘
    public void setWeatherJsonData4(double lat, double lon) {
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

            System.out.println("=============Weather 파싱 시작4=============");

            JsonParser jParser = new JsonParser();
            JsonObject jObject = (JsonObject) jParser.parse(sb.toString());

            JsonArray jObject1 = (JsonArray) jParser.parse(String.valueOf(jObject.get("daily")));

            for(int i=0; i<sevenDtWeather.length; i++) {
                JsonObject jObject2 = (JsonObject) jParser.parse(String.valueOf(jObject1.get(i)));
                JsonObject jObject3 = (JsonObject) jParser.parse(String.valueOf(jObject2.get("temp")));
                JsonArray jObject4 = (JsonArray) jParser.parse(String.valueOf(jObject2.get("weather")));
                JsonObject jObject5 = (JsonObject) jParser.parse(String.valueOf(jObject4.get(0)));

                String min = String.valueOf(jObject3.get("min"));
                int min_round = Math.toIntExact(Math.round(Double.parseDouble(min)));
                setSevenMinWeather(i, min_round);

                String max = String.valueOf(jObject3.get("max"));
                int max_round = Math.toIntExact(Math.round(Double.parseDouble(max)));
                setSevenMaxWeather(i, max_round);

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dt = String.valueOf(jObject2.get("dt"));
                long dt_long = Long.parseLong(dt) * 1000;
                String dt_String = format.format(dt_long);
                setSevenDtWeather(i, dt_String);

                String id = String.valueOf(jObject5.get("id"));
                int id_int = Integer.parseInt(id);
                setSevenIdWeather(i, id_int);
            }

            System.out.println("=============Weather 파싱 끝4=============");
        } catch (Exception e) {
            System.out.println("=============Weather 파싱 에러4=============");
            System.out.println(e.getMessage());
        }
    }
}
