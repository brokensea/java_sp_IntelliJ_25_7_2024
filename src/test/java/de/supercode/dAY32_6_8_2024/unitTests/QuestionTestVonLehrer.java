package de.supercode.dAY32_6_8_2024.unitTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuestionTestVonLehrer {
    private String question;
    private String[] choices;
    private String[] correctChoices;
    private String questionMultiple;
    private String[] correctChoicesMultiple;

    private Question deCapitalQuestion;
    private Question dePlacesQuestion;
    // @BeforeEach // methode mit @BeforeEach wird JEDES MAL ausgeführt bevor eine @Test-Methode ausgeführt wird
    // @BeforeAll  // methode mit @BeforeAll wird einmal ausgeführt bevor ALLE @Test-Methoden ausgeführt werden,,, @TestInstance(Lifecycle.PER_CLASS) nötig
    // Setup und Teardown (Setup => @Before..., Teardown => @After...)

    @BeforeAll
    void setupQuestionTestData() {
        this.question = "Was ist die Hauptstadt von Deutschland?";
        this.choices = new String[]{"Luxemburg", "Hamburg", "NRW", "Berlin"};
        this.correctChoices = new String[]{"Berlin"};

        this.questionMultiple = "Welches dieser Orte/Städe sich in Deutschland?";
        this.correctChoicesMultiple = new String[]{"Hamburg", "NRW", "Berlin"};

        this.deCapitalQuestion = new Question(this.question, this.choices, this.correctChoices);
        this.dePlacesQuestion = new Question(this.questionMultiple, this.choices, this.correctChoicesMultiple);
    }

    @Test
    void questionDescriptionMustBeValid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Question("", this.choices, this.correctChoices);
        });
    }

    @Test
    void atLeastTwoChoices() {
        String[] singleChoice = {"Berlin"};
        assertThrows(IllegalArgumentException.class, () -> {
            new Question(this.question, singleChoice, this.correctChoices);
        });
    }

    @Test
    void atLeastOneCorrectChoice() {
        String[] noCorrectChoice = {};
        assertThrows(IllegalArgumentException.class, () -> {
            new Question(this.question, this.choices, noCorrectChoice);
        });
    }

    @Test
    void allCorrectChoicesAreSubsetOfChoices() {
        String[] correctChoicesWithUnknown = {"Berlin", "Cheeseburg"};
        assertThrows(IllegalArgumentException.class, () -> {
            new Question(this.question, this.choices, correctChoicesWithUnknown);
        });
    }

    @Test
    void correctAnswerSingle() {
        String[] chosen = {"Berlin"};
        assertTrue(deCapitalQuestion.answerQuestion(chosen));
    }

    @Test
    void incorrectAnswerSingle() {
        String[] chosen = {"Hamburg"};
        assertFalse(deCapitalQuestion.answerQuestion(chosen));
    }

    @Test
    void correctAnswerMultiple() {
        String[] chosen = {"Hamburg", "NRW", "Berlin"};
        assertTrue(dePlacesQuestion.answerQuestion(chosen));
    }

    @Test
    void incorrectAnswerMultipleMissingOne() {
        String[] chosen = {"Hamburg", "NRW"};
        assertFalse(dePlacesQuestion.answerQuestion(chosen));
    }

    @Test
    void incorrectAnswerMultipleWrongExtras() {
        String[] chosen = {"Hamburg", "NRW", "Berlin", "Luxemburg"};
        assertFalse(dePlacesQuestion.answerQuestion(chosen));
    }


}