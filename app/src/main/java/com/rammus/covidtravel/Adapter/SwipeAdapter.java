package com.rammus.covidtravel.Adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rammus.covidtravel.ui.ListViewFragment;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    int pagenumber;
    public SwipeAdapter(FragmentManager fm,int pagenumber) {
        super(fm);
        this.pagenumber=pagenumber;
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
        return pagenumber;
    }
}
