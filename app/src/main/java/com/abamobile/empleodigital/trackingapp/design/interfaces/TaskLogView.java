package com.abamobile.empleodigital.trackingapp.design.interfaces;

import com.abamobile.empleodigital.trackingapp.logic.Shift;
import com.abamobile.empleodigital.trackingapp.logic.Task;

import java.util.List;

/**
 * Implemented by the activity that shows the task log of a given shift
 * @version 1.0
 * @see Task
 */
public interface TaskLogView {

    /**
     * Called by the activity handler to show the tasks log
     * @param tasks the list with the tasks
     */
    void showTaskLog(List<Task> tasks);

    /**
     * Called by the activity handler to show the tasks log in the pie chart
     * @param tasks the list with the tasks
     * @param shift the shift when the tasks where made
     */
    void updatePieChart(List<Task> tasks, Shift shift);
}
