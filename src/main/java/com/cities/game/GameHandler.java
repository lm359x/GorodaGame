package com.cities.game;

import java.util.Objects;
import java.util.Set;

public class GameHandler {

    private final Set<String> cities;
    private final Player player1;
    private final  Player player2;

    public GameHandler(Set<String> cities, Player player1, Player player2) {
        this.cities = cities;
        this.player1 = player1;
        this.player2 = player2;
    }

    public boolean checkInput(String input) {
        return getCities().contains(input);
    }

    public boolean removeCity(String input) {
        return getCities().remove(input);
    }

    public Player startTheGame() {
        if (Objects.isNull(player1) || Objects.isNull(player2)) {
            throw new IllegalArgumentException("players are not specified");
        }
        if (Objects.isNull(cities)) {
            throw new IllegalArgumentException("cities are not specified");
        }

        return new Player("");
    }

    public Set<String> getCities() {
        return cities;
    }
}
