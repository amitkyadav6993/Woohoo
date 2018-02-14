package com.amit.woohoo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amit.woohoo.R;
import com.amit.woohoo.models.Forecast;

import java.util.List;

/**
 * Created by readyassist on 2/14/18.
 */

public class ForcastAdapter extends RecyclerView.Adapter<ForcastAdapter.MovieViewHolder> {

    private List<Forecast> forecastList;
    private int rowLayout;
    private String sunrise,sunset;

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate,tvLowTemp,tvHighTemp,tvSunrise,tvSunset;

        MovieViewHolder(View v) {
            super(v);
            tvDate = v.findViewById(R.id.tvDate);
            tvLowTemp = v.findViewById(R.id.tvLowTemp);
            tvHighTemp = v.findViewById(R.id.tvHighTemp);
            tvSunrise = v.findViewById(R.id.tvSunrise);
            tvSunset = v.findViewById(R.id.tvSunset);
        }
    }

    public ForcastAdapter(List<Forecast> forecastList, String sunrise, String sunset, int rowLayout, Context context) {
        this.forecastList = forecastList;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.rowLayout = rowLayout;
    }

    @Override
    public ForcastAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        Forecast forecast = forecastList.get(position);

        holder.tvDate.setText(forecast.getDate());
        holder.tvLowTemp.setText(forecast.getLow());
        holder.tvHighTemp.setText(forecast.getHigh());
        holder.tvSunrise.setText(sunrise);
        holder.tvSunset.setText(sunset);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }
}