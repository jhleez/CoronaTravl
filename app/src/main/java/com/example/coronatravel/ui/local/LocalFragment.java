package com.example.coronatravel.ui.local;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.coronatravel.R;
import com.example.coronatravel.Adapter.SwipeAdapter;

public class LocalFragment extends Fragment {

    private LocalViewModel localViewModel;
    int searchtype;
    int service_typehigh, service_typemiddle, service_typelow;
    int city_big, city_small;
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        localViewModel =
                ViewModelProviders.of(this).get(LocalViewModel.class);

        final SwipeAdapter swipeAdapter= new SwipeAdapter(getChildFragmentManager());
        final View root = inflater.inflate(R.layout.fragment_local, container, false);

        final Spinner spinner_traveltype,spinner_hightype, spinner_middletype,spinner_lowtype,
                spinner_bigcity, spinner_smallcity,spinner_searchtype;

        final ViewPager viewPager = root.findViewById(R.id.viewpager_local_page);

        spinner_traveltype = root.findViewById(R.id.spinner_local_traveltype);

        spinner_hightype = root.findViewById(R.id.spinner_local_hightype);
        spinner_middletype = root.findViewById(R.id.spinner_local_middletype);
        spinner_lowtype = root.findViewById(R.id.spinner_local_lowtype);

        spinner_bigcity = root.findViewById(R.id.spinner_local_bigcity);
        spinner_smallcity = root.findViewById(R.id.spinner_local_smallcity);

        spinner_searchtype=root.findViewById(R.id.spinner_local_searchtype);

        Button button = root.findViewById(R.id.button_local_search);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_traveltype.getSelectedItemPosition();

                service_typehigh = spinner_hightype.getSelectedItemPosition();//대분류
                service_typemiddle = spinner_middletype.getSelectedItemPosition();//중분류
                service_typelow = spinner_lowtype.getSelectedItemPosition();//소분류

                city_big = spinner_bigcity.getSelectedItemPosition();//지역선택
                city_small = spinner_smallcity.getSelectedItemPosition();//시군구선택

                searchtype = spinner_searchtype.getSelectedItemPosition(); // 정렬 방법

                viewPager.setOffscreenPageLimit(1);
                viewPager.setAdapter(swipeAdapter);
                viewPager.setCurrentItem(0);


            }
        });



        return root;
    }
}