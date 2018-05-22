package com.abamobile.empleodigital.trackingapp.design.interfaces;

import com.abamobile.empleodigital.trackingapp.logic.Task;

/**
 * Implemented by the controller of the activity that shows the tasks of the current shift
 * @version 1.0
 * @see Task
 */
public interface TaskController {
    /**
     * Asks the database for the tasks of the current shift and calls the activity.
     */
    void loadTasks();

    /**
     * Starts a given task
     * @param task the task to be started
     */
    void startTask(Task task);

    /**
     * Stops a given task
     * @param task the task to be stopped
     */
    void stopTask(Task task);

    /**
     * Removes the given task
     * @param task the task to be removed
     */
    void removeTask(Task task);

    /**
     * Returns the task that has not been finished yet.
     * @return the unfinished task
     */
    Task getUnfinishedTask();
}
