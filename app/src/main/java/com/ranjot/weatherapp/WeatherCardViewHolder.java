package com.ranjot.weatherapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherCardViewHolder extends RecyclerView.ViewHolder {

    WeatherCardViewHolder(View view) {
        super(view);
    }

    void setUpCardView(View view, JSONObject response) {
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
