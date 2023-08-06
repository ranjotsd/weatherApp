package com.ranjot.weatherapp;

import static androidx.test.core.graphics.BitmapStorage.writeToTestStorage;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.screenshot.ViewInteractionCapture.captureToBitmap;
import static org.junit.Assert.assertEquals;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public TestName nameRule = new TestName();

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

    @Test
    public void loadsCards_image() throws IOException {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        // File will be stored in build/outputs/managed_device_android_test_additional_output/debug.
        // Copy them to the screenshot file. Look into automating this.
        writeToTestStorage(
            captureToBitmap(
                onView(isRoot())), getClass().getSimpleName() + "_" + nameRule.getMethodName());
    }
}
