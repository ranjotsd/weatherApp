package com.ranjot.weatherapp;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.weather_card, WeatherCardFragment.class, null)
                    .commit();
        }

        String url =
                "https://api.weatherapi.com/v1/forecast.json?key=36d9dbd8618948b5a61213117233107&q=London&days=7";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request =
                new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        response -> System.out.println("loaded response " + response),
                        error -> System.out.println("error response " + error.toString()));

        queue.add(request);
    }
}
