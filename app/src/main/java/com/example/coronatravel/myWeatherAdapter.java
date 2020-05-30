package com.example.coronatravel;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myWeatherAdapter extends RecyclerView.Adapter<myWeatherAdapter.ViewHolder> {

    private ArrayList<weatherListViewItem> itemList;
    private Context context;

    public myWeatherAdapter(Context context, ArrayList<weatherListViewItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // context 와 parent.getContext() 는 같다.
        View view = LayoutInflater.from(context)
                .inflate(R.layout.weather_listview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        weatherListViewItem item = itemList.get(position);

        holder.weatherStringTextview.setText(item.getWeatherString());
        holder.dateTextview.setText(item.getDate());
        holder.iconDrawable.setImageDrawable(item.getIconDrawble());
        holder.temperatureTextview.setText(item.getTemperature());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dateTextview;
        public TextView temperatureTextview;
        public ImageView iconDrawable;
        public TextView weatherStringTextview;

        public ViewHolder(View itemView) {
            super(itemView);

            weatherStringTextview = itemView.findViewById(R.id.weatherStringTextView);
            dateTextview = itemView.findViewById(R.id.dateTextview);
            temperatureTextview = itemView.findViewById(R.id.temperatureTextview);
            iconDrawable = itemView.findViewById(R.id.iconImageview);
        }
    }

}
