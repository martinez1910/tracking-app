package com.abamobile.empleodigital.trackingapp.design.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.abamobile.empleodigital.trackingapp.R;
import com.abamobile.empleodigital.trackingapp.databases.RepositoryImp;
import com.abamobile.empleodigital.trackingapp.design.controllers.TaskImp;
import com.abamobile.empleodigital.trackingapp.design.interfaces.TaskController;
import com.abamobile.empleodigital.trackingapp.design.interfaces.TaskView;
import com.abamobile.empleodigital.trackingapp.logic.Task;

import java.util.List;

public class TaskActivity extends BaseActivity implements TaskView {

    private final static int REQUEST_CODE = 0;
    private int running_task_position;
    private Task selectedTask;
    private TaskController controller;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_task);


        this.controller = new TaskImp(this, RepositoryImp.getInstance(this));
        controller.loadTasks();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        putMenu(this);

        prepareList();
    }

    private void prepareList() {
        Task task = controller.getUnfinishedTask();
        if (task != null) {
            controller.startTask(task);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == AddTaskActivity.RESULT_CODE_ADD)
            controller.loadTasks();
    }

    @Override
    public void showTasks(final List<Task> tasks) {
        SwipeMenuListView tasksView = findViewById(R.id.lv_tasks);
        adapter = new TaskAdapter(this);
        tasksView.setAdapter(adapter);
        adapter.updateTasks(tasks);


        tasksView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                new AlertDialog.Builder(TaskActivity.this)
                        .setTitle(R.string.title_deleteTask)
                        .setMessage(R.string.message_deleteTask)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Task deletedTask = adapter.getTask(position);
                                controller.removeTask(deletedTask);
                                adapter.removeTask(position);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();
                return false;
            }
        });

        tasksView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTask = adapter.getTask(position);
                if (selectedTask.getCounter().getCurrentTime() == 0) {
                    if(controller.getUnfinishedTask() == null){
                        running_task_position = position;
                        Toast.makeText(TaskActivity.this, getString(R.string.starting_taskactivity) + " " + selectedTask.getName(), Toast.LENGTH_SHORT).show();
                        controller.startTask(selectedTask);
                    }else{
                        new AlertDialog.Builder(TaskActivity.this)
                                .setTitle(R.string.title_NotSimultaneousTask)
                                .setMessage(R.string.message_NotSimultaneousTask)
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {}})
                                .show();
                    }
                } else {

                    if (selectedTask.getCounter().isRunning()) {
                        new AlertDialog.Builder(TaskActivity.this)
                                .setTitle(R.string.title_stopTask)
                                .setMessage(R.string.message_stopTask)
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        controller.stopTask(selectedTask);
                                        Toast.makeText(TaskActivity.this, getString(R.string.finishing_taskactivity) + " " + selectedTask.getName(), Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton(android.R.string.cancel, null)
                                .show();
                    }
                }
            }
        });

        tasksView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(255,
                        0, 0)));
                // set item width
                deleteItem.setWidth(200);
                // set a icon
                deleteItem.setIcon(ContextCompat.getDrawable(TaskActivity.this, R.drawable.ic_delete));
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        tasksView.setMenuCreator(creator);
    }

    @Override
    public void updateElapsedTime(long time) {
        Task task = adapter.getTask(running_task_position);
        task.getCounter().setElapsedTime(time);
        adapter.updateTask(task, running_task_position);
    }
}
