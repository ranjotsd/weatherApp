package com.ranjot.weatherapp;

import static com.ranjot.weatherapp.WeatherRequestUtil.BASE_URL;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class WeatherTestUtilTest {

    MockedStatic<LocalDateTime> localDateTimeMocked;

    @Before
    public void setup() {
        localDateTimeMocked = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS);
    }

    @After
    public void destroy() {
        localDateTimeMocked.closeOnDemand();
    }

    @Test
    public void getRequestUrl_addsDays() {
        LocalDateTime now = LocalDateTime.of(2023, 8, 4, 10, 0);
        localDateTimeMocked.when(LocalDateTime::now).thenReturn(now);

        String url = WeatherRequestUtil.getRequestUrl("London", 2);

        assertEquals(BASE_URL + "&q=London&dt=2023-08-06", url);
    }

    @Test
    public void getRequestUrl_sameDay() {
        LocalDateTime now = LocalDateTime.of(2023, 8, 4, 10, 0);
        localDateTimeMocked.when(LocalDateTime::now).thenReturn(now);

        String url = WeatherRequestUtil.getRequestUrl("London", 0);

        assertEquals(url, BASE_URL + "&q=London&dt=2023-08-04");
    }

    @Test
    public void getRequestUrl_differentLocation() {
        LocalDateTime now = LocalDateTime.of(2023, 8, 4, 10, 0);
        localDateTimeMocked.when(LocalDateTime::now).thenReturn(now);

        String url = WeatherRequestUtil.getRequestUrl("Reading", 3);

        assertEquals(url, BASE_URL + "&q=Reading&dt=2023-08-07");
    }
}
