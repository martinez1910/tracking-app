package com.abamobile.empleodigital.trackingapp.logic;

/**
 * Implemented by Shift and Task and used in the Timer that updates in real time the timers.
 */
public interface ICounter {

    /**
     * Returns the stopwatch that handles the times.
     * @return the stopwatch that handles the times.
     */
    Counter getCounter();
}
