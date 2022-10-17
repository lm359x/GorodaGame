package com.cities.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DatabaseLoader {

    public Set<String> loadDatabase(File csvFile) {

        Set<String> cities = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("city.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                cities.add(values[3]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }
}
