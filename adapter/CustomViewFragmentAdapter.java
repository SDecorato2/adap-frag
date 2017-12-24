package com.nightonke.saver.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nightonke.saver.fragment.CustomViewFragment;

/**
 * @author  by 伟平 on 2015/10/20.
 */

public class CustomViewFragmentAdapter extends FragmentStatePagerAdapter {
    //some methods

    public CustomViewFragmentAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return CustomViewFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
