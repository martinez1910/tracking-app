package com.abamobile.empleodigital.trackingapp.design.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.abamobile.empleodigital.trackingapp.R;
import com.abamobile.empleodigital.trackingapp.logic.DateUtility;
import com.abamobile.empleodigital.trackingapp.logic.Shift;

import java.util.ArrayList;
import java.util.List;

public class ShiftLogAdapter extends BaseAdapter {

    private List<Shift> shifts;
    private Context context;

    public ShiftLogAdapter(Context context){
        shifts = new ArrayList<>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return shifts.size();
    }

    @Override
    public Object getItem(int position) {
        return shifts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.element_shift_log, null);
        }
        Shift shift = shifts.get(position);

        TextView dateView = convertView.findViewById(R.id.tv_date);
        TextView timeView = convertView.findViewById(R.id.tv_time);

        dateView.setText(DateUtility.dateFormat(shift.startDate()));
        timeView.setText(DateUtility.workTime(shift.getCounter().getElapsedTime()));

        return convertView;
    }

    public Shift getShift(int position) {
        return shifts.get(position);
    }

    public void updateShift(List<Shift> shifts) {
        this.shifts.clear();
        this.shifts.addAll(shifts);
        notifyDataSetChanged();
    }
}
