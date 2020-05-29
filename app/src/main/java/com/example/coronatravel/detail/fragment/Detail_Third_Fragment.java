package com.example.coronatravel.detail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.coronatravel.R;
import com.example.coronatravel.detail.detailRepeat;


public class Detail_Third_Fragment extends Fragment {

    TextView third;
    public Detail_Third_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_third_, container, false);
        // Inflate the layout for this fragment



        third = view.findViewById(R.id.third);
        third.setText("");
        String repeat="";
        for(int i =0;i<detailRepeat.repeat_array.size();i++){
            repeat = repeat + detailRepeat.repeat_array.get(i).getInfoname() +": "+detailRepeat.repeat_array.get(i).getInfotext() + "\n\n";
        }
        third.setText(repeat);
        Toast.makeText(getContext(), detailRepeat.repeat_array.size()+"", Toast.LENGTH_SHORT).show();
        return view;
    }
}
