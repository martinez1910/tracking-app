package com.abamobile.empleodigital.trackingapp.design.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.abamobile.empleodigital.trackingapp.R;
import com.abamobile.empleodigital.trackingapp.databases.RepositoryImp;
import com.abamobile.empleodigital.trackingapp.design.controllers.ShiftLogImp;
import com.abamobile.empleodigital.trackingapp.design.interfaces.ShiftLogController;
import com.abamobile.empleodigital.trackingapp.design.interfaces.ShiftLogView;
import com.abamobile.empleodigital.trackingapp.logic.Shift;

import java.util.List;

public class ShiftLogActivity extends BaseActivity implements ShiftLogView {

    private ShiftLogController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_log);

        controller = new ShiftLogImp(this, RepositoryImp.getInstance(this));
        controller.loadShiftLog();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        putMenu(this);

    }


    @Override
    public void showShiftLog(List<Shift> shifts) {
        ListView daysView = findViewById(R.id.lv_shift);
        final ShiftLogAdapter adapter = new ShiftLogAdapter(this);
        daysView.setAdapter(adapter);
        adapter.updateShift(shifts);
        daysView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                controller.saveCacheShift(adapter.getShift(position));
                Intent intent = new Intent(ShiftLogActivity.this, TaskLogActivity.class);
                startActivity(intent);
            }
        });
    }
}
