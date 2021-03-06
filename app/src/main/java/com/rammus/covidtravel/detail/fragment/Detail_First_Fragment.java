package com.rammus.covidtravel.detail.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rammus.covidtravel.detail.detailCommon;
import com.rammus.covidtravel.R;


public class Detail_First_Fragment extends Fragment {
    detailCommon detail_C1;
    TextView first;

    public Detail_First_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_detail_first_, container, false);;

        detail_C1 = Data.detail_C;
        first = (TextView)view.findViewById(R.id.afirst);
        first.setText("");
        first.setText("이름 : " + detail_C1.getTitle()+
        "\n주소 : " + detail_C1.getAddr1() +
        "\n전화번호 : " + detail_C1.getTel() + " (" + detail_C1.getTel_name() + ")" +
                "\n홈페이지 : " + detail_C1.getHomepage() +
        "\n\n" + detail_C1.getOverview());

       first.setText(""+ detail_C1.getOverview());

        return view;


    }
}
