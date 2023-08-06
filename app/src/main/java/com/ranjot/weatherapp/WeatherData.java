package com.ranjot.weatherapp;

import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue
abstract class WeatherData {

    /** Returns the date in yyyy-MM-dd format. */
    abstract String date();

    /** Returns the Uri for the weather icon (https://github.com/ranjotsd). */
    abstract String weatherIconUri();

    /** Returns the description of the days weather. */
    abstract String dayWeatherDescription();

    /** Returns the minimum temperature of the days weather. */
    abstract String dayWeatherTempMin();

    /** Returns the maximum temperature of the days weather. */
    abstract String dayWeatherTempMax();

    /** Returns a list of hour data for the days weather. */
    abstract List<HourWeatherData> hourData();

    static Builder builder() {
        return new AutoValue_WeatherData.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        /** Sets the date in dd-mm-yyyy format. */
        public abstract Builder setDate(String date);

        /** Set the uri icon for the days weather (github.com/ranjotsd) */
        public abstract Builder setWeatherIconUri(String uri);

        /** Sets the description of the days weather */
        public abstract Builder setDayWeatherDescription(String text);

        /** Sets the minimum temperature of the days weather */
        public abstract Builder setDayWeatherTempMin(String minTemp);

        /** Sets the maximum temperature of the days weather */
        public abstract Builder setDayWeatherTempMax(String maxTemp);

        /** Sets a list of hour data for the days weather */
        public abstract Builder setHourData(List<HourWeatherData> maxTemp);

        public abstract WeatherData build();
    }

    @AutoValue
    public abstract static class HourWeatherData {

        /** Returns the hour of the hours forecast (00:00) */
        public abstract String hour();

        /** Returns the weather icon Uri of the hours forecast (https://github.com/ranjotsd) */
        public abstract String iconUri();

        /** Returns the weather description of the hours forecast */
        public abstract String description();

        /** Returns the weather temperature of the hours forecast */
        public abstract String temperature();

        public static HourWeatherData.Builder builder() {
            return new AutoValue_WeatherData_HourWeatherData.Builder();
        }

        @AutoValue.Builder
        public abstract static class Builder {

            /** Sets the hour of from the date time (2023-08-07-0 00:00) */
            public abstract HourWeatherData.Builder setHour(String hour);

            /** Sets the weather icon Uri from the Url (github.com/ranjotsd) */
            public abstract HourWeatherData.Builder setIconUri(String uri);

            /** Sets the weather description of the hours forecast */
            public abstract HourWeatherData.Builder setDescription(String description);

            /** Sets the weather temperature of the hours forecast */
            public abstract HourWeatherData.Builder setTemperature(String temperature);

            public abstract HourWeatherData build();
        }
    }
}
