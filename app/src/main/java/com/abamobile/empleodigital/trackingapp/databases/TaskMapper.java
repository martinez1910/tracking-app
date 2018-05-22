package com.abamobile.empleodigital.trackingapp.databases;

import com.abamobile.empleodigital.trackingapp.logic.Counter;
import com.abamobile.empleodigital.trackingapp.logic.Shift;
import com.abamobile.empleodigital.trackingapp.logic.Task;

/**
 * Mapper between a Task (Java object) and a TaskEntity (persistence library 'Room' object)
 * @see Task
 * @see TaskEntity
 */
public class TaskMapper {
    public static TaskEntity map(Task task, Shift shift){
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTimestamp(task.getTimestamp());
        taskEntity.setFinished(task.isFinished());
        taskEntity.setT_start(task.getCounter().getStartTime());
        taskEntity.setT_stop(task.getCounter().getStopTime());
        taskEntity.setT_elapsed(task.getCounter().getElapsedTime());
        taskEntity.setRunning(task.getCounter().isRunning());
        taskEntity.setName(task.getName());
        taskEntity.setColor(task.getColor());

        taskEntity.setTimestamp_shift(shift.getTimestamp());

        return taskEntity;
    }

    public static Task map(TaskEntity taskEntity){
        Task task = new Task();

        task.setTimestamp(taskEntity.getTimestamp());
        task.setFinished(taskEntity.isFinished());
        Counter counter = new Counter();
        counter.setStartTime(taskEntity.getT_start());
        counter.setStopTime(taskEntity.getT_stop());
        counter.setElapsedTime(taskEntity.getT_elapsed());
        counter.setRunning(taskEntity.isRunning());
        task.setCounter(counter);
        task.setName(taskEntity.getName());
        task.setColor(taskEntity.getColor());

        return task;
    }
}
