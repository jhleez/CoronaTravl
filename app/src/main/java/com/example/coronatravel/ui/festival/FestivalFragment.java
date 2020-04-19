package com.example.coronatravel.ui.festival;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.coronatravel.ItemAdapter;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;

public class FestivalFragment extends Fragment {

    private FestivalViewModel festivalViewModel;
    int searchtype;
    int service_typehigh, service_typemiddle, service_typelow;
    int city_big, city_small;
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        festivalViewModel =
                ViewModelProviders.of(this).get(FestivalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_festival, container, false);

        final Spinner spinner_hightype, spinner_middletype, spinner_lowtype,
                spinner_bigcity, spinner_smallcity, spinner_searchtype;
       /* listView = root.findViewById(R.id.listview_festival_dataview);

        spinner_hightype=root.findViewById(R.id.spinner_festival_hightype);
        spinner_middletype=root.findViewById(R.id.spinner_festival_middletype);
        spinner_lowtype=root.findViewById(R.id.spinner_festival_lowtype);
        spinner_bigcity=root.findViewById(R.id.spinner_festival_bigcity);
        spinner_smallcity=root.findViewById(R.id.spinner_festival_smallcity);
        spinner_searchtype=root.findViewById(R.id.spinner_festival_searchtype);

        Button button = root.findViewById(R.id.button_festival_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service_typehigh = spinner_hightype.getSelectedItemPosition();//대분류
                service_typemiddle = spinner_middletype.getSelectedItemPosition();//중분류
                service_typelow = spinner_lowtype.getSelectedItemPosition();//소분류

                city_big = spinner_bigcity.getSelectedItemPosition();//지역선택
                city_small = spinner_smallcity.getSelectedItemPosition();//시군구선택

                searchtype = spinner_searchtype.getSelectedItemPosition(); // 정렬 방법

                ((MainActivity)getActivity()).aroundSearch("12","1000","A","126.981611","37.568477","1");
                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                listView.setAdapter(itemAdapter);
            }
        });*/

        return root;
    }
}
