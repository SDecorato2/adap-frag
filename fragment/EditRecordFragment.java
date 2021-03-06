package com.nightonke.saver.fragment;

import javax.swing.text.View;
import javax.swing.text.html.ImageView;

import com.nightonke.saver.R;
import com.nightonke.saver.activity.CoCoinApplication;
import com.nightonke.saver.model.RecordManager;
import com.nightonke.saver.model.SettingManager;
import com.nightonke.saver.util.CoCoinUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.sun.tools.javac.util.JCDiagnostic.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import sun.awt.im.InputMethodManager;

/**
 * Created by 伟平 on 2015/10/27.
 */

/**
 * EditRecordFragment
*/
public class EditRecordFragment extends Fragment {

    private int fragmentPosition;
    private int tagId = -1;

    /**
     * MaterialEditText
     */
    public MaterialEditText editView;

    /**
     * MaterialEditText
     */
    public MaterialEditText remarkEditView;

    /**
     * ImageView
     */
    public ImageView tagImage;

    /**
     * TextView
     */
    public TextView tagName;

    private View mView;

    Activity activity;

    /**
     * EditRecordFragment
     */
    static public EditRecordFragment newInstance(int position) {
        EditRecordFragment fragment = new EditRecordFragment();

        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    /**
     * View
     */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.edit_money_fragment, container, false);

        fragmentPosition = getArguments().getInt("position");
        remarkEditView = (MaterialEditText) mView.findViewById(R.id.remark);
        editView = (MaterialEditText) mView.findViewById(R.id.money);
        tagImage = (ImageView) mView.findViewById(R.id.tag_image);
        tagName = (TextView) mView.findViewById(R.id.tag_name);
        tagName.setTypeface(CoCoinUtil.typefaceLatoLight);

        if (fragmentPosition == 0) {
            editView.setTypeface(CoCoinUtil.typefaceLatoHairline);
            editView.setText("" + (int) RecordManager.RECORDS.get(CoCoinUtil.editRecordPosition).getMoney());
            editView.requestFocus();
            editView.setHelperText(CoCoinUtil.FLOATINGLABELS[editView.getText().toString().length()]);

            tagId = RecordManager.RECORDS.get(CoCoinUtil.editRecordPosition).getTag();
            tagName.setText(CoCoinUtil.GetTagName(tagId));
            tagImage.setImageResource(CoCoinUtil.GetTagIcon(tagId));

            remarkEditView.setVisibility(View.GONE);
        } else {
            remarkEditView.setTypeface(CoCoinUtil.GetTypeface());

            remarkEditView.setText(RecordManager.RECORDS.get(CoCoinUtil.editRecordPosition).getRemark());
            int pos = remarkEditView.getText().length();
            remarkEditView.setSelection(pos);

            editView.setVisibility(View.GONE);
        }

        boolean shouldChange
                = SettingManager.getInstance().getIsMonthLimit()
                && SettingManager.getInstance().getIsColorRemind()
                && RecordManager.getCurrentMonthExpense()
                >= SettingManager.getInstance().getMonthWarning();

        setEditColor(shouldChange);

        return mView;
    }

    /**
     * interface OnTagItemSelectedListener
     */
    public interface OnTagItemSelectedListener {
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
     *setTag 
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
        if (fragmentPosition == 0) {
            editView.requestFocus();
            InputMethodManager imm = (InputMethodManager)
                    CoCoinApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
        } else {
            remarkEditView.requestFocus();
            InputMethodManager keyboard = (InputMethodManager)
                    CoCoinApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.showSoftInput(remarkEditView, InputMethodManager.SHOW_IMPLICIT);
        }

    }

    /**
     * setEditColor
     */
    public void setEditColor(boolean shouldChange) {
        if (shouldChange) {
            editView.setTextColor(SettingManager.getInstance().getRemindColor());
            editView.setPrimaryColor(SettingManager.getInstance().getRemindColor());
            editView.setHelperTextColor(SettingManager.getInstance().getRemindColor());
            remarkEditView.setTextColor(SettingManager.getInstance().getRemindColor());
            remarkEditView.setPrimaryColor(SettingManager.getInstance().getRemindColor());
            remarkEditView.setHelperTextColor(SettingManager.getInstance().getRemindColor());
        } else {
            editView.setTextColor(CoCoinUtil.MY_BLUE);
            editView.setPrimaryColor(CoCoinUtil.MY_BLUE);
            editView.setHelperTextColor(CoCoinUtil.MY_BLUE);
            remarkEditView.setTextColor(CoCoinUtil.MY_BLUE);
            remarkEditView.setPrimaryColor(CoCoinUtil.MY_BLUE);
            remarkEditView.setHelperTextColor(CoCoinUtil.MY_BLUE);
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
     * getRemark
     */
    public String getRemark() {
        return remarkEditView.getText().toString();
    }

    /**
     * setRemark
     */
    public void setRemark(String string) {
        remarkEditView.setText(string);
    }

}
