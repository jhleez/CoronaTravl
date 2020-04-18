package com.example.coronatravel.ui.around;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.coronatravel.R;

public class AroundFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private AroundViewModel aroundViewModel;
    private Context content;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aroundViewModel =
                ViewModelProviders.of(this).get(AroundViewModel.class);
        View root = inflater.inflate(R.layout.fragment_around, container, false);

        Spinner spinner = root.findViewById(R.id.spinner_around_traveltype);
        spinner.setOnItemSelectedListener(this);

        EditText editText =root.findViewById(R.id.edittext_around_distance);



        //final TextView textView = root.findViewById(R.id.text_around);
        /*aroundViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });*/
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