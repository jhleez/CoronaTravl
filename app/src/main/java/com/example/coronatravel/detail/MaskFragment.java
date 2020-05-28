package com.example.coronatravel.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.coronatravel.Adapter.MaskAdapter;
import com.example.coronatravel.Mask;
import com.example.coronatravel.R;

import java.util.ArrayList;

public class MaskFragment extends Fragment {
    ArrayList<Mask> arrayList=new ArrayList<>();
    ListView listView;
    MaskAdapter maskAdapter;
    public MaskFragment(ArrayList<Mask> arrayList) {
        this.arrayList=arrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_mask, container, false);
        listView=view.findViewById(R.id.mask_listview_fragment);
        maskAdapter = new MaskAdapter(arrayList);
        listView.setAdapter(maskAdapter);

        return view;
    }
}
