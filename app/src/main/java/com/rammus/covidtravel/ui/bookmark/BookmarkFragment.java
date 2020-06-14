package com.rammus.covidtravel.ui.bookmark;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.rammus.covidtravel.Adapter.ItemAdapter;
import com.rammus.covidtravel.MainActivity;
import com.rammus.covidtravel.R;
import com.rammus.covidtravel.detail.Detail_view;

public class BookmarkFragment extends Fragment {
    ListView listView;
    ItemAdapter itemAdapter;
    long mLastClickTime = 0;
    private BookmarkViewModel bookmarkViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookmarkViewModel =
                ViewModelProviders.of(this).get(BookmarkViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bookmark, container, false);

        listView = root.findViewById(R.id.bookmark_listview);
        ((MainActivity) getActivity()).bookMarkList();
        itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
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

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).bookMarkList();
        itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
        listView.setAdapter(itemAdapter);
    }
}