package com.rammus.covidtravel.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.rammus.covidtravel.Adapter.ItemAdapter;
import com.rammus.covidtravel.MainActivity;
import com.rammus.covidtravel.R;
import com.rammus.covidtravel.detail.Detail_view;

public class ListViewFragment extends Fragment {

    public static ListView listView;
    public static LinearLayout linearLayout;
    long mLastClickTime = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        final View view;
        Bundle bundle=getArguments();
        int pageNumber = bundle.getInt("pageNumber");

        view= inflater.inflate(R.layout.activity_list_view,container,false);
        listView = view.findViewById(R.id.list_view);
        linearLayout=view.findViewById(R.id.listlayout);

        //변수에 우리가 선택한 스피너, 위도경도, 정렬이 드가면 됨
        ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
        listView.setAdapter(itemAdapter);





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                Intent intent = new Intent(getActivity(), Detail_view.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        return view;
    }
}
