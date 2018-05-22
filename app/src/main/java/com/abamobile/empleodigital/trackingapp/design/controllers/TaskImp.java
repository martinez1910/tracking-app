package com.abamobile.empleodigital.trackingapp.design.controllers;

import com.abamobile.empleodigital.trackingapp.databases.Repository;
import com.abamobile.empleodigital.trackingapp.design.interfaces.TaskController;
import com.abamobile.empleodigital.trackingapp.design.interfaces.TaskView;
import com.abamobile.empleodigital.trackingapp.design.interfaces.TimerListener;
import com.abamobile.empleodigital.trackingapp.logic.Shift;
import com.abamobile.empleodigital.trackingapp.logic.Task;
import com.abamobile.empleodigital.trackingapp.logic.Timer;

/**
 * Handles the activity that shows the tasks of the current shift
 * @version 1.0
 * @see Shift
 * @see Task
 */
public class TaskImp implements TaskController, TimerListener {
    private TaskView view;
    private Repository repository;
    private Timer timer;

    /**
     * Main constructor
     * @param view the view that handles
     * @param repository the database
     */
    public TaskImp(TaskView view, Repository repository) {
        this.view = view;
        this.repository = repository;
        this.timer = new Timer(this);
    }

    @Override
    public void loadTasks() {
        view.showTasks(repository.getTasksFromCurrentShift());
    }

    @Override
    public void startTask(Task task) {
        task.startTask();
        repository.updateTaskFromCurrentShift(task);
        timer.start(task);
    }

    @Override
    public void stopTask(Task task) {
        timer.stop();
        task.stopTask();
        repository.updateTaskFromCurrentShift(task);
    }

    @Override
    public void removeTask(Task task) {
        stopTask(task);
        repository.removeTaskFromCurrentShift(task);
    }

    @Override
    public Task getUnfinishedTask() {
        Task task = repository.getCurrentTask();
        if(task != null)
            task.getCounter().setElapsedTime(System.currentTimeMillis() - task.getCounter().getStartTime());
        return task;
    }


    @Override
    public void updateElapsedTime(long time) {
        view.updateElapsedTime(time);
    }
}
