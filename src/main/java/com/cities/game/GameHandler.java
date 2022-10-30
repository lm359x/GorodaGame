package com.cities.game;

import java.util.Locale;
import java.util.Objects;
import java.util.Set;

public class GameHandler {

    private final Set<String> cities;
    private final Player player1;
    private final  Player player2;
    private final CountdownTimer countdownTimer;
    private String lastCityEntered;

    public GameHandler(Set<String> cities, Player player1, Player player2) {
        this.cities = cities;
        this.player1 = player1;
        this.player2 = player2;
        this.countdownTimer = new CountdownTimer();
    }

    public boolean verifyCity(String city) {
        if (getCities().contains(city)) {
            char letterToCheck = city.charAt(0);
            if (Objects.nonNull(lastCityEntered)) {
                letterToCheck = lastCityEntered.toUpperCase(Locale.ROOT).charAt(lastCityEntered.length() - 1);
                if (letterToCheck == 'Ь') {
                    letterToCheck = lastCityEntered.toUpperCase(Locale.ROOT).charAt(lastCityEntered.length() - 2);
                }
            }
            return Objects.equals(city.charAt(0), letterToCheck);
        } else {
            return false;
        }
    }

    public boolean removeCity(String input) {
        return getCities().remove(input);
    }

    public void startTheGame() {
        if (Objects.isNull(player1) || Objects.isNull(player2)) {
            throw new IllegalArgumentException("players are not specified");
        }
        if (Objects.isNull(cities)) {
            throw new IllegalArgumentException("cities are not specified");
        }

        while(cities.size() > 0) {
            if (Objects.equals(handlePlayerAnswer(player1), false)) {
                announceLoser(player1);
                return;
            }
            System.out.println("=====================================================================================");
            if (Objects.equals(handlePlayerAnswer(player2), false)) {
                announceLoser(player2);
                return;
            }
            System.out.println("=====================================================================================");
        }
    }

    private boolean handlePlayerAnswer(Player player) {
        countdownTimer.startCountdown(player);

        String city = "";

        while (!verifyCity(city)) {
            System.out.println(player.getName() + ": ");
            city = player.inputCity();

            if (countdownTimer.isTimeOver()) {
                return false;
            }
        }

        countdownTimer.stopCountdown();
        this.lastCityEntered = city;
        removeCity(city);
        return true;
    }

    public void announceLoser(Player player) {
        System.out.println("Время вышло! " + player.getName() + " проиграл(а)!");
    }

    public Set<String> getCities() {
        return cities;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setLastCityEntered(String lastCityEntered) {
        this.lastCityEntered = lastCityEntered;
    }
}
