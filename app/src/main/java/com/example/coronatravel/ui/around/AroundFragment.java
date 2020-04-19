package com.example.coronatravel.ui.around;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
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
import com.example.coronatravel.detail.Detail_view;

import java.util.ArrayList;

public class AroundFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private AroundViewModel aroundViewModel;
    String radius;
    int spinner_item_position;
    ListView listView;
    long mLastClickTime = 0;
    String contentTypeId;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aroundViewModel =
                ViewModelProviders.of(this).get(AroundViewModel.class);
        View root = inflater.inflate(R.layout.fragment_around, container, false);

        final Spinner spinner = root.findViewById(R.id.spinner_around_traveltype);
        final EditText editText = root.findViewById(R.id.edittext_around_distance);
        listView = root.findViewById(R.id.listview_around_dataview);

        Button button = root.findViewById(R.id.button_around_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_item_position = spinner.getSelectedItemPosition();
                contentTypeId = positionToContenttypeid(spinner_item_position);
                radius = editText.getText().toString();

                ((MainActivity)getActivity()).aroundSearch("12","1000","A","126.981611","37.568477","1");
                //변수에 우리가 선택한 스피너, 위도경도, 정렬이 드가면 됨
                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                listView.setAdapter(itemAdapter);

            }
        });


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