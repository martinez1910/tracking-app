package com.abamobile.empleodigital.trackingapp.logic;

import java.util.Date;

/**
 *  Task that has a starting, finishing time, name and a color-related attribute
 *  @version 1.0
 *  @see ICounter
 */
public class Task implements ICounter {
    private long timestamp;
    private Counter counter;
    private String name;
    private int color;
    private boolean finished = false;


    /**
     * Basic constructor with essential parts to work
     */
    public Task() {
        timestamp = System.currentTimeMillis();
        counter = new Counter();
    }

    /**
     * Main constructor
     * @param name task's name
     * @param color task's related color
     */
    public Task(String name, int color){
        this();
        this.name = name;
        this.color = color;
    }


    /**
     * Returns the color related to the task
     * @return color related to the task
     */
    public int getColor() {
        return color;
    }

    /**
     * Returns the stopwatch that handles task's times. It contains all the information related to time handling.
     * @return stopwatch with shift's times
     * @see Counter
     */
    @Override
    public Counter getCounter() {
        return counter;
    }

    /**
     * Returns the name of the task
     * @return name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the timestamp at which the task was created
     * @return timestamp at which the task was created
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Returns if the task has finished
     * @return true if the task has finished, false otherwise
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Sets the color related to the task
     * @param color new color related to the task
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Sets task's stopwatch
     * @param counter new stopwatch
     * @see Counter
     */
    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    /**
     * Sets if the shift has finished
     * @param finished true if the shift has finished, false otherwise
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Sets the name of the task
     * @param name new name of the task
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the timestamp at which the task was created
     * @param timestamp new timestamp at which the task was created
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the time at which the task was started as a date
     * @return time at which the task was created
     * @see Date
     */
    public Date startDate() {
        Date date = new Date(counter.getStartTime());
        return date;
    }

    /**
     * Starts the task
     */
    public void startTask() {
        counter.start();
    }

    /**
     * Return the time at which the task was finished as a date
     * @return time at which the task was finished
     * @see Date
     */
    public Date stopDate() {
        Date date = new Date(counter.getStopTime());
        return date;
    }

    /**
     * Stops the task
     */
    public void stopTask() {
        counter.stop();
        finished = true;
    }












}
