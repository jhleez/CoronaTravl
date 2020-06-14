package com.rammus.covidtravel.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rammus.covidtravel.Mask;
import com.rammus.covidtravel.detail.MaskFragment;

import java.util.ArrayList;

public class MaskSwipeAdapter extends FragmentStatePagerAdapter {
    ArrayList<Mask> arrayList;
    public static int save;
    public MaskSwipeAdapter(FragmentManager fm, ArrayList<Mask> arrayList) {
        super(fm);
        this.arrayList = arrayList;
    }

    public MaskSwipeAdapter(FragmentManager fm, ArrayList<Mask> arrayList, int position) {
        super(fm);
        this.arrayList = arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        save = position;
        ArrayList<Mask> list = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            if (arrayList.size() >= 3 * position + j + 1) list.add(arrayList.get(3 * position + j));
            else break;
        }
        fragment = new MaskFragment(list);

        return fragment;
    }

    @Override
    public int getCount() {
        return ((arrayList.size() - 1) / 3) + 1;
    }
}
