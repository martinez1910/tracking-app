package com.abamobile.empleodigital.trackingapp.databases;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Entity that models Shift in the database
 * @see com.abamobile.empleodigital.trackingapp.logic.Shift
 */
@Entity(tableName = "shift")
public class ShiftEntity {

    @PrimaryKey
    private long timestamp;
    private long t_start;
    private long t_stop;
    private long t_elapsed;
    private boolean finished;
    private boolean running;

    public long getTimestamp() {
        return timestamp;
    }

    public long getT_start() {
        return t_start;
    }

    public long getT_stop() {
        return t_stop;
    }

    public long getT_elapsed() {
        return t_elapsed;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isRunning() {
        return running;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setT_start(long t_start) {
        this.t_start = t_start;
    }

    public void setT_stop(long t_stop) {
        this.t_stop = t_stop;
    }

    public void setT_elapsed(long t_elapsed) {
        this.t_elapsed = t_elapsed;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
