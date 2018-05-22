package com.abamobile.empleodigital.trackingapp.design.controllers;


import com.abamobile.empleodigital.trackingapp.databases.Repository;
import com.abamobile.empleodigital.trackingapp.design.interfaces.ClockInController;
import com.abamobile.empleodigital.trackingapp.design.interfaces.ClockInView;
import com.abamobile.empleodigital.trackingapp.design.interfaces.TimerListener;
import com.abamobile.empleodigital.trackingapp.logic.DateUtility;
import com.abamobile.empleodigital.trackingapp.logic.Shift;
import com.abamobile.empleodigital.trackingapp.logic.Task;
import com.abamobile.empleodigital.trackingapp.logic.Timer;

/**
 * Handles the activity that starts the shift
 * @version 1.0
 * @see Shift
 * @see Task
 */
public class ClockInImp implements ClockInController, TimerListener {
    private Repository repository;
    private ClockInView view;
    private Timer timer;

    /**
     * Main constructor
     * @param view the view that handles
     * @param repository the database
     */
    public ClockInImp(ClockInView view, Repository repository) {
        this.view = view;
        this.repository = repository;
        timer = new Timer(this);
    }

    @Override
    public void play() {
        Shift shift = repository.getCurrentShift();

        if(shift != null && shift.getCounter().isRunning()){
            shift.getCounter().setElapsedTime(System.currentTimeMillis() - shift.getCounter().getStartTime());
        }

        if(shift == null) {
            shift = new Shift();
            shift.startShift();
            repository.saveShift(shift);
        }

        timer.start(shift);
        view.showInitialTime(getShiftInitialTimeHours(shift));
    }

    @Override
    public void stop() {
        Shift shift = repository.getCurrentShift();
        timer.stop();
        if(shift != null){
            shift.stopShift();
            repository.updateShift(shift);
        }
    }

    @Override
    public boolean existsUnfinishedShift() {
        Shift shift = repository.getCurrentShift();
        return shift != null;
    }

    @Override
    public Task getUnfinishedTask() {
        Task task = repository.getCurrentTask();
        return task;
    }

    @Override
    public void updateTaskFromCurrentShift(Task task) {
        repository.updateTaskFromCurrentShift(task);
    }

    @Override
    public void updateElapsedTime(long time) {
        view.showElapsedTime(DateUtility.workTime(time));
    }

    private String getShiftInitialTimeHours(Shift shift) {
        return DateUtility.hourFormat(shift.startDate());
    }
}
