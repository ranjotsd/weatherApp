package com.ranjot.weatherapp;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void loadsWeatherFragment() {
        activityRule.getScenario().onActivity(
            activity -> assertEquals(
                activity.getSupportFragmentManager().findFragmentById(R.id.weather_card).getClass(),
                WeatherCardFragment.class));
    }
}
