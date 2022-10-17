package com.cities.game;

import java.util.Set;

public class GameHandler {

    private final Set<String> cities;
    public GameHandler(Set<String> cities) {
        this.cities = cities;
    }

    public boolean checkInput(String input) {
        return getCities().contains(input);
    }

    public boolean removeCity(String input) {
        return getCities().remove(input);
    }

    public Set<String> getCities() {
        return cities;
    }
}
