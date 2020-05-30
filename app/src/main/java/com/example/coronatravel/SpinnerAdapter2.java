package com.example.coronatravel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerAdapter2 extends BaseAdapter {

    private ArrayList<SpinnerItem> SpinnerItemList = new ArrayList<SpinnerItem>() ;
    LayoutInflater inflater;
    // ListViewAdapter의 생성자

    public SpinnerAdapter2(Context context) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return SpinnerItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_item2, parent, false);
        }


        TextView country = (TextView) convertView.findViewById(R.id.item) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        SpinnerItem spinnerItem = SpinnerItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        country.setText(spinnerItem.getItem());;

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_dropdown, parent, false);
        }
        ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
        layoutParams.height = 120;

        convertView.setLayoutParams(layoutParams);
        TextView item = (TextView) convertView.findViewById(R.id.item) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        SpinnerItem spinnerItem = SpinnerItemList.get(position);

        item.setText(spinnerItem.getItem());;

        return convertView;
    }




    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return SpinnerItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String country) {
        SpinnerItem item = new SpinnerItem();

        item.setItem(country);
        SpinnerItemList.add(item);
    }

    public void clear(){
        SpinnerItemList.clear();
    }
}