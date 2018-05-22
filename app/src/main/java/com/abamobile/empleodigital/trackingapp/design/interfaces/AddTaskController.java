package com.abamobile.empleodigital.trackingapp.design.interfaces;

import com.abamobile.empleodigital.trackingapp.logic.Task;

/**
 * Implemented by the controller of the activity that adds a new task
 * @version 1.0
 * @see Task
 */
public interface AddTaskController {
    /**
     * Adds the new task to the database and calls the activity.
     * @param task
     */
    void addTask(Task task);
}
