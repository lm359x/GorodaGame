package com.cities.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameHandlerTest {

    private GameHandler gameHandler;

    @BeforeEach
    public void init() {
        Set<String> cities = Stream.of("Москва").collect(Collectors.toCollection(HashSet::new));
        Player player1 = new Player("Player #1");
        Player player2 = new Player("Player #2");

        gameHandler = new GameHandler(cities, Mockito.mock(Player.class), Mockito.mock(Player.class));
    }

    @Test
    void verifyCity_correctCityPassed_TrueReturned() {
        gameHandler.setLastCityEntered("Амстердам");
        assertTrue(gameHandler.verifyCity("Москва"));
    }

    @Test
    void verifyCity_lastCityEnteredHasIllegalLastLetter_TrueReturned() {
        gameHandler.setLastCityEntered("Анадырь");
        gameHandler.getCities().add("Рыбинск");
        assertTrue(gameHandler.verifyCity("Рыбинск"));
    }

    @Test
    void verifyCity_invalidCityPassed_FalseReturned() {
        gameHandler.setLastCityEntered("Амстердам");
        assertFalse(gameHandler.verifyCity("Санкт-Петербург"));
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

    @Test
    void startTheGame_normalGameSimulated_firstPlayerIsLoser() {
        Mockito.when(gameHandler.getPlayer1().inputCity()).then((invocationOnMock) -> {
            Thread.sleep(15000);
            return "";
        });
        Mockito.when(gameHandler.getPlayer2().inputCity()).thenReturn("Архангельск");

        assertThrows(RuntimeException.class, () -> gameHandler.startTheGame());
        Mockito.verify(gameHandler).announceLoser(gameHandler.getPlayer2());
    }
}