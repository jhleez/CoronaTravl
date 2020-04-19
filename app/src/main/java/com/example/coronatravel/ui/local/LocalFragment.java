package com.example.coronatravel.ui.local;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.coronatravel.R;

public class LocalFragment extends Fragment {

    private LocalViewModel localViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        localViewModel =
                ViewModelProviders.of(this).get(LocalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_local, container, false);

        final Spinner spinner_traveltype,spinner_hightype, spinner_middletype,spinner_lowtype,
                spinner_bigcity, spinner_smallcity;

        spinner_traveltype = root.findViewById(R.id.spinner_local_traveltype);

        spinner_hightype = root.findViewById(R.id.spinner_local_hightype);
        spinner_middletype = root.findViewById(R.id.spinner_local_middletype);
        spinner_lowtype = root.findViewById(R.id.spinner_local_lowtype);

        spinner_bigcity = root.findViewById(R.id.spinner_local_bigcity);
        spinner_smallcity = root.findViewById(R.id.spinner_local_smallcity);




        return root;
    }
}