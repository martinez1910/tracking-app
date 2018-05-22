package com.abamobile.empleodigital.trackingapp.design.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abamobile.empleodigital.trackingapp.R;
import com.abamobile.empleodigital.trackingapp.logic.DateUtility;
import com.abamobile.empleodigital.trackingapp.logic.Task;

import java.util.ArrayList;
import java.util.List;


public class TaskAdapter extends BaseAdapter{

    private List<Task> tasks;
    private Context context;

    public TaskAdapter(Context context){
        tasks = new ArrayList<>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.element_task, null);
        }
        Task task = tasks.get(position);

        TextView taskNameView = convertView.findViewById(R.id.tv_taskName);
        TextView timerView = convertView.findViewById(R.id.tv_timer);
        ImageView bookMarkView = convertView.findViewById(R.id.iv_bookmark);

        taskNameView.setText(task.getName());
        timerView.setText(DateUtility.workTime(task.getCounter().getElapsedTime()));
        bookMarkView.setColorFilter(task.getColor());

        return convertView;
    }

    public void removeTask(int position) {
        tasks.remove(position);
        notifyDataSetChanged();
    }

    public void updateTasks(List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
        notifyDataSetChanged();
    }

    public Task getTask(int position) {
        return tasks.get(position);
    }

    public void updateTask(Task task, int position){
        this.tasks.remove(position);
        this.tasks.add(position, task);
        notifyDataSetChanged();
    }
}
