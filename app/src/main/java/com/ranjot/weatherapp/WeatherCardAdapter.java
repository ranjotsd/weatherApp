package com.ranjot.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class WeatherCardAdapter extends RecyclerView.Adapter<WeatherCardViewHolder> {

    @NonNull
    @Override
    public WeatherCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_card, parent, false);

        return new WeatherCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherCardViewHolder holder, int position) {
        RequestQueue queue = Volley.newRequestQueue(holder.itemView.getContext());
        JsonObjectRequest request =
                new JsonObjectRequest(
                        Request.Method.GET,
                        WeatherRequestUtil.getRequestUrl("London", position),
                        null,
                        response -> holder.setUpCardView(holder.itemView, response, position),
                        e -> System.out.println("Failed to load data" + e.getMessage()));
        queue.add(request);
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
