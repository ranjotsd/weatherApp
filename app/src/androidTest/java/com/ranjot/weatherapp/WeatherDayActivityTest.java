package com.ranjot.weatherapp;

import static org.junit.Assert.assertEquals;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class WeatherDayActivityTest {

    @Rule
    public TestName nameRule = new TestName();

    @Rule
    public ActivityScenarioRule<WeatherDayActivity> activityRule =
            new ActivityScenarioRule<>(WeatherDayActivity.class);

    @Test
    public void loadsHourCardAdapter() {
        activityRule.getScenario().onActivity(
            activity -> {
                RecyclerView recyclerView = activity.findViewById(R.id.recycler_view);

                assertEquals(HourCardAdapter.class, recyclerView.getAdapter().getClass());
                assertEquals(24, recyclerView.getAdapter().getItemCount());
            });
    }
}