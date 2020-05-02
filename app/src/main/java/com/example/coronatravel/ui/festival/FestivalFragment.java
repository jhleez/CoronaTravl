package com.example.coronatravel.ui.festival;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.coronatravel.Adapter.ItemAdapter;
import com.example.coronatravel.LocationBasedList_Class;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;
import com.example.coronatravel.Adapter.SwipeAdapter;
import com.example.coronatravel.TypeId;
import com.example.coronatravel.ui.ListViewFragment;

public class FestivalFragment extends Fragment {

    private FestivalViewModel festivalViewModel;
    int searchtype;
    int service_typehigh, service_typemiddle, service_typelow;
    int city_big, city_small;
    ListView listView;
    String contentTypeId;
    SwipeAdapter swipeAdapter;
    ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        festivalViewModel =
                ViewModelProviders.of(this).get(FestivalViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_festival, container, false);
        swipeAdapter= new SwipeAdapter(getChildFragmentManager(),1);
        viewPager = root.findViewById(R.id.viewpager_festival_page);
        final Spinner spinner_hightype, spinner_middletype, spinner_lowtype,
                spinner_bigcity, spinner_smallcity, spinner_searchtype;


        /* listView = root.findViewById(R.id.listview_festival_dataview);

        spinner_hightype=root.findViewById(R.id.spinner_festival_hightype);
        spinner_middletype=root.findViewById(R.id.spinner_festival_middletype);
        spinner_lowtype=root.findViewById(R.id.spinner_festival_lowtype);
        spinner_bigcity=root.findViewById(R.id.spinner_festival_bigcity);
        spinner_smallcity=root.findViewById(R.id.spinner_festival_smallcity);
        spinner_searchtype=root.findViewById(R.id.spinner_festival_searchtype);

       */
        Button button = root.findViewById(R.id.button_festival_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  service_typehigh = spinner_hightype.getSelectedItemPosition();//대분류
                service_typemiddle = spinner_middletype.getSelectedItemPosition();//중분류
                service_typelow = spinner_lowtype.getSelectedItemPosition();//소분류

                city_big = spinner_bigcity.getSelectedItemPosition();//지역선택
                city_small = spinner_smallcity.getSelectedItemPosition();//시군구선택

                searchtype = spinner_searchtype.getSelectedItemPosition(); // 정렬 방법*/
                //contentTypeId = TypeId.ContentTypeId(spinner.getSelectedItemPosition());



                Log.d("totalcount", LocationBasedList_Class.totalcount);
                if (Integer.parseInt(LocationBasedList_Class.totalcount) != 0) {
                    swipeAdapter = new SwipeAdapter(getChildFragmentManager(), (Integer.parseInt(LocationBasedList_Class.totalcount) / 5) + 1);

                    viewPager.setOffscreenPageLimit(1);
                    viewPager.setAdapter(swipeAdapter);
                    viewPager.setCurrentItem(0);

                    //변수에 우리가 선택한 스피너, 위도경도, 정렬이 드가면 됨

                    ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                    ListViewFragment.listView.setAdapter(itemAdapter);

                    //Toast.makeText(getActivity(), Integer.parseInt(LocationBasedList_Class.totalcount) + "\n" + "현재위치 \n위도 " + latitude + "\n경도 " + longitude + "\n" + address, Toast.LENGTH_LONG).show();
                }

        /*        ((MainActivity)getActivity()).aroundSearch("12","1000","A","126.981611","37.568477","1");
                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                listView.setAdapter(itemAdapter);*/
            }
        });

        return root;
    }

    public String positionToContenttypeid(int position) {
        if (position == 0) {
            return "";
        } else if (position == 1) {
            return "12";
        } else if (position == 2) {
            return "14";
        } else if (position == 3) {
            return "15";
        } else if (position == 4) {
            return "25";
        } else if (position == 5) {
            return "25";
        } else if (position == 6) {
            return "32";
        } else if (position == 7) {
            return "38";
        } else if (position == 8) {
            return "39";
        }
        return "";
    }

}
