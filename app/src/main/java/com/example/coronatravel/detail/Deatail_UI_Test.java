package com.example.coronatravel.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.coronatravel.Adapter.MaskSwipeAdapter;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;

public class Deatail_UI_Test extends AppCompatActivity {

    ViewPager viewPager_mask;
    MaskSwipeAdapter maskSwipeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatail__u_i__test);
       /* viewPager_mask=findViewById(R.id.mask_viewpager);

        maskSwipeAdapter = new MaskSwipeAdapter(getSupportFragmentManager(),MainActivity.MASK_AraayList);
        if(MainActivity.MASK_AraayList.size()!=0) viewPager_mask.setAdapter(maskSwipeAdapter);
*/



    }
}
