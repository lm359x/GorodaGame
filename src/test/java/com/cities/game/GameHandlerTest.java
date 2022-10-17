package com.cities.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

class GameHandlerTest {

    private static final GameHandler gameHandler = new GameHandler(new HashSet<>());

    @BeforeEach
    public void initCities() {
        gameHandler.getCities().clear();
        gameHandler.getCities().add("Москва");
    }

    @Test
    void checkInput_correctCityPassed_TrueReturned() {
        assertTrue(gameHandler.checkInput("Москва"));
    }

    @Test
    void checkInput_invalidCityPassed_FalseReturned() {
        assertFalse(gameHandler.checkInput("Санкт-Петербург"));
    }

    @Test
    void removeCity_correctCityPassedForRemoval_TrueReturned() {
        assertTrue(gameHandler.removeCity("Москва"));
    }
}