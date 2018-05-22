package com.abamobile.empleodigital.trackingapp.logic;

/**
 * Stopwatch that cannot be paused
 * @version 1.0
 */
public class Counter{

    private long startTime;
    private long stopTime;
    private long elapsedTime;
    private boolean running = false;


    /**
     * Starts the stopwatch
     */
    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    /**
     * Stops the stopwatch
     */
    public void stop() {
        if (running) {
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            running = false;
        }
    }

    /**
     * Returns the elapsed time even if it's running
     * @return elapsed time
     */
    public long getCurrentTime() {
        if (running) {
            return elapsedTime + (System.currentTimeMillis() - startTime);
        }
        return elapsedTime;
    }

    /**
     * Returns the proper elapsed time when the stopwatch is stopped
     * @return 0 if it's running, the correct elapsed time otherwise
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Returns the time when the stopwatch was initiated
     * @return time when it was started
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Returns the time when the stopwatch was stopped
     * @return time when it was stopped
     */
    public long getStopTime() {
        return stopTime;
    }

    /**
     * Returns if the stopwatch is running
     * @return true if it's running, false otherwise
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets the elapsed time
     * @param elapsedTime new elapsed time
     */
    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * Sets if the stopwatch is running or not.
     * @param running true if it's running, false otherwise
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Sets the time when the stopwatch was initiated
     * @param startTime new time when the stopwatch was initiated
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the time when the stopwatch was stopped
     * @param stopTime new time when the stopwatch was stopped
     */
    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }
}
