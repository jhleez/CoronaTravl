package com.example.coronatravel.ui.around;

import android.os.Bundle;
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

import com.example.coronatravel.ItemAdapter;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;
import com.example.coronatravel.data.AroundData;

import java.util.ArrayList;

public class AroundFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private AroundViewModel aroundViewModel;
    String abc;
    int select_spinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aroundViewModel =
                ViewModelProviders.of(this).get(AroundViewModel.class);
        View root = inflater.inflate(R.layout.fragment_around, container, false);

        final Spinner spinner = root.findViewById(R.id.spinner_around_traveltype);
        final EditText editText = root.findViewById(R.id.edittext_around_distance);


        Button button = root.findViewById(R.id.button_around_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abc = editText.getText().toString();
                select_spinner = spinner.getSelectedItemPosition();
                Toast.makeText(getActivity(), select_spinner + "선택 " + abc + "거리", Toast.LENGTH_SHORT).show();

            }
        });

        ListView listView = root.findViewById(R.id.listview_around_dataview);
        ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
        listView.setAdapter(itemAdapter);

        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(), i + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}