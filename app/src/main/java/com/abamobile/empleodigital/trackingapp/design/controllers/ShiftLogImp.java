package com.abamobile.empleodigital.trackingapp.design.controllers;

import com.abamobile.empleodigital.trackingapp.databases.Repository;
import com.abamobile.empleodigital.trackingapp.design.interfaces.ShiftLogController;
import com.abamobile.empleodigital.trackingapp.design.interfaces.ShiftLogView;
import com.abamobile.empleodigital.trackingapp.logic.Shift;

/**
 * Handles the activity that shows the shifts log
 * @version 1.0
 * @see Shift
 */
public class ShiftLogImp implements ShiftLogController {

    private ShiftLogView view;
    private Repository repository;

    /**
     * Main constructor
     * @param view the view that handles
     * @param repository the database
     */
    public ShiftLogImp(ShiftLogView view, Repository repository){
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void loadShiftLog() {
        view.showShiftLog(repository.getAllFinishedShifts());
    }

    @Override
    public void saveCacheShift(Shift shift) {
        repository.setCachedShift(shift);
    }
}
