package com.nightonke.saver.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.nightonke.saver.R;
import com.nightonke.saver.activity.CoCoinApplication;
import com.nightonke.saver.adapter.TodayViewRecyclerViewAdapter;
import com.nightonke.saver.model.CoCoinRecord;
import com.nightonke.saver.model.RecordManager;
import com.nightonke.saver.util.CoCoinUtil;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 伟平 on 2015/10/20.
 */

public class TodayViewFragment extends Fragment {

    private int position;

    private List<CoCoinRecord> list = new ArrayList<>();

    private Context mContext;

    private RecyclerView mRecyclerView;
    private RecyclerViewMaterialAdapter mAdapter;
    private RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    static final int TODAY = 0;
    static final int YESTERDAY = 1;
    static final int THIS_WEEK = 2;
    static final int LAST_WEEK = 3;
    static final int THIS_MONTH = 4;
    static final int LAST_MONTH = 5;
    static final int THIS_YEAR = 6;
    static final int LAST_YEAR = 7;


    //TO-DO CONTROL HERE
    public static TodayViewFragment newInstance() {
        TodayViewFragment fragment = new TodayViewFragment();
        Bundle argom = new Bundle();
        int k;
        argom.putInt("POSITION", k);
        fragment.setArguments(argom);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        position = getArguments() != null ? getArguments().getInt("POSITION") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.today_fragment, container, false);
    }


    public void cond(){
        if (start == -1) {
            start = i;
        }
    }

    public void fun1(){
        leftRang  = CoCoinUtil.GetTodayLeftRange(now);
        for (int i = recordManager.RECORDS.size() - 1; i >= 0; i--) {
            if (recordManager.RECORDS.get(i).getCalendar().before(leftRange)) {
                end = i + 1;
            }
            cond();
        }
    }

    public void fun2(){
        leftRange = CoCoinUtil.GetYesterdayLeftRange(now);
        rightRange = CoCoinUtil.GetYesterdayRightRange(now);
        for (int i = recordManager.RECORDS.size() - 1; i >= 0; i--) {
            if (recordManager.RECORDS.get(i).getCalendar().before(leftRange)) {
                end = i + 1;
            } else cond1();
        }
    }

    public void fun3(){
        leftRange = CoCoinUtil.GetThisWeekLeftRange(now);
        for (int i = recordManager.RECORDS.size() - 1; i >= 0; i--) {
            if (recordManager.RECORDS.get(i).getCalendar().before(leftRange)) {
                end = i + 1;
            }
            cond();
        }
    }

    public void fun4(){
        leftRange = CoCoinUtil.GetLastWeekLeftRange(now);
        rightRange = CoCoinUtil.GetLastWeekRightRange(now);
        for (int i = recordManager.RECORDS.size() - 1; i >= 0; i--) {
            if (recordManager.RECORDS.get(i).getCalendar().before(leftRange)) {
                end = i + 1;
            } else cond1();
        }
    }

    public void fun5(){
        leftRange = CoCoinUtil.GetThisMonthLeftRange(now);
        for (int i = recordManager.RECORDS.size() - 1; i >= 0; i--) {
            if (recordManager.RECORDS.get(i).getCalendar().before(leftRange)) {
                end = i + 1;
            }
            cond();
        }
    }

    public void fun6(){
        leftRange = CoCoinUtil.GetLastMonthLeftRange(now);
        rightRange = CoCoinUtil.GetLastMonthRightRange(now);
        for (int i = recordManager.RECORDS.size() - 1; i >= 0; i--) {
            if (recordManager.RECORDS.get(i).getCalendar().before(leftRange)) {
                end = i + 1;
            } else cond1();
        }
    }

    public void fun7(){
        leftRange = CoCoinUtil.GetThisYearLeftRange(now);
        for (int i = recordManager.RECORDS.size() - 1; i >= 0; i--) {
            if (recordManager.RECORDS.get(i).getCalendar().before(leftRange)) {
                end = i + 1;
            }
            cond();
        }
    }

    public void fun8(){
        leftRange = CoCoinUtil.GetLastYearLeftRange(now);
        rightRange = CoCoinUtil.GetLastYearRightRange(now);
        for (int i = recordManager.RECORDS.size() - 1; i >= 0; i--) {
            if (recordManager.RECORDS.get(i).getCalendar().before(leftRange)) {
                end = i + 1;
            } else cond1();
        }
    }

    public void cond1(){
        if (recordManager.RECORDS.get(i).getCalendar().before(rightRange)) {
            cond();
        }
    }

    public void functionz(int num){
        switch(num){
            case TODAY:
                fun1();
                break;
            case YESTERDAY:
                fun2();
                break;
            case THIS_WEEK:
                fun3();
                break;
            case LAST_WEEK:
                fun4();
                break;
        }
    }

    public void functionz(int num){
        switch(num){
            case THIS_MONTH:
                fun5();
                break;
            case LAST_MONTH:
                fun6();
                break;
            case THIS_YEAR:
                fun7();
                break;
            case LAST_YEAR:
                fun8();
                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        Calendar now = Calendar.getInstance();
        Calendar leftRange;
        Calendar rightRange;

        RecordManager recordManager = RecordManager.getInstance(mContext.getApplicationContext());
        int start = -1;
        int end = 0;

        if(position<4){
            functionz(position);
        }
        else{
            funtionf(position);
        }

        adapter = new TodayViewRecyclerViewAdapter(start, end, mContext, position);

        mAdapter = new RecyclerViewMaterialAdapter(adapter);
        mRecyclerView.setAdapter(mAdapter);

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        RefWatcher refWatcher = CoCoinApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);

    }

}
