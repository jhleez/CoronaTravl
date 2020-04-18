package com.example.coronatravel.ui.around;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.coronatravel.R;
import com.example.coronatravel.data.AroundData;

import java.util.ArrayList;

public class AroundFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private AroundViewModel aroundViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aroundViewModel =
                ViewModelProviders.of(this).get(AroundViewModel.class);
        View root = inflater.inflate(R.layout.fragment_around, container, false);

        Spinner spinner = root.findViewById(R.id.spinner_around_traveltype);
        spinner.setOnItemSelectedListener(this);

        EditText editText =root.findViewById(R.id.edittext_around_distance);

        //데이터 선언
        ListView listView = root.findViewById(R.id.listview_around_dataview);
        ArrayList<AroundData> arrayList = new ArrayList<>();
        /*List*/




        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(), i+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}