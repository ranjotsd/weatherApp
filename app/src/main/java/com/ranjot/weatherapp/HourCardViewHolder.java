package com.ranjot.weatherapp;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HourCardViewHolder extends RecyclerView.ViewHolder {

    HourCardViewHolder(View view) {
        super(view);
    }

    void setUpCardView(View view, List<WeatherData.HourWeatherData> hourData, int position) {
        TextView hour = view.findViewById(R.id.card_hour);
        hour.setText(hourData.get(position).hour());
    }
}