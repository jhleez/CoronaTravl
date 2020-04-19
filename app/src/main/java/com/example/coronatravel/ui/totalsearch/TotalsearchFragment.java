package com.example.coronatravel.ui.totalsearch;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.coronatravel.ItemAdapter;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;
import com.example.coronatravel.detail.Detail_view;

public class TotalsearchFragment extends Fragment {

    private TotalsearchViewModel totalsearchViewModel;
    int searchtype;
    int service_typehigh, service_typemiddle, service_typelow;
    int city_big, city_small;
    String input;
    ListView listView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        totalsearchViewModel =
                ViewModelProviders.of(this).get(TotalsearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_totalsearch, container, false);

        final Spinner spinner_hightype, spinner_middletype,spinner_lowtype,
                spinner_bigcity, spinner_smallcity,spinner_searchtype;
        final EditText editText_input;

        editText_input = root.findViewById(R.id.edittext_totalsearch_input);

        spinner_hightype = root.findViewById(R.id.spinner_totalsearch_hightype);
        spinner_middletype = root.findViewById(R.id.spinner_totalsearch_middletype);
        spinner_lowtype = root.findViewById(R.id.spinner_totalsearch_lowtype);

        spinner_bigcity = root.findViewById(R.id.spinner_totalsearch_bigcity);
        spinner_smallcity = root.findViewById(R.id.spinner_totalsearch_smallcity);

        spinner_searchtype=root.findViewById(R.id.spinner_totalsearch_searchtype);

        listView = root.findViewById(R.id.listview_totalsearch_dataview);
        Button button = root.findViewById(R.id.button_totalsearch_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service_typehigh = spinner_hightype.getSelectedItemPosition();//대분류
                service_typemiddle = spinner_middletype.getSelectedItemPosition();//중분류
                service_typelow = spinner_lowtype.getSelectedItemPosition();//소분류

                city_big = spinner_bigcity.getSelectedItemPosition();//지역선택
                city_small = spinner_smallcity.getSelectedItemPosition();//시군구선택

                input= editText_input.getText().toString(); // 검색 내용

                searchtype = spinner_searchtype.getSelectedItemPosition(); // 정렬 방법
            }
        });

        return root;
    }


    public String positionToContenttypeid(int position){
        if(position == 0){
            return "";
        }
        else if (position == 1){
            return "12";
        }
        else if (position == 2){
            return "14";
        }
        else if (position == 3){
            return "15";
        }
        else if (position == 4){
            return "25";
        }
        else if (position == 5){
            return "25";
        }
        else if (position == 6){
            return "32";
        }
        else if (position == 7){
            return "38";
        }
        else if (position == 8){
            return "39";
        }
        return "";
    }
}