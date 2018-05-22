package com.abamobile.empleodigital.trackingapp.databases;

import android.content.Context;

import com.abamobile.empleodigital.trackingapp.logic.Shift;
import com.abamobile.empleodigital.trackingapp.logic.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton. Repository that acts as a middle-man between the database and the activity controllers
 * @version 1.0
 * @see Shift
 * @see Task
 */
public class RepositoryImp implements Repository {
    private static RepositoryImp INSTANCE = null;
    private Shift cachedShift = null;
    private LocalDatabase database;

    /**
     * Private constructor used when the instance of the singleton is created.
     * @param context
     */
    private RepositoryImp(Context context) {
        database = LocalDatabase.getINSTANCE(context);
    }

    /**
     * Singleton. Returns or creates the instance.
     * @param contex
     * @return instance of the class
     */
    public static RepositoryImp getInstance(Context contex) {
        if(INSTANCE == null) {
            INSTANCE = new RepositoryImp(contex);
        }
        return INSTANCE;
    }

    @Override
    public Shift getCurrentShift() {
      ShiftEntity entity =  database.getShiftDAO().getCurrentShift();
      return entity == null ? null : ShiftMapper.map(entity);
    }

    @Override
    public void saveShift(Shift shift) {
        database.getShiftDAO().insertShift(ShiftMapper.map(shift));
    }

    @Override
    public void updateShift(Shift shift) {
        database.getShiftDAO().updateShift(ShiftMapper.map(shift));
    }

    @Override
    public List<Shift> getAllShifts() {
        List<ShiftEntity> shiftEntities = database.getShiftDAO().getShifts();
        List<Shift> shifts = new ArrayList<Shift>();
        for(ShiftEntity shiftEntity : shiftEntities)
            shifts.add(ShiftMapper.map(shiftEntity));
        return shifts;
    }

    @Override
    public List<Shift> getAllFinishedShifts() {
        List<ShiftEntity> shiftEntities = database.getShiftDAO().getFinishedShifts();
        List<Shift> shifts = new ArrayList<Shift>();
        for(ShiftEntity shiftEntity : shiftEntities)
            shifts.add(ShiftMapper.map(shiftEntity));
        return shifts;
    }

    @Override
    public List<Task> getTasksFromShift(Shift shift) {
        List<TaskEntity> taskEntities = database.getTaskDAO().getTasksFromShift(shift.getTimestamp());
        List<Task> tasks = new ArrayList<Task>();
        for(TaskEntity taskEntity : taskEntities)
            tasks.add(TaskMapper.map(taskEntity));
        return tasks;
    }

    @Override
    public List<Task> getTasksFromCurrentShift() {
        Shift shift = ShiftMapper.map(database.getShiftDAO().getCurrentShift());
        List<TaskEntity> taskEntities = database.getTaskDAO().getTasksFromShift(shift.getTimestamp());
        List<Task> tasks = new ArrayList<Task>();
        for(TaskEntity taskEntity : taskEntities)
            tasks.add(TaskMapper.map(taskEntity));
        return tasks;
    }

    @Override
    public void saveTaskInCurrentShift(Task task) {
        Shift shift = ShiftMapper.map(database.getShiftDAO().getCurrentShift());
        database.getTaskDAO().insertTask(TaskMapper.map(task, shift));
    }

    @Override
    public void removeTaskFromCurrentShift(Task task) {
        Shift shift = ShiftMapper.map(database.getShiftDAO().getCurrentShift());
        database.getTaskDAO().deleteTask(TaskMapper.map(task, shift));
    }

    @Override
    public void updateTaskFromCurrentShift(Task task) {
        Shift shift = ShiftMapper.map(database.getShiftDAO().getCurrentShift());
        database.getTaskDAO().updateTask(TaskMapper.map(task, shift));
    }

    @Override
    public Task getCurrentTask() {
        TaskEntity taskEntity = database.getTaskDAO().getCurrentTask();
        return taskEntity == null ? null : TaskMapper.map(taskEntity);
    }

    @Override
    public void setCachedShift(Shift shift) {
        this.cachedShift = shift;
    }

    @Override
    public Shift getCachedShift() {
        return cachedShift;
    }
}
