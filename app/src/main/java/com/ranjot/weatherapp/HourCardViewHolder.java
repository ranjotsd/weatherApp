package com.ranjot.weatherapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HourCardViewHolder extends RecyclerView.ViewHolder {

    HourCardViewHolder(View view) {
        super(view);
    }

    void setUpCardView(View view, List<WeatherData.HourWeatherData> hourData, int position) {
        // The card position will determine the hour that should be displayed
        TextView hour = view.findViewById(R.id.card_hour);
        hour.setText(hourData.get(position).hour());

        TextView description = view.findViewById(R.id.card_hour_description);
        description.setText(hourData.get(position).description());

        ImageView weatherIcon = view.findViewById(R.id.card_hour_icon);
        Picasso.get()
                .load(hourData.get(position).iconUri())
                .fit()
                .into(weatherIcon);

        TextView temperature = view.findViewById(R.id.card_hour_temperature);
        temperature.setText(hourData.get(position).temperature());
    }
}