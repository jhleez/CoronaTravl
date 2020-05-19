package com.example.coronatravel.ui.totalsearch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.coronatravel.Adapter.ItemAdapter;
import com.example.coronatravel.Adapter.TotalsearchCityAdapter;
import com.example.coronatravel.LocationBasedList_Class;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;
import com.example.coronatravel.Adapter.SwipeAdapter;
import com.example.coronatravel.TypeId;
import com.example.coronatravel.ui.ListViewFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class TotalsearchFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private TotalsearchViewModel totalsearchViewModel;
    String searchtype;
    String service_typehigh, service_typemiddle, service_typelow;
    String city_big, city_small;
    String input;
    TextView local_result_text;
    Spinner spinner_hightype, spinner_middletype,
            spinner_bigcity, spinner_smallcity, spinner_searchtype;
    SwipeAdapter swipeAdapter;
    ViewPager viewPager;
    EditText editText_input;
    LinearLayout local_select;
    ImageButton next,pre;
    int a = 0, b = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        totalsearchViewModel =
                ViewModelProviders.of(this).get(TotalsearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_totalsearch, container, false);

        viewPager = root.findViewById(R.id.viewpager_totalsearch_page);
        viewPager.addOnPageChangeListener(this);
        local_select = root.findViewById(R.id.button_totalsearch_local);
        local_select.setOnClickListener(this);
        local_result_text = root.findViewById(R.id.select_local_textview);
        next= root.findViewById(R.id.floatbt_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (input.length() >= 2) {
                        ((MainActivity) getActivity()).totalSearch(input, city_big, city_small, service_typehigh, service_typemiddle, "", searchtype, String.valueOf((viewPager.getCurrentItem() + 1)));
                        if (Integer.parseInt(LocationBasedList_Class.totalcount) != 0) {
                            ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                            ListViewFragment.listView.setAdapter(itemAdapter);
                        }
                    } else if (input.length() == 0) {
                        ((MainActivity) getActivity()).localSearch("", city_big, city_small, service_typehigh, service_typemiddle, "", searchtype, String.valueOf((viewPager.getCurrentItem() + 1)));
                        if (Integer.parseInt(LocationBasedList_Class.totalcount) != 0) {
                            ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                            ListViewFragment.listView.setAdapter(itemAdapter);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                ListViewFragment.listView.setAdapter(itemAdapter);
            }
        });
        pre=root.findViewById(R.id.floatbt_previous);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (input.length() >= 2) {
                        ((MainActivity) getActivity()).totalSearch(input, city_big, city_small, service_typehigh, service_typemiddle, "", searchtype, String.valueOf((viewPager.getCurrentItem())));
                        if (Integer.parseInt(LocationBasedList_Class.totalcount) != 0) {
                            ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                            ListViewFragment.listView.setAdapter(itemAdapter);
                        }
                    } else if (input.length() == 0) {
                        ((MainActivity) getActivity()).localSearch("", city_big, city_small, service_typehigh, service_typemiddle, "", searchtype, String.valueOf((viewPager.getCurrentItem())));
                        if (Integer.parseInt(LocationBasedList_Class.totalcount) != 0) {
                            ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                            ListViewFragment.listView.setAdapter(itemAdapter);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                ListViewFragment.listView.setAdapter(itemAdapter);

            }
        });


        editText_input = root.findViewById(R.id.edittext_totalsearch_input);
        editText_input.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editText_input.setInputType(InputType.TYPE_CLASS_TEXT);
        editText_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        try {
                            (getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                            if (((getActivity()).getCurrentFocus() != null) && (((Activity) getActivity()).getCurrentFocus().getWindowToken() != null)) {
                                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((getActivity()).getCurrentFocus().getWindowToken(), 0);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });


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
        spinner_searchtype = root.findViewById(R.id.spinner_totalsearch_searchtype);

        Button button = root.findViewById(R.id.button_totalsearch_search);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                service_typehigh = TypeId.cat1(spinner_hightype.getSelectedItemPosition());//대분류
                service_typemiddle = TypeId.cat2(spinner_middletype.getSelectedItemPosition(), spinner_hightype.getSelectedItemPosition());//중분류

                city_big = TypeId.areacode(a);//지역선택
                city_small = String.valueOf(b);//시군구선택

                input = editText_input.getText().toString(); // 검색 내용

                searchtype = TypeId.arrange(spinner_searchtype.getSelectedItemPosition()); // 정렬 방법
                if (input.length() >= 2) {
                    ((MainActivity) getActivity()).totalSearch(input, city_big, city_small, service_typehigh, service_typemiddle, "", searchtype, "1");
                    int pagecount= (Integer.parseInt(LocationBasedList_Class.totalcount)%10==0)?
                            (Integer.parseInt(LocationBasedList_Class.totalcount)/10):(Integer.parseInt(LocationBasedList_Class.totalcount)/10)+1;
                    swipeAdapter = new SwipeAdapter(getChildFragmentManager(), pagecount);

                    viewPager.setOffscreenPageLimit(1);
                    viewPager.setAdapter(swipeAdapter);
                    viewPager.setCurrentItem(0);

                    pre.setVisibility(View.GONE);
                    if(1!=pagecount){
                        next.setVisibility(View.VISIBLE);
                    }

                    ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                    ListViewFragment.listView.setAdapter(itemAdapter);

                } else if (input.length() == 0) {
                    ((MainActivity) getActivity()).localSearch("", city_big, city_small, service_typehigh, service_typemiddle, "", searchtype, "1");
                    int pagecount= (Integer.parseInt(LocationBasedList_Class.totalcount)%10==0)?
                            (Integer.parseInt(LocationBasedList_Class.totalcount)/10):(Integer.parseInt(LocationBasedList_Class.totalcount)/10)+1;
                    swipeAdapter = new SwipeAdapter(getChildFragmentManager(), pagecount);

                    viewPager.setOffscreenPageLimit(1);
                    viewPager.setAdapter(swipeAdapter);
                    viewPager.setCurrentItem(0);

                    pre.setVisibility(View.GONE);
                    if(1!=pagecount){
                        next.setVisibility(View.VISIBLE);
                    }

                    ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                    ListViewFragment.listView.setAdapter(itemAdapter);


                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("검색 오류");
                    builder.setMessage("2글자 이상 입력해 주세요")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }


            }
        });
        return root;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onPageSelected(int position) {
        int pagecount= (Integer.parseInt(LocationBasedList_Class.totalcount)%10==0)?
                (Integer.parseInt(LocationBasedList_Class.totalcount)/10):(Integer.parseInt(LocationBasedList_Class.totalcount)/10)+1;
        if(position==0){
            pre.setVisibility(View.GONE);
        }else if(position+1==pagecount){
            next.setVisibility(View.GONE);
        }else{
            pre.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }

        service_typehigh = TypeId.cat1(spinner_hightype.getSelectedItemPosition());//대분류
        service_typemiddle = TypeId.cat2(spinner_middletype.getSelectedItemPosition(), spinner_hightype.getSelectedItemPosition());//중분류


        city_big = TypeId.areacode(a);//지역선택
        city_small = String.valueOf(b);//시군구선택
        input = editText_input.getText().toString(); // 검색 내용
        searchtype = TypeId.arrange(spinner_searchtype.getSelectedItemPosition()); // 정렬 방법
        if (input.length() >= 2) {
            ((MainActivity) getActivity()).totalSearch(input, city_big, city_small, service_typehigh, service_typemiddle, "", searchtype, String.valueOf((position + 1)));
            if (Integer.parseInt(LocationBasedList_Class.totalcount) != 0) {
                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                ListViewFragment.listView.setAdapter(itemAdapter);
            }
        } else if (input.length() == 0) {
            ((MainActivity) getActivity()).localSearch("", city_big, city_small, service_typehigh, service_typemiddle, "", searchtype, String.valueOf((position + 1)));
            if (Integer.parseInt(LocationBasedList_Class.totalcount) != 0) {
                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                ListViewFragment.listView.setAdapter(itemAdapter);
            }
        }
        ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
        ListViewFragment.listView.setAdapter(itemAdapter);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

        Log.i("state",state+"");

    }

    @Override
    public void onClick(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("지역 선택");
        final int[] temp1 = new int[1];
        final int[] temp2 = new int[1];
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.totalsearch_local_setting, null);
        final String[] bigcity_string = {""};
        final String[] smallcity_string = {""};
        final TextView textView = view.findViewById(R.id.textview_result_city);
        final ListView listView1 = view.findViewById(R.id.big_city_listview);
        final ListView listView2 = view.findViewById(R.id.small_city_listview);
        final ArrayList<String> bigarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.bigcity)));
        TotalsearchCityAdapter totalsearchCityAdapter = new TotalsearchCityAdapter(bigarraylist);
        listView1.setAdapter(totalsearchCityAdapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> smallarraylist = null;
                if (position == 0) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.total_middle)));
                } else if (position == 1) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.seoul_middle)));
                } else if (position == 2) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.incheon_middle)));
                } else if (position == 3) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.daejeun_middle)));
                } else if (position == 4) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.daegu_middle)));
                } else if (position == 5) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.gangju_middle)));
                } else if (position == 6) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.busan_middle)));
                } else if (position == 7) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.ulsan_middle)));
                } else if (position == 8) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.sejong_middle)));
                } else if (position == 9) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.geunggi_middle)));
                } else if (position == 10) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.gangwon_middle)));
                } else if (position == 11) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.choongbook_middle)));
                } else if (position == 12) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.choongnam_middle)));
                } else if (position == 13) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.geungbook_middle)));
                } else if (position == 14) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.geungnak_middle)));
                } else if (position == 15) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.junbook_middle)));
                } else if (position == 16) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.junnam_midlle)));
                } else if (position == 17) {
                    smallarraylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.jeaju_middle)));
                }
                bigcity_string[0] = (String) listView1.getAdapter().getItem(position);
                temp1[0] = (int) listView1.getAdapter().getItemId(position);
                temp2[0] = 0;
                textView.setText(bigcity_string[0]);
                TotalsearchCityAdapter totalsearchCityAdapter = new TotalsearchCityAdapter(smallarraylist);
                listView2.setAdapter(totalsearchCityAdapter);
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                smallcity_string[0] = (String) listView2.getAdapter().getItem(position);
                temp2[0] = (int) listView2.getAdapter().getItemId(position);
                textView.setText(bigcity_string[0] + " " + smallcity_string[0]);
            }
        });
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                a=temp1[0]; b=temp2[0];
                local_result_text.setText(bigcity_string[0] + " " + smallcity_string[0]);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}