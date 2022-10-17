package com.cities.game;

import java.util.Scanner;

public class Player {

    private String name;
    private final Scanner scanner = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
    }

    String inputCity() {
        return scanner.nextLine();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
