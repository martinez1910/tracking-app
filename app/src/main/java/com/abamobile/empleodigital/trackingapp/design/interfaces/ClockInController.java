package com.abamobile.empleodigital.trackingapp.design.interfaces;

import com.abamobile.empleodigital.trackingapp.logic.Task;

/**
 * Implemented by the controller of the activity that starts a new shift
 * @version 1.0
 * @see com.abamobile.empleodigital.trackingapp.logic.Shift
 * @see Task
 */
public interface ClockInController {

    /**
     * Handles the logic when the user starts a new shift
     */
    void play();

    /**
     * Handles the logic when the user stops the shift
     */
    void stop();

    /**
     * Performs a call to the database to know if exists an unfinished shift to be loaded when the activity is created
     * @return true if exists an unfinished shift, false otherwise.
     */
    boolean existsUnfinishedShift();

    /**
     * Performs a call to the database to retrieve the task that is not finished
     * @return the unfinished task
     * @see Task
     */
    Task getUnfinishedTask();

    /**
     * Stores the given task in the database
     * @param task the task to be stored
     * @see Task
     */
    void updateTaskFromCurrentShift(Task task);
}
