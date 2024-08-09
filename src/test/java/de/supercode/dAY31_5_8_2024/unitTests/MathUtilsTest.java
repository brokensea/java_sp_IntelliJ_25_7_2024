package de.supercode.dAY31_5_8_2024.unitTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    private MathUtils mathUtils;

    @BeforeEach
    void setUp() {
        //    Einmal vor jeder Testmethode ausführen, um das MathUtils-Objekt zu initialisieren
        mathUtils = new MathUtils();
    }


    @Test
    void maxZweiPositiv() {
        assertEquals(5, mathUtils.max(5, 4));
    }

    @Test
    void maxZweiNegativ() {
        assertEquals(-4, mathUtils.max(-5, -4));
    }


    @Test
    void minZweiPostiv() {
        assertEquals(4, mathUtils.min(5, 4));
    }

    @Test
    void minZweiNegativ() {
        assertEquals(-5, mathUtils.min(-5, -4));
    }
}