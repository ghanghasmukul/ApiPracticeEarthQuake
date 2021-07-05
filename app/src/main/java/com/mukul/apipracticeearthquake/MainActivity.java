package com.mukul.apipracticeearthquake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.mukul.apipracticeearthquake.databinding.ActivityMainBinding;
import com.mukul.apipracticeearthquake.models.Feature;
import com.mukul.apipracticeearthquake.models.Last30DaysEarthquakes;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    AdapterClass adapter;
    ActivityMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        setTitle("Significant Earthquakes, Past 30 Days");
        mainMethod();


    }

    private void mainMethod() {
        if (isNetworkConnected()) {
            bind.lottieLayerName.setVisibility(View.GONE);
            getVolleyResponse();
        } else {

            bind.lottieLayerName.setVisibility(View.VISIBLE);

            bind.lottieLayerName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isNetworkConnected()) {
                        bind.lottieLayerName.setVisibility(View.GONE);
                        getVolleyResponse();
                    } else {
                        Toast.makeText(MainActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void getVolleyResponse() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest getEarthquakes = new JsonObjectRequest(Request.Method.GET,
                "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Last30DaysEarthquakes last30DaysEarthquakes = new GsonBuilder().create().fromJson(response.toString(), Last30DaysEarthquakes.class);

                        adapter = new AdapterClass(last30DaysEarthquakes.getFeatures());

                        bind.mainRV.setAdapter(adapter);
                        bind.mainRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));

                        Log.e("responseFromAPI", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(getEarthquakes);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainMethod();
    }
}