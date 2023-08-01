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
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(
            MainActivity.class);

    @Test
    public void button_clicks() {
        activityRule.getScenario().onActivity(activity -> activity.findViewById(R.id.test_button).performClick());
        assertEquals(1 + 2, 3);
    }
}
