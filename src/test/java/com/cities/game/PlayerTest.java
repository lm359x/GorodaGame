package com.cities.game;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private final Player player = Mockito.mock(Player.class);

    @Test
    void inputCity() {
        Mockito.when(player.inputCity()).thenReturn("Москва");
        assertEquals("Москва", player.inputCity());
    }
}