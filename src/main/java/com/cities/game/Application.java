package com.cities.game;

import java.util.Set;

public class Application {
    public static void main(String[] args) {
        Player player1 = new Player("Player #1");
        Player player2 = new Player("Player #2");
        Set<String> cities = DatabaseLoader.loadDatabase();

        GameHandler gameHandler = new GameHandler(cities, player1, player2);

        Player winner = gameHandler.startTheGame();
        System.out.println("Игра окончена! В данном раунде одержал(а) победу " + winner.getName());
    }
}
