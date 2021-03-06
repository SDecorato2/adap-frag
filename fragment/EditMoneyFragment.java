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
import android.widget.ImageView;
import android.widget.TextView;

import com.nightonke.saver.R;
import com.nightonke.saver.activity.CoCoinApplication;
import com.nightonke.saver.model.RecordManager;
import com.nightonke.saver.model.SettingManager;
import com.nightonke.saver.util.CoCoinUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * @Created by 伟平 on 2015/11/13.
 * @Version 2.1
 *
 */


/**
 * This class produce some results
 */

public class EditMoneyFragment extends Fragment {

    private int fragmentPosition;
    private int tagId = -1;

    /**
     * some variables
     */

    public MaterialEditText editView;

    /**
     *  ImageView tagImage
     */
    public ImageView tagImage;
    
    /**
     * TextView tagName
     */
    public TextView tagName;

    private View mView;

    Activity activity;

    /**
     * EditMoneyFragment
     */
    static public EditMoneyFragment newInstance(int position, int type) {
        EditMoneyFragment fragment = new EditMoneyFragment();

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
        mView = inflater.inflate(R.layout.edit_money_fragment, container, false);

        if (getArguments().getInt("type") == CoCoinFragmentManager.MAIN_ACTIVITY_FRAGMENT) {
            CoCoinFragmentManager.mainActivityEditMoneyFragment = this;
        } else if (getArguments().getInt("type") == CoCoinFragmentManager.EDIT_RECORD_ACTIVITY_FRAGMENT) {
            CoCoinFragmentManager.editRecordActivityEditMoneyFragment = this;
        }

        fragmentPosition = getArguments().getInt("position");
        editView = (MaterialEditText)mView.findViewById(R.id.money);
        tagImage = (ImageView)mView.findViewById(R.id.tag_image);
        tagName = (TextView)mView.findViewById(R.id.tag_name);
        tagName.setTypeface(CoCoinUtil.typefaceLatoLight);

        editView.setTypeface(CoCoinUtil.typefaceLatoHairline);
        editView.setText("0");
        editView.requestFocus();
        editView.setHelperText(" ");
        editView.setKeyListener(null);
        editView.setOnClickListener(null);
        editView.setOnTouchListener(null);

        boolean shouldChange
                = SettingManager.getInstance().getIsMonthLimit()
                && SettingManager.getInstance().getIsColorRemind()
                && RecordManager.getCurrentMonthExpense()
                >= SettingManager.getInstance().getMonthWarning();

        setEditColor(shouldChange);

        if (getArguments().getInt("type") == CoCoinFragmentManager.EDIT_RECORD_ACTIVITY_FRAGMENT
                && CoCoinUtil.editRecordPosition != -1) {
            CoCoinFragmentManager.editRecordActivityEditMoneyFragment
                    .setTagImage(CoCoinUtil.GetTagIcon(
                            (int)RecordManager.SELECTED_RECORDS.get(CoCoinUtil.editRecordPosition).getTag()));
            CoCoinFragmentManager.editRecordActivityEditMoneyFragment
                    .setTagName(CoCoinUtil.GetTagName(
                            (int)RecordManager.SELECTED_RECORDS.get(CoCoinUtil.editRecordPosition).getTag()));
            CoCoinFragmentManager.editRecordActivityEditMoneyFragment
                    .setTagId(RecordManager.SELECTED_RECORDS.get(CoCoinUtil.editRecordPosition).getTag());
            CoCoinFragmentManager.editRecordActivityEditMoneyFragment
                    .setNumberText(String.format("%.0f", RecordManager.SELECTED_RECORDS.get(CoCoinUtil.editRecordPosition).getMoney()));
        }

        return mView;
    }

    /**
     * this is a interface called OnTagItemSelectedListener
     */
    public interface OnTagItemSelectedListener {
        //a method
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
     * setTagId
     */
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    /**
     * setTag
     */
    public void setTag(int p) {
        tagId = RecordManager.TAGS.get(p).getId();
        tagName.setText(CoCoinUtil.GetTagName(RecordManager.TAGS.get(p).getId()));
        tagImage.setImageResource(CoCoinUtil.GetTagIcon(RecordManager.TAGS.get(p).getId()));
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
     * editRequestFocus
     */
    public void editRequestFocus() {
        editView.requestFocus();
        InputMethodManager imm = (InputMethodManager)
                CoCoinApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
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
     * setTagName
     */
    public void setTagName(String name) {
        tagName.setText(name);
    }

    /**
     * setTagImage
     */
    public void setTagImage(int resource) {
        tagImage.setImageResource(resource);
    }

    /**
     * getTagPosition
     */
    public void getTagPosition(int[] position) {
        tagImage.getLocationOnScreen(position);
        position[0] += tagImage.getWidth() / 2;
        position[1] += tagImage.getHeight() / 2;
    }

}
