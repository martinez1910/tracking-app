package com.abamobile.empleodigital.trackingapp.databases;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Entity that models Task in the database
 * @see com.abamobile.empleodigital.trackingapp.logic.Task
 */
@Entity(tableName = "task", foreignKeys = @ForeignKey(entity = ShiftEntity.class, parentColumns = "timestamp", childColumns = "timestamp_shift", onDelete = CASCADE))
public class TaskEntity {
    @PrimaryKey
    private long timestamp;
    private long timestamp_shift;
    private long t_start;
    private long t_stop;
    private long t_elapsed;
    private boolean finished;
    private boolean running;
    private String name;
    private int color;

    public long getTimestamp() {
        return timestamp;
    }

    public long getTimestamp_shift() {
        return timestamp_shift;
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

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimestamp_shift(long timestamp_shift) {
        this.timestamp_shift = timestamp_shift;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
