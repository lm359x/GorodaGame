package com.cities.game;

import java.util.Scanner;

public class Player {

    private final Scanner scanner = new Scanner(System.in);

    String inputCity() {
        return scanner.nextLine();
    }
}
