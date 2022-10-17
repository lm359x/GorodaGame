package com.cities.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DatabaseLoader {

    public static Set<String> loadDatabase() {

        Set<String> cities = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/city.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                cities.add(values[3].replaceAll("\"", ""));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }
}
