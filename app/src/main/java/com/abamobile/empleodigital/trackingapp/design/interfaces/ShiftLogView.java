package com.abamobile.empleodigital.trackingapp.design.interfaces;

import com.abamobile.empleodigital.trackingapp.logic.Shift;

import java.util.List;

/**
 * Implemented by the activity that shows the shifts log
 * @version 1.0
 */
public interface ShiftLogView {

    /**
     * Called by the activity handler to show the shifts log
     * @param shifts the list with the shifts
     */
    void showShiftLog(List<Shift> shifts);
}
