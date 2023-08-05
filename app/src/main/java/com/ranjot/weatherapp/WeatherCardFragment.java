package com.ranjot.weatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherCardFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.weather_card_fragment, container, false);

        RequestQueue queue = Volley.newRequestQueue(inflater.getContext());
        JsonObjectRequest request =
                new JsonObjectRequest(
                        Request.Method.GET,
                        WeatherRequestUtil.getRequestUrl("London", 2),
                        null,
                        response -> setUpCardView(view, response),
                        e -> System.out.println("Failed to load data" + e.getMessage()));
        queue.add(request);
        return view;
    }

    private void setUpCardView(View view, JSONObject response) {
        WeatherData weatherData;
        try {
            weatherData = WeatherRequestUtil.getWeatherData(response);
        } catch (JSONException e) {
            System.out.println("Failed to load data" + e.getMessage());
            return;
        }

        TextView textDate = view.findViewById(R.id.card_date);
        textDate.setText(weatherData.date());

        ImageView weatherIcon = view.findViewById(R.id.card_weather_icon);
        Picasso.get()
                .load(weatherData.weatherIconUri())
                .fit()
                .into(weatherIcon);

        TextView textDescription = view.findViewById(R.id.card_body);
        textDescription.setText(weatherData.dayWeatherDescription());
    }
}
