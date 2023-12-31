package com.ranjot.weatherapp;

import androidx.annotation.VisibleForTesting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

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

     /** Converts the weatherApi Json response into {@link WeatherData} */
    static WeatherData getWeatherData(JSONObject response) throws JSONException {
        WeatherData.Builder builder = WeatherData.builder();
        JSONObject dayForecast =
                response.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0);

        String dateTime = dayForecast.getString("date");

        return builder
            .setDate(
                DateTimeFormatter
                    .ofPattern("dd-MM-yyyy")
                    .format(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateTime)))
            .setWeatherIconUri(
                    String.format(
                        "https:%s",
                        dayForecast.getJSONObject("day").getJSONObject("condition").getString("icon")))
            .setDayWeatherDescription(
                dayForecast.getJSONObject("day").getJSONObject("condition").getString("text"))
            .setDayWeatherTempMin(dayForecast.getJSONObject("day").getString("mintemp_c") + "°C")
            .setDayWeatherTempMax(dayForecast.getJSONObject("day").getString("maxtemp_c") + "°C")
            .setHourData(getHourData(dayForecast.getJSONArray("hour")))
            .build();
    }

    private static List<WeatherData.HourWeatherData> getHourData(JSONArray hourForecast)
            throws JSONException {
        int length = hourForecast.length();
        List<WeatherData.HourWeatherData> hourWeatherDataList = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            hourWeatherDataList
                .add(
                    WeatherData.HourWeatherData.builder()
                        .setHour(
                            hourForecast
                                .getJSONObject(i)
                                .getString("time")
                                .substring(11,16))
                        .setIconUri(
                            String.format(
                                "https:%s",
                                hourForecast
                                    .getJSONObject(i)
                                    .getJSONObject("condition")
                                    .getString("icon")))
                        .setDescription(
                            hourForecast
                                .getJSONObject(i)
                                .getJSONObject("condition")
                                .getString("text"))
                            .setTemperature(
                                String.format("%s°C", hourForecast
                                    .getJSONObject(i)
                                    .getString("temp_c")))
                        .build());
        }
        return hourWeatherDataList;
    }
}
