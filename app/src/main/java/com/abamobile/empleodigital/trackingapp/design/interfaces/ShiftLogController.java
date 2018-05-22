package com.abamobile.empleodigital.trackingapp.design.interfaces;

import com.abamobile.empleodigital.trackingapp.logic.Shift;

/**
 * Implemented by the controller of the activity that shows the shifts log
 * @version 1.0
 * @see Shift
 */
public interface ShiftLogController {
    /**
     * Asks the database for the finished shifts and calls the activity.
     */
    void loadShiftLog();

    /**
     * Saves the selected shift from the list into the repository for further access.
     * @param shift the shift to be cached
     */
    void saveCacheShift(Shift shift);
}
