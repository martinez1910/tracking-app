package com.abamobile.empleodigital.trackingapp.design.controllers;

import com.abamobile.empleodigital.trackingapp.databases.Repository;
import com.abamobile.empleodigital.trackingapp.design.interfaces.AddTaskController;
import com.abamobile.empleodigital.trackingapp.design.interfaces.AddTaskView;
import com.abamobile.empleodigital.trackingapp.logic.Task;

/**
 * Handles the activity that creates a new task
 * @version 1.0
 * @see Task
 */
public class AddTaskImp implements AddTaskController {
    private AddTaskView view;
    private Repository repository;

    /**
     * Main constructor
     * @param view the view that handles
     * @param repository the database
     */
    public AddTaskImp(AddTaskView view, Repository repository){
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void addTask(Task task) {
        repository.saveTaskInCurrentShift(task);
        view.taskAdded();
    }
}
