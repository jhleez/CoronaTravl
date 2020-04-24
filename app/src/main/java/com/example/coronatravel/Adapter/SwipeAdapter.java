package com.example.coronatravel.Adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.coronatravel.ui.ListViewFragment;

public class SwipeAdapter extends FragmentStatePagerAdapter {



    int pagenumber;
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

    public void setPagenumber(int pagenumber) {
        this.pagenumber = pagenumber;
    }
    @Override
    public int getCount() {
        return 100;
    }
}
