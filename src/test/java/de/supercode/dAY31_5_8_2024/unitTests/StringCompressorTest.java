package de.supercode.dAY31_5_8_2024.unitTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;

class StringCompressorTest {

    private StringCompressor stringCompressor;

    @BeforeEach
    void setUp() {
        stringCompressor = new StringCompressor();
    }

    @Test
    void stringCompressorIntputEmptyString() {
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            stringCompressor.compress("");
        });
    }

    @Test
    void stringCompressorIntputLongerOutput() {
        String input = "aab";
        assertEquals("a2b", stringCompressor.compress(input));
    }


    @Test
    void stringCompressorIntputAllSameChar() {
        assertEquals("a10", stringCompressor.compress("aaaaaaaaaa"));
    }

    @Test
    void stringCompressorIntputSingleChar() {
        assertEquals("a", stringCompressor.compress("a"));
    }


    @Test
    void stringCompressorIntputNoCompression() {
        assertEquals("abc", stringCompressor.compress("abc"));
    }


    @Test
    void stringCompressorIntputNull() {
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            stringCompressor.compress(null);
        });
    }

    @Test
    void stringCompressorIntputaaabbbcccaaaOutputa3b3c3a3() {
        assertEquals("a3b3c3a3", stringCompressor.compress("aaabbbcccaaa"));
    }

    @Test
    void stringCompressorIntputaabcccccaaaOutputa2bc5a3() {
        assertEquals("a3b3c3a3", stringCompressor.compress("aaabbbcccaaa"));
    }

    @ParameterizedTest
    @CsvSource({
            "abc, abc",
            "aaabbbcccaaa, a3b3c3a3",
            "aabcccccaaa, a2bc5a3",
            "aab, a2b"
    })
    public void testStringCompressor(String input, String expected) {
        assertEquals(expected, stringCompressor.compress(input));
    }


}