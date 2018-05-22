package com.abamobile.empleodigital.trackingapp.design.activities;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.abamobile.empleodigital.trackingapp.R;
import com.abamobile.empleodigital.trackingapp.databases.RepositoryImp;
import com.abamobile.empleodigital.trackingapp.design.controllers.AddTaskImp;
import com.abamobile.empleodigital.trackingapp.design.interfaces.AddTaskController;
import com.abamobile.empleodigital.trackingapp.design.interfaces.AddTaskView;
import com.abamobile.empleodigital.trackingapp.logic.Task;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;


public class AddTaskActivity extends BaseActivity implements AddTaskView{
    protected final static int RESULT_CODE_ADD = 1;

    private AddTaskController controller;
    private Task task;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        this.task = new Task();
        this.controller = new AddTaskImp(this, RepositoryImp.getInstance(this));

        putMenu(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_cancelNewTask:
                this.finish();
                break;
            case R.id.bt_addNewtask:
                addTask();
                break;
            case R.id.bt_palette:
                openColorPicker();
                break;
        }
    }

    private void openColorPicker() {
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(10)
                .setPositiveButton(android.R.string.ok, new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        ImageView palette = findViewById(R.id.bt_palette);
                        palette.setColorFilter(selectedColor);
                        EditText taskName = findViewById(R.id.et_task);
                        taskName.getBackground().mutate().setColorFilter(selectedColor, PorterDuff.Mode.SRC_ATOP);

                        task.setColor(selectedColor);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .build()
                .show();
    }

    private void addTask(){
        task.setName(((EditText) findViewById(R.id.et_task)).getText().toString());
        controller.addTask(task);
    }

    @Override
    public void taskAdded() {
        this.setResult(RESULT_CODE_ADD);
        this.finish();
    }
}
