package com.nightonke.saver.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.nightonke.saver.fragment.ReportViewFragment;

/**
 * Created by 伟平 on 2015/10/20.
 */

// Todo optimize this

public class ReportViewFragmentAdapter extends FragmentStatePagerAdapter {

    /**
     * returns the super object
     */
    public ReportViewFragmentAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    /**
     * returns a new instance of ReportViewFragment
     */
    @Override
    public Fragment getItem(int i) {
        return ReportViewFragment.newInstance();
    }

    /**
     * return 1
     */
    @Override
    public int getCount() {
        return 1;
    }

    /**
     * retun the title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return ReportViewFragment.REPORT_TITLE;
    }
}
