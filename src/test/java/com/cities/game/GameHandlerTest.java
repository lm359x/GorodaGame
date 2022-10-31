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

    private final Set<String> cities = Stream.of("Москва", "Архангельск", "Калининград")
            .collect(Collectors.toCollection(HashSet::new));
    private final Player player1 = Mockito.spy(new Player("Player #1"));
    private final Player player2 = Mockito.spy(new Player("Player #2"));
    private GameHandler gameHandler;

    @BeforeEach
    public void init() {
        gameHandler = new GameHandler(cities, player1, player2);
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
    void startTheGame_correctAnswerIsGivenBeyondAvailableTime_secondPlayerIsLoser() {
        GameHandler spyOnGameHandler = Mockito.spy(gameHandler);
        Player player1 = spyOnGameHandler.getPlayer1();
        Player player2 = spyOnGameHandler.getPlayer2();

        Mockito.doReturn("Москва").when(player1).inputCity();
        Mockito.doAnswer(invocationOnMock -> {
            Thread.sleep(20000);
            return "Архангельск";
        }).when(player2).inputCity();

        spyOnGameHandler.startTheGame();
        Mockito.verify(spyOnGameHandler).announceLoser(player2);
    }

    @Test
    void startTheGame_correctAnswerIsNotGivenAtAll_firstPlayerIsLoser() {
        GameHandler spyOnGameHandler = Mockito.spy(gameHandler);
        Player player1 = spyOnGameHandler.getPlayer1();
        Player player2 = spyOnGameHandler.getPlayer2();

        Mockito.doAnswer(invocationOnMock -> {
            Thread.sleep(5000);
            return "Москва";
        }).when(player1).inputCity();

        Mockito.doReturn("Архангельск").when(player2).inputCity();
        //Mockito.doNothing().when(spyOnGameHandler).announceLoser(Mockito.any(Player.class));

        spyOnGameHandler.startTheGame();
        Mockito.verify(spyOnGameHandler).announceLoser(player1);
    }
}