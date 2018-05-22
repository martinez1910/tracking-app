package com.abamobile.empleodigital.trackingapp.logic;

import java.util.Date;

/**
 * Working shift that has a starting and finishing time
 * @version 1.0
 * @see ICounter
 */
public class Shift implements ICounter{
    private long timestamp;
    private Counter counter;
    private boolean finished = false;


    /**
     * Main constructor
     */
    public Shift() {
        timestamp = System.currentTimeMillis();
        counter = new Counter();
    }


    /**
     * Returns the stopwatch that handles shift's times. It contains all the information related to time handling.
     * @return stopwatch with shift's times
     * @see Counter
     */
    @Override
    public Counter getCounter() {
        return counter;
    }

    /**
     * Returns the timestamp at which the shift was created
     * @return timestamp at which the shift was created
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Returns if the shift has finished
     * @return true if the shift has finished, false otherwise
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Sets shift's stopwatch
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
     * Sets the timestamp at which the shift was created
     * @param timestamp new timestamp at which the shift was created
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Starts the shift
     */
    public void startShift() {
        counter.start();
    }

    /**
     * Finishes the shift
     */
    public void stopShift() {
        counter.stop();
        finished = true;
    }

    /**
     * Returns the time at which the shift was started as a date
     * @return time at which the shift was created
     * @see Date
     */
    public Date startDate(){
        Date date = new Date(counter.getStartTime());
        return date;
    }

    /**
     * Return the time at which the shift was finished as a date
     * @return time at which the shift was finished
     * @see Date
     */
    public Date stopDate(){
        Date date = new Date(counter.getStopTime());
        return date;
    }
}
