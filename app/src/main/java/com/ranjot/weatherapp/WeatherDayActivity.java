package com.ranjot.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDayActivity extends Activity {

    static final String DAYS_IN_FUTURE = "DAYS_IN_FUTURE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_day);
        Bundle extras = getIntent().getExtras();

        int daysInFuture = 0;
        if (extras != null && extras.containsKey(DAYS_IN_FUTURE)) {
            daysInFuture = extras.getInt(DAYS_IN_FUTURE);
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request =
                new JsonObjectRequest(
                        Request.Method.GET,
                        WeatherRequestUtil.getRequestUrl("London", daysInFuture),
                        null,
                        this::setUpView,
                        e -> System.out.println("Failed to load data" + e.getMessage()));
        queue.add(request);
    }

    private void setUpView(JSONObject response) {
        WeatherData weatherData;
        try {
            weatherData = WeatherRequestUtil.getWeatherData(response);
        } catch (JSONException e) {
            System.out.println("Failed to load data" + e.getMessage());
            return;
        }

        TextView dayDescription = findViewById(R.id.weather_day_description);
        dayDescription.setText(weatherData.dayWeatherDescription());

        TextView dayLocation = findViewById(R.id.weather_day_location);
        dayLocation.setText("London");

        TextView date = findViewById(R.id.weather_day_date);
        date.setText(weatherData.date());

        ImageView weatherIcon = findViewById(R.id.weather_day_icon);
        Picasso.get()
                .load(weatherData.weatherIconUri())
                .fit()
                .into(weatherIcon);

    }
}
