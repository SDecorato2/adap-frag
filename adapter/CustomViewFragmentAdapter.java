package com.nightonke.saver.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nightonke.saver.fragment.CustomViewFragment;

/**
 * Created by 伟平 on 2015/10/20.
 */

// Todo optimize this

public class CustomViewFragmentAdapter extends FragmentStatePagerAdapter {

    /**
     * CustomViewFragmentAdapter
     */
    public CustomViewFragmentAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }


    /**
     * returns a new CustomViewFragment instance
     */
    @Override
    public Fragment getItem(int i) {
        return CustomViewFragment.newInstance();
    }


    /**
     * returns 1
     */
    @Override
    public int getCount() {
        return 1;
    }


    /**
     * returns " "
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
