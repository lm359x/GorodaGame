package com.cities.game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownTimer {

    private ExecutorService service;

    private boolean isTimeOver;

    public void startCountdown(Player player) {

        Runnable runnable = () -> {
            try {
                isTimeOver = false;
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                return;
            }

            isTimeOver = true;
        };

        service = Executors.newFixedThreadPool(1,
                threadFactory -> {
                    Thread t = Executors.defaultThreadFactory().newThread(threadFactory);
                    t.setDaemon(true);
                    return t;
                });
        service.execute(runnable);
    }

    public void stopCountdown() {
        service.shutdownNow();
    }

    public boolean isTimeOver() {
        return isTimeOver;
    }
}
