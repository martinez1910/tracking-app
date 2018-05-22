package com.abamobile.empleodigital.trackingapp.databases;

import com.abamobile.empleodigital.trackingapp.logic.Counter;
import com.abamobile.empleodigital.trackingapp.logic.Shift;

/**
 * Mapper between a Shift (Java object) and a ShiftEntity (persistence library 'Room' object)
 * @see Shift
 * @see ShiftEntity
 */
public class ShiftMapper {

    public static ShiftEntity map(Shift shift){
        ShiftEntity shiftEntity = new ShiftEntity();

        shiftEntity.setTimestamp(shift.getTimestamp());
        shiftEntity.setFinished(shift.isFinished());
        shiftEntity.setT_start(shift.getCounter().getStartTime());
        shiftEntity.setT_stop(shift.getCounter().getStopTime());
        shiftEntity.setT_elapsed(shift.getCounter().getElapsedTime());
        shiftEntity.setRunning(shift.getCounter().isRunning());

        return shiftEntity;
    }

    public static Shift map(ShiftEntity shiftEntity){
        Shift shift = new Shift();

        shift.setTimestamp(shiftEntity.getTimestamp());
        shift.setFinished(shiftEntity.isFinished());
        Counter counter = new Counter();
        counter.setStartTime(shiftEntity.getT_start());
        counter.setStopTime(shiftEntity.getT_stop());
        counter.setElapsedTime(shiftEntity.getT_elapsed());
        counter.setRunning(shiftEntity.isRunning());
        shift.setCounter(counter);

        return shift;
    }
}
