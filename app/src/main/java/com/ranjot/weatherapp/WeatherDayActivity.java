package com.ranjot.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

        // Will try to displays today's data if DAYS_IN_FUTURE is not set.
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
                        e -> {
                            TextView dayDescription = findViewById(R.id.weather_day_description);
                            dayDescription.setText(R.string.failed_to_load_data);
                            System.out.println("Failed to load data" + e.getMessage());
                        });
        queue.add(request);
    }

    private void setUpView(JSONObject response) {
        WeatherData weatherData;
        try {
            weatherData = WeatherRequestUtil.getWeatherData(response);
        } catch (JSONException e) {
            System.out.println("Failed to load data" + e.getMessage());
            TextView dayDescription = findViewById(R.id.weather_day_description);
            dayDescription.setText(R.string.failed_to_load_data);
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

        TextView weatherMinMax = findViewById(R.id.weather_day_temp_min_max);
        String temp = weatherData.dayWeatherTempMax() + "\n" + weatherData.dayWeatherTempMin();
        weatherMinMax.setText(temp);

        RecyclerView recyclerView = findViewById(R.id.hour_recycle_view);
        HourCardAdapter adapter = new HourCardAdapter(weatherData);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
