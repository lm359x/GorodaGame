package com.cities.game;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseLoaderTest {

    @Test
    void loadDatabase() {
        Set<String> cities = new HashSet<>();

        try (MockedStatic<DatabaseLoader> databaseLoaderMockedStatic = Mockito.mockStatic(DatabaseLoader.class)) {
            databaseLoaderMockedStatic.when(DatabaseLoader::loadDatabase).thenReturn(cities);

            assertEquals(cities, DatabaseLoader.loadDatabase());
        }
    }
}