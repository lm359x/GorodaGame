package com.cities.game;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseLoaderTest {

    private final DatabaseLoader databaseLoader = Mockito.mock(DatabaseLoader.class);

    @Test
    void loadDatabase() {
        File csvFile = new File("");
        databaseLoader.loadDatabase(csvFile);

        Mockito.verify(databaseLoader).loadDatabase(csvFile);
    }
}