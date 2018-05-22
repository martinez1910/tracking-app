package com.abamobile.empleodigital.trackingapp.design.interfaces;

import com.abamobile.empleodigital.trackingapp.logic.Task;

import java.util.List;

/**
 * Implemented by the activity that shows the tasks of the current shift
 * @version 1.0
 * @see Task
 */
public interface TaskView {

    /**
     * Called by the activity handler to show the tasks of the current shift
     * @param tasks the list with the tasks
     */
    void showTasks(List<Task> tasks);

    /**
     * Called by the activity handler to update the timer of a task
     * @param time the new time in milliseconds
     */
    void updateElapsedTime(long time);
}
