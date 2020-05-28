package com.example.coronatravel.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.coronatravel.detail.MaskFragment;

public class MaskSwipeAdapter extends FragmentStatePagerAdapter {

    public MaskSwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment= new MaskFragment();
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
