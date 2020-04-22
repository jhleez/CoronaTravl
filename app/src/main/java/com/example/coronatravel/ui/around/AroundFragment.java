package com.example.coronatravel.ui.around;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.coronatravel.ItemAdapter;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;
import com.example.coronatravel.data.AroundData;
import com.example.coronatravel.detail.Detail_view;
import com.example.coronatravel.ui.SwipeAdapter;

import java.util.ArrayList;

public class AroundFragment extends Fragment  {

    private AroundViewModel aroundViewModel;
    String radius;
    int spinner_item_position;
    ListView listView;
    long mLastClickTime = 0;
    String contentTypeId;
    int searchtype;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aroundViewModel =
                ViewModelProviders.of(this).get(AroundViewModel.class);

        final SwipeAdapter swipeAdapter= new SwipeAdapter(getChildFragmentManager());
        final View root = inflater.inflate(R.layout.fragment_around, container, false);
        final ViewPager viewPager = root.findViewById(R.id.viewpager_around_page);

        final Spinner spinner = root.findViewById(R.id.spinner_around_traveltype);
        final EditText editText = root.findViewById(R.id.edittext_around_distance);
        final Spinner spinner_searchtype = root.findViewById(R.id.spinner_around_searchtype);

        Button button = root.findViewById(R.id.button_around_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_item_position = spinner.getSelectedItemPosition();
                contentTypeId = positionToContenttypeid(spinner_item_position);
                radius = editText.getText().toString();
                searchtype= spinner_searchtype.getSelectedItemPosition(); // 검색타입 선택 변수

                viewPager.setOffscreenPageLimit(1);
                viewPager.setAdapter(swipeAdapter);
                viewPager.setCurrentItem(0);

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