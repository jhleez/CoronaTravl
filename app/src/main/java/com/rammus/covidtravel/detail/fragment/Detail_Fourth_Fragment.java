package com.rammus.covidtravel.detail.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.rammus.covidtravel.Adapter.Fourth_Adapter;
import com.rammus.covidtravel.R;


public class Detail_Fourth_Fragment extends Fragment {

    public Detail_Fourth_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("destroy","oncreatview");

        View view =inflater.inflate(R.layout.fragment_detail_fourth_, container, false);
        ListView listView=view.findViewById(R.id.fourth_listview);


        //TextView fourth = view.findViewById(R.id.fourth);
        /*String a = "";
        for(int i=0;i<detailImage.Images.size();i++){
             a += "\n\n"+ detailImage.Images.get(i);
        }
        fourth.setText(a);*/

        Fourth_Adapter fourth_adapter= new Fourth_Adapter();
        listView.setAdapter(fourth_adapter);

        // your listview inside scrollview
        listView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        Log.i("destroy","destroyview");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i("destroy","destroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i("destroy","detach");
        super.onDetach();
    }
}
