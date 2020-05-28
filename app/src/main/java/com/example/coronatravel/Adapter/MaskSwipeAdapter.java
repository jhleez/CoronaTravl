package com.example.coronatravel.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.coronatravel.Mask;
import com.example.coronatravel.detail.MaskFragment;

import java.util.ArrayList;

public class MaskSwipeAdapter extends FragmentStatePagerAdapter {
    ArrayList<Mask> arrayList;

    public MaskSwipeAdapter(FragmentManager fm, ArrayList<Mask> arrayList) {
        super(fm);
        this.arrayList=arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        for(int i=0;i<((arrayList.size()-1)/3)+1;i++){
            ArrayList<Mask> list=new ArrayList<>();
            for(int j=0;j<3;j++){
                if(arrayList.get(3*i+j)!=null) list.add(arrayList.get(3*i+j));
            }
            fragment= new MaskFragment(list);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ((arrayList.size()-1)/3)+1;
    }
}
