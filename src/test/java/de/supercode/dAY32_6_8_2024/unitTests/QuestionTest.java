package de.supercode.dAY32_6_8_2024.unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    private Question question;

    @BeforeEach
    void setUp() {
        String[] choices = {"Luxemburg", "Hamburg", "NRW", "Berlin"};
        String[] correctChoices = {"Berlin"};
        question = new Question("Was ist die Hauptstadt von Deutschland?", choices, correctChoices);
    }

    /*@AfterEach
    void tearDown() {
    }*/

    @Test
    void answerQuestion() {
        String[] correctAnswer = {"Berlin"};
        String[] wrongAnswer = {"Hamburg"};
        String[] partialCorrectAnswer = {"Berlin", "Hamburg"};

        assertTrue(question.answerQuestion(correctAnswer), "The answer should be correct");
        assertFalse(question.answerQuestion(wrongAnswer), "The answer should be incorrect");
        assertFalse(question.answerQuestion(partialCorrectAnswer),
                "The answer should be incorrect due to additional wrong choice");

    }

    @Test
    void setDescription() {
        question.setDescription("Was ist die Hauptstadt von Deutschland?");
        assertEquals("Was ist die Hauptstadt von Deutschland?", question.getDescription());

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            question.setDescription("Invalid description");
        });

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            question.setDescription("Invalid description");
        });
        assertEquals("Question description invalid", thrown.getMessage());
    }

    @Test
    void setChoices() {
        String[] newChoices = {"A", "B"};
        question.setChoices(newChoices);
        assertArrayEquals(newChoices, question.getChoices());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            question.setChoices(new String[]{"OnlyOneChoice"});
        });
        assertEquals("At least two choices must be provided", thrown.getMessage());
    }

    @Test
    void setCorrectChoices() {
        String[] newCorrectChoices = {"Hamburg"};
        question.setCorrectChoices(newCorrectChoices);
        assertArrayEquals(newCorrectChoices, question.getCorrectChoices());

        IllegalArgumentException thrown =
                assertThrows(IllegalArgumentException.class,
                        () -> {
                            question.setCorrectChoices(new String[]{"Unknown"});
                        });
        assertEquals("Correct choices must be a subset of choices", thrown.getMessage());
    }

    // setup @Before und Teardown @After... z.B. DatenBank verbindung.


}