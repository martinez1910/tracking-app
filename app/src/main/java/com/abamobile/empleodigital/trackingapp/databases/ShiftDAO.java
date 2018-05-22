package com.abamobile.empleodigital.trackingapp.databases;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Implemented by the entity that models the shift in the database.
 */
@Dao
public interface ShiftDAO {

    /**
     * Inserts the given shift entity into the database. It's replaced if it's already in the database.
     * @param shiftEntity the shift to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertShift(ShiftEntity shiftEntity);

    /**
     * Returns every shift entity from the database
     * @return a list with every shift entity
     */
    @Query("SELECT * FROM shift")
    public List<ShiftEntity> getShifts();

    /**
     * Returns every finished shift entity from the database
     * @return a list with every finished shift entity
     */
    @Query("SELECT * FROM shift WHERE finished = 1")
    public List<ShiftEntity> getFinishedShifts();

    /**
     * Returns a shift entity given its id
     * @param timestamp the id of the shift
     * @return the shift entity
     */
    @Query("SELECT * FROM shift WHERE timestamp = :timestamp")
    public ShiftEntity getShiftById(long timestamp);

    /**
     * Returns the shift that has not been finished yet.
     * @return the shift entity
     */
    @Query("SELECT * FROM shift WHERE finished = 0")
    public ShiftEntity getCurrentShift();

    /**
     * Removes the given shift entity from the database
     * @param shiftEntity the shift entity to be removed
     */
    @Delete
    public void deleteShift(ShiftEntity shiftEntity);

    /**
     * Updates the given shift entity in the databse
     * @param shiftEntity the shift entity to be updated
     */
    @Update
    public void updateShift(ShiftEntity shiftEntity);
}
