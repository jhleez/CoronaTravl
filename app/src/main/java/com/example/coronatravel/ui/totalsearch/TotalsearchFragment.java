package com.example.coronatravel.ui.totalsearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.coronatravel.R;
import com.example.coronatravel.Adapter.SwipeAdapter;

public class TotalsearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private TotalsearchViewModel totalsearchViewModel;
    int searchtype;
    int service_typehigh, service_typemiddle, service_typelow;
    int city_big, city_small;
    String input;
    ListView listView;
    Spinner spinner_hightype, spinner_middletype,
            spinner_bigcity, spinner_smallcity, spinner_searchtype;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        totalsearchViewModel =
                ViewModelProviders.of(this).get(TotalsearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_totalsearch, container, false);


        final EditText editText_input;
        final ViewPager viewPager = root.findViewById(R.id.viewpager_totalsearch_page);
        final SwipeAdapter swipeAdapter = new SwipeAdapter(getChildFragmentManager(), 1);

        editText_input = root.findViewById(R.id.edittext_totalsearch_input);

        spinner_hightype = root.findViewById(R.id.spinner_totalsearch_hightype);
        spinner_middletype = root.findViewById(R.id.spinner_totalsearch_middletype);
        spinner_hightype.setOnItemSelectedListener(this);


        spinner_bigcity = root.findViewById(R.id.spinner_totalsearch_bigcity);
        spinner_smallcity = root.findViewById(R.id.spinner_totalsearch_smallcity);

        spinner_searchtype = root.findViewById(R.id.spinner_totalsearch_searchtype);

        Button button = root.findViewById(R.id.button_totalsearch_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service_typehigh = spinner_hightype.getSelectedItemPosition();//대분류
                service_typemiddle = spinner_middletype.getSelectedItemPosition();//중분류

                city_big = spinner_bigcity.getSelectedItemPosition();//지역선택
                city_small = spinner_smallcity.getSelectedItemPosition();//시군구선택

                input = editText_input.getText().toString(); // 검색 내용

                searchtype = spinner_searchtype.getSelectedItemPosition(); // 정렬 방법

                viewPager.setOffscreenPageLimit(1);
                viewPager.setAdapter(swipeAdapter);
                viewPager.setCurrentItem(0);
            }
        });

        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String[] models = null;
        if (position == 0) {
            models = getResources().getStringArray(R.array.middletype);

        } else if (position == 1) {
            models = getResources().getStringArray(R.array.middle_1);

        } else if (position == 2) {
            models = getResources().getStringArray(R.array.middle_2);

        } else if (position == 3) {
            models = getResources().getStringArray(R.array.middle_3);

        } else if (position == 4) {
            models = getResources().getStringArray(R.array.middle_4);

        } else if (position == 5) {
            models = getResources().getStringArray(R.array.middle_5);

        } else if (position == 6) {
            models = getResources().getStringArray(R.array.middle_6);

        } else if (position == 7) {
            models = getResources().getStringArray(R.array.middle_7);

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(), R.layout.support_simple_spinner_dropdown_item, models);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_middletype.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}