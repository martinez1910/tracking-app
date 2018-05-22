package com.abamobile.empleodigital.trackingapp.databases;

import com.abamobile.empleodigital.trackingapp.logic.Shift;
import com.abamobile.empleodigital.trackingapp.logic.Task;

import java.util.List;

/**
 * Implemented by the repository. Contains a set of methods that interacts with the database
 * @version 1.0
 * @see Shift
 * @see Task
 */
public interface Repository {

    /**
     * Returns the shift that has not been finished
     * @return the shift if exists, null otherwise
     */
    Shift getCurrentShift();

    /**
     * Saves the given shift into the database
     * @param shift the shift to be saved
     */
    void saveShift(Shift shift);

    /**
     * Updates the given shift into the database
     * @param shift the shift to be updated
     */
    void updateShift(Shift shift);

    /**
     * Returns every shift in the database
     * @return a list containing every shift
     */
    List<Shift> getAllShifts();


    /**
     * Returns the task from the current shift that has not been finished
     * @return the unfinished task
     */
    Task getCurrentTask();

    /**
     * Returns every task from a given shift
     * @param shift the shift when the tasks where created
     * @return a list containing every task from the given shift
     */
    List<Task> getTasksFromShift(Shift shift);

    /**
     * Returns every finished shift
     * @return a list containing every finished shift
     */
    List<Shift> getAllFinishedShifts();


    /**
     * Returns every task from the current shift
     * @return a list conaining every task from the current shift
     */
    List<Task> getTasksFromCurrentShift();

    /**
     * Saves a given task in the current shift
     * @param task the task to be saved
     */
    void saveTaskInCurrentShift(Task task);

    /**
     * Removes a task from the current shift
     * @param task the task to be removed
     */
    void removeTaskFromCurrentShift(Task task);

    /**
     * Updates a task from the current shift
     * @param task the task to be updated
     */
    void updateTaskFromCurrentShift(Task task);


    /**
     * Returns the cached shift
     * @return the cached shift
     */
    Shift getCachedShift();

    /**
     * Saves the given shift into cache. Only one is cached at the same time.
     * @param shift the shift to be cached
     */
    void setCachedShift(Shift shift);
}
