package com.example.coronatravel.ui.sleeping;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.coronatravel.ItemAdapter;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;
import com.example.coronatravel.detail.Detail_view;
import com.example.coronatravel.ui.SwipeAdapter;

public class SleepingFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private SleepingViewModel sleepingViewModel;
    int radiocheck=0;
    int city_big, city_small;
    ListView listView;
    int searchtype;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sleepingViewModel =
                ViewModelProviders.of(this).get(SleepingViewModel.class);

        final SwipeAdapter swipeAdapter= new SwipeAdapter(getChildFragmentManager());
        final View root = inflater.inflate(R.layout.fragment_sleeping, container, false);
        final ViewPager viewPager = root.findViewById(R.id.viewpager_sleeping_page);


        final Spinner spinner_bigcity, spinner_smallcity,spinner_searchtype;

        RadioGroup group =root.findViewById(R.id.radiogroup);

        spinner_bigcity = root.findViewById(R.id.spinner_sleeping_bigcity);
        spinner_smallcity = root.findViewById(R.id.spinner_sleeping_smallcity);

        spinner_searchtype=root.findViewById(R.id.spinner_sleeping_searchtype);

        Button button =root.findViewById(R.id.button_sleeping_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city_big = spinner_bigcity.getSelectedItemPosition();//지역선택
                city_small = spinner_smallcity.getSelectedItemPosition();//시군구선택

                searchtype = spinner_searchtype.getSelectedItemPosition(); // 정렬 방법

                viewPager.setOffscreenPageLimit(1);
                viewPager.setAdapter(swipeAdapter);
                viewPager.setCurrentItem(0);
            }
        });


        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.sleeping_radiobt0:
                        radiocheck=0;
                        break;
                    case R.id.sleeping_radiobt1:
                        radiocheck=1;
                        break;
                    case R.id.sleeping_radiobt2:
                        radiocheck=2;
                        break;
                    case R.id.sleeping_radiobt3:
                        radiocheck=3;
                        break;
                }
            }
        });


        return root;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(), i + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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