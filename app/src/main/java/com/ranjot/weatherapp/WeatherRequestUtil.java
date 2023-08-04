package com.ranjot.weatherapp;

import androidx.annotation.VisibleForTesting;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class WeatherRequestUtil {

    @VisibleForTesting
    final static String BASE_URL =
            "https://api.weatherapi.com/v1/forecast.json?key=36d9dbd8618948b5a61213117233107";

    /**
     * @param location: The location for the weather
     * @param daysInFuture: The day in the future to get the weather forecast, where 0 is today.
     */
    static String getRequestUrl(String location, int daysInFuture) {
        String day = LocalDateTime.now().plusDays(daysInFuture).format(DateTimeFormatter.ISO_LOCAL_DATE);
        return String.format("%s&q=%s&dt=%s", BASE_URL, location, day);
    }
}
