package com.ranjot.weatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class WeatherCardFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.weather_card_fragment, container, false);

        RequestQueue queue = Volley.newRequestQueue(inflater.getContext());
        JsonObjectRequest request =
                new JsonObjectRequest(
                        Request.Method.GET,
                        WeatherRequestUtil.getRequestUrl("London", 2),
                        null,
                        response -> {
                            TextView textBody = v.findViewById(R.id.card_body);
                            textBody.setText(response.toString());
                        },
                        e -> {
                            TextView textBody = v.findViewById(R.id.card_body);
                            textBody.setText(e.toString());
                        });
        queue.add(request);
        return v;
    }
}
