package com.example.coronatravel.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.coronatravel.R;

public class Deatail_UI_Test extends AppCompatActivity {

    ViewPager viewPager_mask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatail__u_i__test);
        viewPager_mask=findViewById(R.id.mask_viewpager);


    }
}
