package com.ranjot.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HourCardAdapter extends RecyclerView.Adapter<HourCardViewHolder> {

    private final WeatherData weatherData;
    public HourCardAdapter(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    @NonNull
    @Override
    public HourCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hour_card, parent, false);

        return new HourCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourCardViewHolder holder, int position) {
        holder.setUpCardView(holder.itemView, weatherData.hourData(), position);
    }

    @Override
    public int getItemCount() {
        // Using 24 will cause errors on DLS. Hence, use the API hour array size.
        return weatherData.hourData().size();
    }
}
