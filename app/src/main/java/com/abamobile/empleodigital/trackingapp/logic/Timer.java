package com.abamobile.empleodigital.trackingapp.logic;

import android.os.Handler;
import android.os.Looper;

import com.abamobile.empleodigital.trackingapp.design.interfaces.TimerListener;

/**
 * Class that handles the timers implemented in the UI.
 * Receives an ICounter and a TimeListener
 * @version 1.0
 * @see ICounter
 * @see TimerListener
 */
public class Timer {
    private boolean running = false;
    private ICounter runningICounter;
    private TimerListener listener;
    private Handler handler;

    /**
     * Main constructor
     * @param listener object waiting for the updated elapsed time
     */
    public Timer(TimerListener listener) {
        this.listener = listener;
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * Starts the timer
     * @param iCounter object containing the time handler
     */
    public void start(ICounter iCounter){
        this.runningICounter = iCounter;
        play();
    }

    /**
     * Stops the timer
     */
    public void stop(){
        running = false;
    }

    /**
     * Implements the starting instructions for the timer
     */
    private void play(){
        if(!running) {
            running = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (running) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(running){
                            runningICounter.getCounter().setElapsedTime(runningICounter.getCounter().getElapsedTime() + 1000);
                            updateListener();
                        }
                    }
                }
            }).start();
        }
    }

    /**
     * Returns the updated time to the listener
     */
    private void updateListener(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.updateElapsedTime(runningICounter.getCounter().getElapsedTime());
            }
        });
    }
}
