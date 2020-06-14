package com.rammus.covidtravel.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rammus.covidtravel.LocationBasedList_Class;
import com.rammus.covidtravel.R;
import com.squareup.picasso.Picasso;


import java.util.List;

public class ItemAdapter extends BaseAdapter {


    private List<LocationBasedList_Class> list;

    public ItemAdapter(List<LocationBasedList_Class> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View convertView, final ViewGroup viewGroup) {

        LocationBasedList_Class data = list.get(i);
        convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_list_item, viewGroup, false);

        TextView title = convertView.findViewById(R.id.textview_listitem_title);
        TextView addreess = convertView.findViewById(R.id.textview_listitem_address);
        TextView distance = convertView.findViewById(R.id.textview_listitem_distance);
        ImageView imageView = convertView.findViewById(R.id.imageview_listitem);

        String URI = data.getFirstimage();

        if (URI == "") URI = "http://";
        Picasso.get().load(URI).placeholder(R.drawable.luggageicon).into(imageView);

        title.setText(data.getTitle());
        addreess.setText(data.getAddr1());
        if(!data.getDist().equals("")) distance.setText(data.getDist()+" M");
        else distance.setText("");
        return convertView;
    }

}
