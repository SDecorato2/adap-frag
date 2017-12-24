package com.nightonke.saver.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.nightonke.saver.R;
import com.nightonke.saver.activity.CoCoinApplication;
import com.nightonke.saver.model.RecordManager;
import com.nightonke.saver.model.SettingManager;
import com.nightonke.saver.util.CoCoinUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by 伟平 on 2015/10/27.
 */
public class EditRemarkFragment extends Fragment {

    private int fragmentPosition;
    private int tagId = -1;

    /**
     * MaterialEditText editView
     */
    public MaterialEditText editView;

    private View mView;

    Activity activity;

    /**
     * public EditRemarkFragment
     */
    static public EditRemarkFragment newInstance(int position, int type) {
        EditRemarkFragment fragment = new EditRemarkFragment();

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("type", type);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    /**
     * View onCreateView
     */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.edit_remark_fragment, container, false);
        editView = (MaterialEditText)mView.findViewById(R.id.remark);

        if (getArguments().getInt("type") == CoCoinFragmentManager.MAIN_ACTIVITY_FRAGMENT) {
            CoCoinFragmentManager.mainActivityEditRemarkFragment = this;
        } else if (getArguments().getInt("type") == CoCoinFragmentManager.EDIT_RECORD_ACTIVITY_FRAGMENT) {
            CoCoinFragmentManager.editRecordActivityEditRemarkFragment = this;
        }

        boolean shouldChange
                = SettingManager.getInstance().getIsMonthLimit()
                && SettingManager.getInstance().getIsColorRemind()
                && RecordManager.getCurrentMonthExpense()
                >= SettingManager.getInstance().getMonthWarning();

        setEditColor(shouldChange);

        if (getArguments().getInt("type") == CoCoinFragmentManager.EDIT_RECORD_ACTIVITY_FRAGMENT
                && CoCoinUtil.editRecordPosition != -1) {
            CoCoinFragmentManager.editRecordActivityEditRemarkFragment
                    .setRemark(RecordManager.SELECTED_RECORDS.get(CoCoinUtil.editRecordPosition).getRemark());
            CoCoinFragmentManager.editRecordActivityEditRemarkFragment.setLastSelection();
        }

        return mView;
    }

    /**
     * @interface OnTagItemSelectedListener
     */
    public interface OnTagItemSelectedListener {
        //tag
        void onTagItemPicked(int position);
    }

    /**
     * updateTags
     */
    public void updateTags() {

    }

    /**
     * getTagId
     */
    public int getTagId() {
        return tagId;
    }

    /**
     * setTag
     */
    public void setTag(int p) {
        tagId = RecordManager.TAGS.get(p).getId();
    }

    /**
     * getNumberText
     */
    public String getNumberText() {
        return editView.getText().toString();
    }

    /**
     * setNumberText
     */
    public void setNumberText(String string) {
        editView.setText(string);
    }

    /**
     * getHelpText
     */
    public String getHelpText() {
        return editView.getHelperText();
    }

    /**
     * setHelpText
     */
    public void setHelpText(String string) {
        editView.setHelperText(string);
    }

    /**
     * setLastSelection
     */
    public void setLastSelection() {
        editView.setSelection(editView.getText().length());
    }

    /**
     * editRequestFocus
     */
    public void editRequestFocus() {
        editView.requestFocus();
        InputMethodManager keyboard = (InputMethodManager)
                CoCoinApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.showSoftInput(editView, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * setEditColor
     */
    public void setEditColor(boolean shouldChange) {
        if (shouldChange) {
            editView.setTextColor(SettingManager.getInstance().getRemindColor());
            editView.setPrimaryColor(SettingManager.getInstance().getRemindColor());
            editView.setHelperTextColor(SettingManager.getInstance().getRemindColor());
        } else {
            editView.setTextColor(CoCoinUtil.getInstance().MY_BLUE);
            editView.setPrimaryColor(CoCoinUtil.getInstance().MY_BLUE);
            editView.setHelperTextColor(CoCoinUtil.getInstance().MY_BLUE);
        }
    }

    /**
     * getRemark
     */
    public String getRemark() {
        return editView.getText().toString();
    }

    /**
     * setRemark
     */
    public void setRemark(String string) {
        editView.setText(string);
    }

}
