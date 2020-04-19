package com.example.coronatravel.ui.sleeping;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.coronatravel.ItemAdapter;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;
import com.example.coronatravel.detail.Detail_view;

public class SleepingFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private SleepingViewModel sleepingViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sleepingViewModel =
                ViewModelProviders.of(this).get(SleepingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sleeping, container, false);


        return root;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(), i + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public String positionToContenttypeid(int position){
        if(position == 0){
            return "";
        }
        else if (position == 1){
            return "12";
        }
        else if (position == 2){
            return "14";
        }
        else if (position == 3){
            return "15";
        }
        else if (position == 4){
            return "25";
        }
        else if (position == 5){
            return "25";
        }
        else if (position == 6){
            return "32";
        }
        else if (position == 7){
            return "38";
        }
        else if (position == 8){
            return "39";
        }
        return "";
    }
}