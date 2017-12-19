package com.nightonke.saver.fragment;

import com.nightonke.saver.ui.CustomTitleSliderView;

import java.util.ArrayList;

/**
 * Created by Weiping on 2016/1/19.
 */
public class CoCoinFragmentManager {

    /**
     * The fragment number
     * */
    public static int MAIN_ACTIVITY_FRAGMENT = 0;

    /**
     * The main EditMoneyFragment
     * */
    public static EditMoneyFragment mainActivityEditMoneyFragment = null;

    /**
     * The main EditRemarkFragment
     * */
    public static EditRemarkFragment mainActivityEditRemarkFragment = null;

    /**
     * Edit record activity number
     * */
    public static int EDIT_RECORD_ACTIVITY_FRAGMENT = 1;

    /**
     * editRecordActivityEditMoneyFragment
     * */
    public static EditMoneyFragment editRecordActivityEditMoneyFragment = null;

    /**
     * editRecordActivityEditRemarkFragment
     * */
    public static EditRemarkFragment editRecordActivityEditRemarkFragment = null;

    /**
     * passwordChangeFragment
     * */
    public static PasswordChangeFragment passwordChangeFragment[] = new PasswordChangeFragment[3];

    /**
     * tagChooseFragments
     * */
    public static ArrayList<TagChooseFragment> tagChooseFragments = new ArrayList<>();

    /**
     * NUMBER_SLIDER
     * */
    public static final int NUMBER_SLIDER = 0;

    /**
     * EXPENSE_SLIDER
     * */
    public static final int EXPENSE_SLIDER = 1;

    /**
     * numberCustomTitleSliderView
     * */
    public static CustomTitleSliderView numberCustomTitleSliderView = null;

    /**
     * expenseCustomTitleSliderView
     * */
    public static CustomTitleSliderView expenseCustomTitleSliderView = null;

    /**
     * reportViewFragment
     * */
    public static ReportViewFragment reportViewFragment = null;

































    private static CoCoinFragmentManager ourInstance = new CoCoinFragmentManager();

    public static CoCoinFragmentManager getInstance() {
        return ourInstance;
    }

    private CoCoinFragmentManager() {
    }
}
