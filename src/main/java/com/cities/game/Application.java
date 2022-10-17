package com.cities.game;

import java.util.Set;

public class Application {
    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        Set<String> cities = DatabaseLoader.loadDatabase();
        GameHandler gameHandler = new GameHandler(cities);

        /*while (true) {
            String city = player1.inputCity();

        }*/
    }
}
