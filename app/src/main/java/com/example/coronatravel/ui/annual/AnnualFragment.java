package com.example.coronatravel.ui.annual;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.coronatravel.R;

public class AnnualFragment extends Fragment {

    private AnnualViewModel annualViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        annualViewModel =
                ViewModelProviders.of(this).get(AnnualViewModel.class);
        View root = inflater.inflate(R.layout.fragment_annual, container, false);
        final TextView textView = root.findViewById(R.id.text_annual);
        annualViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}