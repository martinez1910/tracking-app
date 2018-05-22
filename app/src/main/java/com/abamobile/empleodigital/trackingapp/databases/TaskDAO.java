package com.abamobile.empleodigital.trackingapp.databases;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Implemented by the entity that models the task in the database.
 */
@Dao
public interface TaskDAO {

    /**
     * Inserts the gives task entity into the database. It's replaced if it's already in the database.
     * @param taskEntity the task entity to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTask(TaskEntity taskEntity);

    /**
     * Returns every task entity from the database
     * @return a list with every task entity
     */
    @Query("SELECT * FROM task")
    public List<TaskEntity> getTasks();

    /**
     * Returns a task entity given its id
     * @param timestamp the id of the task entity
     * @return the task entity
     */
    @Query("SELECT * FROM task WHERE timestamp = :timestamp")
    public TaskEntity getTaskById(long timestamp);

    /**
     * Returns every task entity that belongs to a given shift entity
     * @param shiftTimestamp the id of the shift entity
     * @return a list with every task belonging to the shift entity
     */
    @Query("SELECT * FROM task WHERE task.timestamp_shift = :shiftTimestamp")
    public List<TaskEntity> getTasksFromShift(long shiftTimestamp);

    /**
     * Returns every finished task entity
     * @return a list with every task that has finished
     */
    @Query("SELECT * FROM task WHERE finished = 1")
    public List<TaskEntity> getFinishedTasks();

    /**
     * Removes a given task entity from the database
     * @param taskEntity the task entity to be removed
     */
    @Delete
    public void deleteTask(TaskEntity taskEntity);

    /**
     * Updates a given task entity in the database
     * @param taskEntity the task entity to be updated
     */
    @Update
    public void updateTask (TaskEntity taskEntity);

    /**
     * Returns the task that is currently running
     * @return the running task
     */
    @Query("SELECT * FROM task WHERE running = 1")
    public TaskEntity getCurrentTask();
}
