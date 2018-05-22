package com.abamobile.empleodigital.trackingapp.design.interfaces;

import com.abamobile.empleodigital.trackingapp.logic.Task;

/**
 * Implemented by the controller of the activity that shows the tasks log
 * @version 1.0
 * @see Task
 */
public interface TaskLogController {
    /**
     * Asks the database for the tasks of a given shift and calls the activity.
     */
    void loadTasks();
}
