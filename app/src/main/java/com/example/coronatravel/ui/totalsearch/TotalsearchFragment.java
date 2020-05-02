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

import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;
import com.example.coronatravel.Adapter.SwipeAdapter;
import com.example.coronatravel.TypeId;

public class TotalsearchFragment extends Fragment  {

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
        spinner_hightype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] model_1 = null;
                if (position == 0) {
                    model_1 = getResources().getStringArray(R.array.middletype);
                } else if (position == 1) {
                    model_1 = getResources().getStringArray(R.array.middle_1);
                } else if (position == 2) {
                    model_1 = getResources().getStringArray(R.array.middle_2);
                } else if (position == 3) {
                    model_1 = getResources().getStringArray(R.array.middle_3);
                } else if (position == 4) {
                    model_1 = getResources().getStringArray(R.array.middle_4);
                } else if (position == 5) {
                    model_1 = getResources().getStringArray(R.array.middle_5);
                } else if (position == 6) {
                    model_1 = getResources().getStringArray(R.array.middle_6);
                } else if (position == 7) {
                    model_1 = getResources().getStringArray(R.array.middle_7);
                }
                ArrayAdapter<String> adapter_1 = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, model_1);
                adapter_1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner_middletype.setAdapter(adapter_1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_bigcity = root.findViewById(R.id.spinner_totalsearch_bigcity);
        spinner_smallcity = root.findViewById(R.id.spinner_totalsearch_smallcity);
        spinner_bigcity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] model_2 = null;
                if (position == 0) {
                    model_2 = getResources().getStringArray(R.array.total_middle);
                } else if (position == 1) {
                    model_2 = getResources().getStringArray(R.array.seoul_middle);
                } else if (position == 2) {
                    model_2 = getResources().getStringArray(R.array.incheon_middle);
                } else if (position == 3) {
                    model_2 = getResources().getStringArray(R.array.daejeun_middle);
                } else if (position == 4) {
                    model_2 = getResources().getStringArray(R.array.daegu_middle);
                } else if (position == 5) {
                    model_2 = getResources().getStringArray(R.array.gangju_middle);
                } else if (position == 6) {
                    model_2 = getResources().getStringArray(R.array.busan_middle);
                } else if (position == 7) {
                    model_2 = getResources().getStringArray(R.array.ulsan_middle);
                } else if (position == 8) {
                    model_2 = getResources().getStringArray(R.array.sejong_middle);
                } else if (position == 9) {
                    model_2 = getResources().getStringArray(R.array.geunggi_middle);
                } else if (position == 10) {
                    model_2 = getResources().getStringArray(R.array.gangwon_middle);
                } else if (position == 12) {
                    model_2 = getResources().getStringArray(R.array.choongbook_middle);
                } else if (position == 13) {
                    model_2 = getResources().getStringArray(R.array.choongnam_middle);
                } else if (position == 14) {
                    model_2 = getResources().getStringArray(R.array.geungbook_middle);
                } else if (position == 15) {
                    model_2 = getResources().getStringArray(R.array.geungnak_middle);
                } else if (position == 16) {
                    model_2 = getResources().getStringArray(R.array.junbook_middle);
                } else if (position == 17) {
                    model_2 = getResources().getStringArray(R.array.junnam_midlle);
                } else if (position == 18) {
                    model_2 = getResources().getStringArray(R.array.jeaju_middle);
                }
                ArrayAdapter<String> adapter_2 = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, model_2);
                adapter_2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner_smallcity.setAdapter(adapter_2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

                if(input.equals("")) {
                    ((MainActivity) getActivity()).localSearch("", String.valueOf(city_big), String.valueOf(city_small),
                            TypeId.cat1(service_typehigh), TypeId.cat2(service_typehigh, service_typelow), "", TypeId.arrange(searchtype), "1");
                }
                else {
                    ((MainActivity) getActivity()).totalSearch(input, String.valueOf(city_big), String.valueOf(city_small),
                            TypeId.cat1(service_typehigh), TypeId.cat2(service_typehigh, service_typelow), "", TypeId.arrange(searchtype), "1");
                }


                viewPager.setOffscreenPageLimit(1);
                viewPager.setAdapter(swipeAdapter);
                viewPager.setCurrentItem(0);
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