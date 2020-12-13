package org.techtown.tiny_weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeActivity {
    long now;
    Date date;
    SimpleDateFormat sdfNow;
    String formatDate;

    public String getTime() {
        now = System.currentTimeMillis();
        date = new Date(now);
        sdfNow = new SimpleDateFormat("yyyy-MM-dd hh:mm a", new Locale("en", "US"));
        formatDate = sdfNow.format(date);

        return formatDate;
    }

    public String getTime2(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk"+":00");
        Date time = new Date();
        String currentTime = format.format(time);

        return currentTime;
    }
}
