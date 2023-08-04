package com.ranjot.weatherapp;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class WeatherData {
    abstract String date();

    static Builder builder() {
        return new AutoValue_WeatherData.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setDate(String date);

        public abstract WeatherData build();
    }
}
