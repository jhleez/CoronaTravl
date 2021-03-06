package com.rammus.covidtravel.ui.festival;

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

import com.rammus.covidtravel.Adapter.ItemAdapter;
import com.rammus.covidtravel.LocationBasedList_Class;
import com.rammus.covidtravel.MainActivity;
import com.rammus.covidtravel.R;
import com.rammus.covidtravel.Adapter.SwipeAdapter;
import com.rammus.covidtravel.ui.ListViewFragment;

public class FestivalFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private FestivalViewModel festivalViewModel;
    int searchtype;
    int service_typehigh, service_typemiddle, service_typelow;
    int city_big, city_small;
    int term;
    ListView listView;
    String contentTypeId;
    SwipeAdapter swipeAdapter;
    ViewPager viewPager;
    Spinner spinner_term,spinner_searchtype;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        festivalViewModel =
                ViewModelProviders.of(this).get(FestivalViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_festival, container, false);
        viewPager = root.findViewById(R.id.viewpager_festival_page);
        viewPager.addOnPageChangeListener(this);

        spinner_term = root.findViewById(R.id.spinner_festival_term);
        spinner_searchtype=root.findViewById(R.id.spinner_festival_searchtype);

        Button button = root.findViewById(R.id.button_festival_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                term = spinner_term.getSelectedItemPosition();
                searchtype = spinner_searchtype.getSelectedItemPosition();

                if (Integer.parseInt(LocationBasedList_Class.totalcount) != 0) {
                    swipeAdapter = new SwipeAdapter(getChildFragmentManager(), (Integer.parseInt(LocationBasedList_Class.totalcount) / 5) + 1);


                    viewPager.setOffscreenPageLimit(1);
                    viewPager.setAdapter(swipeAdapter);
                    viewPager.setCurrentItem(0);


                    ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                    ListViewFragment.listView.setAdapter(itemAdapter);

                }
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //page 정보 나타내는 변수 설정 해줘야함
        term = spinner_term.getSelectedItemPosition();
        searchtype = spinner_searchtype.getSelectedItemPosition();

        //((MainActivity) getActivity()).festival(contentTypeId, radius, searchtype, String.valueOf(longitude), String.valueOf(latitude), String.valueOf(position + 1));
        ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
        ListViewFragment.listView.setAdapter(itemAdapter);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
