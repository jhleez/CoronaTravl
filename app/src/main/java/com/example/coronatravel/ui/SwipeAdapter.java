package com.example.coronatravel.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SwipeAdapter extends FragmentStatePagerAdapter {
    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment pageFragment = new ListViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber",position);
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    @Override
    public int getCount() {
        return 100;
    }
}
