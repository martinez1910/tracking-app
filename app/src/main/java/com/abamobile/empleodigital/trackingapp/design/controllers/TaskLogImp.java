package com.abamobile.empleodigital.trackingapp.design.controllers;

import com.abamobile.empleodigital.trackingapp.databases.Repository;
import com.abamobile.empleodigital.trackingapp.design.interfaces.TaskLogController;
import com.abamobile.empleodigital.trackingapp.design.interfaces.TaskLogView;
import com.abamobile.empleodigital.trackingapp.logic.Shift;
import com.abamobile.empleodigital.trackingapp.logic.Task;

import java.util.List;

/**
 * Handles the activity that shows the tasks log of a given shift
 * @version 1.0
 * @see Shift
 * @see Task
 */
public class TaskLogImp implements TaskLogController {
    private TaskLogView view;
    private Repository repository;
    private Shift shift;

    /**
     * Main constructor
     * @param view the view that handles
     * @param repository the database
     */
    public TaskLogImp(TaskLogView view, Repository repository){
        this.view = view;
        this.repository = repository;
        this.shift = repository.getCachedShift();
    }

    @Override
    public void loadTasks() {
        List<Task> task = repository.getTasksFromShift(this.shift);
        view.showTaskLog(task);
        view.updatePieChart(task, shift);
    }
}
