package de.supercode.dAY31_5_8_2024.unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankkontoTest {
    private Bankkonto bankkontoA;
    private Bankkonto bankkontoB;

    @BeforeEach
    void SetUp() {
        bankkontoA = new Bankkonto("Mr. A", "1234");
        bankkontoB = new Bankkonto("Mr. B", "5678");
    }

    @Test
    void getKontoinhaber() {
        assertEquals("Mr. A", bankkontoA.getKontoinhaber());
    }

    @Test
    void setKontoinhaber() {
        bankkontoA.setKontoinhaber("Mr. C");
        assertEquals("Mr. C", bankkontoA.getKontoinhaber());
    }

    @Test
    void getKontonummer() {
        assertEquals("1234", bankkontoA.getKontonummer());
    }

    @Test
    void setKontonummer() {
        bankkontoA.setKontonummer("1255");
        assertEquals("1255", bankkontoA.getKontonummer());
    }

    @Test
    void getKontostand() {
        bankkontoA.einzahlen(100.9);
        assertEquals(100.9, bankkontoA.getKontostand());
    }

    @Test
    void setKontostand() {
        bankkontoA.setKontostand(200.9);
        assertEquals(200.9, bankkontoA.getKontostand());
    }

    @Test
    void einzahlen() {
        bankkontoA.einzahlen(200.9);
        assertEquals(200.9, bankkontoA.getKontostand());
    }

    @Test
    void einzahlenNegativBetrag() {
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            bankkontoA.einzahlen(-200.9);
        });
    }

    @Test
    void einzahlenNegativBetrag2() {
        try {
            bankkontoA.einzahlen(-200.9);
            fail("Erwartete IllegalArgumentException wurde nicht geworfen");
        } catch (IllegalArgumentException e) {
            // Der Test ist erfolgreich, da die erwartete Ausnahme ausgelöst wurde
        }

    }

    @Test
    void abheben() {
        bankkontoA.einzahlen(200.9);
        bankkontoA.abheben(100);
        assertEquals(100.9, bankkontoA.getKontostand());
    }

    @Test
    void abhebenNagativUndUeberschreitet() {
        bankkontoA.einzahlen(100);
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            bankkontoA.abheben(-100);
        });
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            bankkontoA.abheben(200);
        });
    }

//    @Test
//    void gibKontoinformationen() {
//    }

    @Test
    void ueberweisen() {
        bankkontoA.einzahlen(200.9);
        bankkontoA.ueberweisen(bankkontoB, 100);
        assertEquals(100.9, bankkontoA.getKontostand());
        assertEquals(100, bankkontoB.getKontostand());
    }

    @Test
    void ueberweisenNegativUeberschreitetUndZielKontoNull() {
        bankkontoA.einzahlen(100);

        // Testen, ob ein negativer Betrag eine Ausnahme wirft
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            bankkontoA.ueberweisen(bankkontoB, -100);
        });

        // Testen, ob das Überschreiten des Saldos eine Ausnahme wirft
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            bankkontoA.ueberweisen(bankkontoB, 200);
        });

        // Testen, ob das Überweisen auf ein Konto mit null Kontonummer eine Ausnahme wirft
        bankkontoB.setKontonummer(null);
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            bankkontoA.ueberweisen(bankkontoB, 10);
        });
    }

//    @Test
//    void printTransaktionshistorien() {
//
//    }
}