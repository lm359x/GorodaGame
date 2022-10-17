package com.cities.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameHandlerTest {

    private static GameHandler gameHandler;

    @BeforeEach
    public void initCities() {
        Set<String> cities = Stream.of("Москва").collect(Collectors.toCollection(HashSet::new));
        gameHandler = new GameHandler(cities, new Player(""), new Player(""));
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

    @Test
    void startTheGame_playersAreNull_IllegalArgumentExceptionThrown() {
        gameHandler = new GameHandler(new HashSet<>(), null, new Player(""));
        assertThrows(IllegalArgumentException.class, () -> gameHandler.startTheGame());

        gameHandler = new GameHandler(new HashSet<>(), new Player(""), null);
        assertThrows(IllegalArgumentException.class, () -> gameHandler.startTheGame());

        gameHandler = new GameHandler(new HashSet<>(), null, null);
        assertThrows(IllegalArgumentException.class, () -> gameHandler.startTheGame());
    }

    @Test
    void startTheGame_citiesAreNull_IllegalArgumentExceptionThrown() {
        gameHandler = new GameHandler(null, new Player(""), new Player(""));
        assertThrows(IllegalArgumentException.class, () -> gameHandler.startTheGame());
    }
}