package com.example.coronatravel.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coronatravel.Mask;
import com.example.coronatravel.R;

import java.util.ArrayList;

public class MaskAdapter extends BaseAdapter {
    ArrayList<Mask> arrayList;

    public MaskAdapter(ArrayList<Mask> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Mask mask= arrayList.get(position);

        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.mask_list_item, parent, false);


        TextView address,name;
        ImageView imageView;

        address= convertView.findViewById(R.id.mask_list_item_address);
        name=convertView.findViewById(R.id.mask_list_item_name);
        imageView=convertView.findViewById(R.id.mask_list_item_imageview);

        address.setText(mask.getAddr());
        name.setText(mask.getName());

        if(mask.getRemain_stat().equals("plenty")) imageView.setImageResource(R.drawable.plenty_24dp);
        else if(mask.getRemain_stat().equals("some")) imageView.setImageResource(R.drawable.middle_24dp);
        else if(mask.getRemain_stat().equals("few")) imageView.setImageResource(R.drawable.little_24dp);
        else if(mask.getRemain_stat().equals("empty")) imageView.setImageResource(R.drawable.none_24dp);

        return convertView;
    }
}
