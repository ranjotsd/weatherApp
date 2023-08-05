package com.ranjot.weatherapp;

import static org.junit.Assert.assertEquals;

import androidx.recyclerview.widget.RecyclerView;
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
    public void loadsWeatherCardAdapter() {
        activityRule.getScenario().onActivity(
            activity -> {
                RecyclerView recyclerView = activity.findViewById(R.id.recycler_view);
                assertEquals(
                        WeatherCardAdapter.class, recyclerView.getAdapter().getClass());
                assertEquals(7, recyclerView.getAdapter().getItemCount());
            });
    }
}
