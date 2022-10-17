package com.cities.game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownTimer {

    private ExecutorService service;
    private final GameHandler gameHandler;

    public CountdownTimer(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    public void startCountdown(Player player) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                return;
            }
            gameHandler.announceLoser(player);
        };

        service = Executors.newCachedThreadPool();
        service.execute(runnable);
    }

    public void stopCountdown() {
        service.shutdownNow();
    }
}
