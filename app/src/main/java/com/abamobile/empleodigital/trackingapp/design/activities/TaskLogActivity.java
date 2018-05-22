package com.abamobile.empleodigital.trackingapp.design.activities;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;


import com.abamobile.empleodigital.trackingapp.R;
import com.abamobile.empleodigital.trackingapp.databases.RepositoryImp;
import com.abamobile.empleodigital.trackingapp.design.controllers.TaskLogImp;
import com.abamobile.empleodigital.trackingapp.design.interfaces.TaskLogController;
import com.abamobile.empleodigital.trackingapp.design.interfaces.TaskLogView;
import com.abamobile.empleodigital.trackingapp.logic.Shift;
import com.abamobile.empleodigital.trackingapp.logic.Task;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import static com.abamobile.empleodigital.trackingapp.logic.DateUtility.dateFormat;


public class TaskLogActivity extends BaseActivity implements TaskLogView{

    private TextView fechaView;
    private PieChart pieChart;
    private TaskAdapter adapter;
    private TaskLogController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_log);

        fechaView = findViewById(R.id.tv_fecha);
        pieChart = findViewById(R.id.chart1);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.animateY(2000, Easing.EasingOption.EaseInOutBack);
        pieChart.setEntryLabelTextSize(0f);
        pieChart.setHoleRadius(55f);
        pieChart.setTransparentCircleRadius(58f);
        pieChart.setHoleColor(getResources().getColor(R.color.colorBackground));

        ListView tasksView = findViewById(R.id.lv_tasks);
        adapter = new TaskAdapter(this);
        tasksView.setAdapter(adapter);

        controller = new TaskLogImp(this, RepositoryImp.getInstance(this));
        controller.loadTasks();

        putMenu(this);
    }

    @Override
    public void showTaskLog(List<Task> tasks){
        adapter.updateTasks(tasks);
    }


    @Override
    public void updatePieChart(List<Task> tasks, Shift shift){
        List<PieEntry> pieEntries = new ArrayList<>();
        long tasksTime = 0;
        for (Task task: tasks){
            pieEntries.add(new PieEntry(task.getCounter().getElapsedTime(),task.getName()));
            tasksTime += task.getCounter().getElapsedTime();
        }


        fechaView.setText(dateFormat("dd MMMM yyyy",shift.startDate().getTime()));
        long idleTime = shift.getCounter().getElapsedTime() - tasksTime;
        pieEntries.add(new PieEntry(idleTime, R.string.no_task));



        PieDataSet dataSet = new PieDataSet(pieEntries, "");



        ArrayList<Integer> colors = new ArrayList<>();
        for(Task task : tasks)
            colors.add(task.getColor());
        colors.add(Color.GRAY);
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);

        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(14f);


        pieChart.setData(data);
        pieChart.setCenterText(getString(R.string.totaltime) + dateFormat("HH'h' mm'm'",shift.getCounter().getElapsedTime()));
        pieChart.setCenterTextSize(15f);
        pieChart.invalidate();
    }
}
