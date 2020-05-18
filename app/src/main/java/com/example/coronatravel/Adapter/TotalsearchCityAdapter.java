package com.example.coronatravel.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.coronatravel.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TotalsearchCityAdapter extends BaseAdapter {
    ArrayList<String> arrayList;

    public TotalsearchCityAdapter(ArrayList<String> arrayList) {
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
        String string =arrayList.get(position);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.totalsearch_local_item, parent, false);
        TextView textView = convertView.findViewById(R.id.total_item_text);
        textView.setText(string);

        return convertView;
    }
}
