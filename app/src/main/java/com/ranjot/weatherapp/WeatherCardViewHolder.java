package com.ranjot.weatherapp;

import static com.ranjot.weatherapp.WeatherDayActivity.DAYS_IN_FUTURE;

import android.content.Intent;
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

    void setUpCardView(View view, JSONObject response, int daysInFuture) {
        WeatherData weatherData;
        try {
            weatherData = WeatherRequestUtil.getWeatherData(response);
        } catch (JSONException e) {
            System.out.println("Failed to load data" + e.getMessage());
            return;
        }

        view.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), WeatherDayActivity.class);
            intent.putExtra(DAYS_IN_FUTURE, daysInFuture);
            view.getContext().startActivity(intent);
        });

        TextView textDate = view.findViewById(R.id.card_date);
        textDate.setText(weatherData.date());

        ImageView weatherIcon = view.findViewById(R.id.card_weather_icon);
        Picasso.get()
                .load(weatherData.weatherIconUri())
                .fit()
                .into(weatherIcon);

        TextView textDescription = view.findViewById(R.id.card_body);
        textDescription.setText(weatherData.dayWeatherDescription());

        TextView weatherMinMax = view.findViewById(R.id.card_temp_min_max);
        String temp = weatherData.dayWeatherTempMax() + "\n" + weatherData.dayWeatherTempMin();
        weatherMinMax.setText(temp);
    }
}
