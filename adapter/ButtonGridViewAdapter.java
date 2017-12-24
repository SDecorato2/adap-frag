package com.nightonke.saver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.nightonke.saver.R;
import com.nightonke.saver.model.RecordManager;
import com.nightonke.saver.model.SettingManager;
import com.nightonke.saver.util.CoCoinUtil;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;
import net.steamcrafted.materialiconlib.MaterialIconView;

/**
 * Created by 伟平 on 2015/10/16.
 */

public class ButtonGridViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;
    private  ViewHolder holder;

    public ButtonGridViewAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return CoCoinUtil.BUTTONS.length;
    }

    @Override
    public Integer getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            function4(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == 11) {
            function1();
        } else if (position == 9) {
            function2();
        } else {
            function3(position);
        }

        holder.ml.setRippleDuration(300);
        boolean shouldChange
                = SettingManager.getInstance().getIsMonthLimit()
                && SettingManager.getInstance().getIsColorRemind()
                && RecordManager.getCurrentMonthExpense()
                >= SettingManager.getInstance().getMonthWarning();
        if (shouldChange) {
            function5();
        } else {
            function6();
        }

        return convertView;
    }


    public void function6(){
        holder.fl.setBackgroundColor(CoCoinUtil.getAlphaColor(CoCoinUtil.MY_BLUE));
        holder.ml.setRippleColor(CoCoinUtil.MY_BLUE);
        holder.iv.setColor(CoCoinUtil.MY_BLUE);
        holder.tv.setTextColor(CoCoinUtil.MY_BLUE);
    }

    public void function5(){
        holder.fl.setBackgroundColor(CoCoinUtil.getAlphaColor(SettingManager.getInstance().getRemindColor()));
        holder.ml.setRippleColor(SettingManager.getInstance().getRemindColor());
        holder.iv.setColor(SettingManager.getInstance().getRemindColor());
        holder.tv.setTextColor(SettingManager.getInstance().getRemindColor());
    }

    public void function4( View convertView1){
        holder = new ViewHolder();
        convertView1 = this.inflater.inflate(R.layout.button_gridview_item, null);
        holder.fl = (FrameLayout)convertView1.findViewById(R.id.frame_layout);
        holder.iv = (MaterialIconView)convertView1.findViewById(R.id.icon);
        holder.tv = (TextView) convertView1.findViewById(R.id.textview);
        holder.ml = (MaterialRippleLayout)convertView1.findViewById(R.id.material_ripple_layout);
        convertView1.setTag(holder);
    }

    public void duplicate(){
        holder.iv.setVisibility(View.INVISIBLE);
        holder.tv.setTypeface(CoCoinUtil.typefaceLatoHairline);
    }

    public void function3(int posizione){
        duplicate();
        holder.tv.setText(CoCoinUtil.BUTTONS[posizione]);
        holder.ml.setRippleDelayClick(false);
    }

    public void function2(){
        holder.iv.setIcon(MaterialDrawableBuilder.IconValue.ERASER);
        duplicate();
        holder.ml.setRippleAlpha(50);
    }

    public void function1(){
        duplicate();
        holder.iv.setIcon(MaterialDrawableBuilder.IconValue.CHECK);
        holder.ml.setRippleAlpha(50);
    }


    private class ViewHolder {
        FrameLayout fl;
        TextView tv;
        MaterialIconView iv;
        MaterialRippleLayout ml;
    }
}
