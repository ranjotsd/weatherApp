package com.ranjot.weatherapp;

import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue
abstract class WeatherData {
    abstract String date();

    abstract String weatherIconUri();

    abstract String dayWeatherDescription();

    abstract String dayWeatherTempMin();

    abstract String dayWeatherTempMax();

    abstract List<HourWeatherData> hourData();

    static Builder builder() {
        return new AutoValue_WeatherData.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setDate(String date);

        public abstract Builder setWeatherIconUri(String uri);

        public abstract Builder setDayWeatherDescription(String text);

        public abstract Builder setDayWeatherTempMin(String minTemp);

        public abstract Builder setDayWeatherTempMax(String maxTemp);

        public abstract Builder setHourData(List<HourWeatherData> maxTemp);

        public abstract WeatherData build();
    }

    @AutoValue
    public abstract static class HourWeatherData {
        public abstract String hour();

        public abstract String iconUri();

        public abstract String description();

        public abstract String temperature();

        public static HourWeatherData.Builder builder() {
            return new AutoValue_WeatherData_HourWeatherData.Builder();
        }

        @AutoValue.Builder
        public abstract static class Builder {

            public abstract HourWeatherData.Builder setHour(String hour);

            public abstract HourWeatherData.Builder setIconUri(String uri);

            public abstract HourWeatherData.Builder setDescription(String description);

            public abstract HourWeatherData.Builder setTemperature(String temperature);

            public abstract HourWeatherData build();
        }
    }
}
