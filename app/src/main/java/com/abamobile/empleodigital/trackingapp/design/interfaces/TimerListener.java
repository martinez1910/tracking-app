package com.abamobile.empleodigital.trackingapp.design.interfaces;

/**
 * Implemented by the activity handler that updates the timers in real time.
 * @version 1.0
 * @see com.abamobile.empleodigital.trackingapp.logic.Shift
 * @see com.abamobile.empleodigital.trackingapp.logic.Task
 */
public interface TimerListener {

    /**
     * Calls the handled activity to update the elapsed time.
     * @param time the new time to update the timer
     */
    void updateElapsedTime(long time);
}
