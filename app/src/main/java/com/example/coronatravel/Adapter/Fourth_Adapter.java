package com.example.coronatravel.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.coronatravel.R;
import com.example.coronatravel.detail.detailImage;
import com.squareup.picasso.Picasso;

public class Fourth_Adapter extends BaseAdapter {


    @Override
    public int getCount() {
        return detailImage.Images.size();
    }

    @Override
    public Object getItem(int position) {
        return detailImage.Images;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String URI=detailImage.Images.get(position);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_image_layout, parent, false);

        ImageView imageView;
        imageView=convertView.findViewById(R.id.detail_image_view);

        Picasso.get().load(URI).placeholder(R.drawable.sunnyicon).into(imageView);
        return convertView;
    }
}
