package com.example.coronatravel.ui.around;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.coronatravel.Adapter.ItemAdapter;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;
import com.example.coronatravel.Adapter.SwipeAdapter;
import com.example.coronatravel.ui.ListViewFragment;

public class AroundFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private AroundViewModel aroundViewModel;
    String radius;
    int spinner_item_position;
    String contentTypeId;
    int searchtype;

    private SwipeAdapter swipeAdapter;
    private View root;
    private ViewPager viewPager;
    private Spinner spinner;
    private EditText editText;
    private Spinner spinner_searchtype;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aroundViewModel =
                ViewModelProviders.of(this).get(AroundViewModel.class);

        swipeAdapter = new SwipeAdapter(getChildFragmentManager());
        root = inflater.inflate(R.layout.fragment_around, container, false);
        viewPager = root.findViewById(R.id.viewpager_around_page);
        viewPager.addOnPageChangeListener(this);


        spinner = root.findViewById(R.id.spinner_around_traveltype);
        editText = root.findViewById(R.id.edittext_around_distance);
        spinner_searchtype = root.findViewById(R.id.spinner_around_searchtype);

        Button button = root.findViewById(R.id.button_around_search);
        button.setOnClickListener(this);



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
        Log.d("ITPANGPANG","onPageScrolled : "+position);

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("ITPANGPANG","onPageSelected : "+position);

        ((MainActivity)getActivity()).aroundSearch("12","1000","A","126.981611","37.568477",String.valueOf(position+1));
        //변수에 우리가 선택한 스피너, 위도경도, 정렬이 드가면 됨
        ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
        ListViewFragment.listView.setAdapter(itemAdapter);

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("ITPANGPANG","onPageScrollStateChanged : "+state);

    }

    @Override
    public void onClick(View view) {
        spinner_item_position = spinner.getSelectedItemPosition();
        contentTypeId = positionToContenttypeid(spinner_item_position);
        radius = editText.getText().toString();
        searchtype = spinner_searchtype.getSelectedItemPosition(); // 검색타입 선택 변수

        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);
    }
}