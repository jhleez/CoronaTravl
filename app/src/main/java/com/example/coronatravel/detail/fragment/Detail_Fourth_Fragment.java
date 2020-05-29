package com.example.coronatravel.detail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.coronatravel.R;
import com.example.coronatravel.detail.detailImage;


public class Detail_Fourth_Fragment extends Fragment {

    public Detail_Fourth_Fragment() {
        // Required empty public constructor
    }
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_detail_fourth_, container, false);
        listView=view.findViewById(R.id.fourth_listview);

        TextView fourth = view.findViewById(R.id.fourth);
        String a = "";
        for(int i=0;i<detailImage.Images.size();i++){
             a += "\n\n"+ detailImage.Images.get(i);
        }
        fourth.setText(a);

        // Inflate the layout for this fragment
        return view;
    }
}
