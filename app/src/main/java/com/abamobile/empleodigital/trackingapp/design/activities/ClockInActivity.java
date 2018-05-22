package com.abamobile.empleodigital.trackingapp.design.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abamobile.empleodigital.trackingapp.R;
import com.abamobile.empleodigital.trackingapp.databases.RepositoryImp;
import com.abamobile.empleodigital.trackingapp.design.controllers.ClockInImp;
import com.abamobile.empleodigital.trackingapp.design.interfaces.ClockInController;
import com.abamobile.empleodigital.trackingapp.design.interfaces.ClockInView;
import com.abamobile.empleodigital.trackingapp.logic.Task;

public class ClockInActivity extends BaseActivity implements ClockInView {

    private boolean isRunning = false;
    private ClockInController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_in);
        controller = new ClockInImp(this, RepositoryImp.getInstance(this));

        putMenu(this);

        prepareShift();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_chooseTask:
                if (isRunning) {
                    Intent intent1 = new Intent(this, TaskActivity.class);
                    startActivity(intent1);
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.StartShiftFirst)
                            .setMessage(R.string.message_start_shift)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
                break;
            case R.id.bt_play:
                if(!isRunning){
                    isRunning = !isRunning;
                    toggleButtonStyle();
                    controller.play();
                }else{
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.title_stop)
                            .setMessage(R.string.message_stop)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    isRunning = !isRunning;
                                    toggleButtonStyle();
                                    Task task = controller.getUnfinishedTask();
                                    if(task != null){
                                        task.stopTask();
                                        controller.updateTaskFromCurrentShift(task);
                                    }
                                    controller.stop();
                                }
                            })
                            .setNegativeButton(android.R.string.cancel, null)
                            .show();
                }
                break;
        }
    }

    private void toggleButtonStyle() {
        if (isRunning) {
            ((LinearLayout) findViewById(R.id.ll_horaInicio)).setBackgroundColor(getResources().getColor(R.color.Lightgreen));
            ((Button) findViewById(R.id.bt_play)).setText(R.string.StopShift);
            ((Button) findViewById(R.id.bt_play)).setBackgroundResource(R.drawable.button_stop);


        } else {
            ((LinearLayout) findViewById(R.id.ll_horaInicio)).setBackgroundColor(getResources().getColor(R.color.GrisButton));
            ((Button) findViewById(R.id.bt_play)).setText(R.string.PlayShift);
            ((Button) findViewById(R.id.bt_play)).setBackgroundResource(R.drawable.button_play);
        }
    }

    private void prepareShift() {
        if (controller.existsUnfinishedShift()) {
            isRunning = true;
            toggleButtonStyle();
            controller.play();
        }
    }

    @Override
    public void showInitialTime(String time) {
        ((TextView) findViewById(R.id.tv_horaInicio)).setText(time);
    }

    @Override
    public void showElapsedTime(String time) {
        ((TextView) findViewById(R.id.tv_tiempoTranscurrido)).setText(time);
    }
}

