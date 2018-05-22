package com.abamobile.empleodigital.trackingapp.design.interfaces;

/**
 * Implemented by the activity that starts a new shift.
 * @version 1.0
 */
public interface ClockInView {

    /**
     * Called by the activity handler to show the initial time provided
     * @param time the time provided by the controller
     */
    void showInitialTime(String time);

    /**
     * Called by the activity handler to show the elapsed time provided
     * @param time the time provided by the controller
     */
    void showElapsedTime(String time);
}
