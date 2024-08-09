package de.supercode.dAY32_6_8_2024.unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class QuizTest {

    private Quiz quiz;
    private User user;
    private List<Question> questions;

    @BeforeEach
    void setUp() {
        user = new User("User1");
        questions = Arrays.asList(
                new Question("What is 2+2?", new String[]{"3", "4", "5"}, new String[]{"4"}),
                new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris"}, new String[]{"Paris"})
        );
        quiz = new Quiz(new ArrayList<>(questions));
    }

    @Test
    void getQuestions() {
        assertEquals(questions, quiz.getQuestions());
    }

    @Test
    void addQuestion() {
        Question newQuestion = new Question("What is 3+3?", new String[]{"5", "6", "7"}, new String[]{"6"});
        quiz.addQuestion(newQuestion);
        assertTrue(quiz.getQuestions().contains(newQuestion));
    }

    @Test
    void askQuestions() {
        // Simulate user input for testing askQuestions method
        // Simulating user selecting the second choice for both questions (4 for the first question, Paris for the second question)
        String simulatedInput = "2\n3\n";
        // Mock the Scanner to return the simulated input
        System.setIn(new java.io.ByteArrayInputStream(simulatedInput.getBytes()));
        int correctAnswers = quiz.askQuestions(user);
        assertEquals(2, correctAnswers);  // Check if two answers are correct
        assertEquals(2, user.getScore()); // Check if user's score is updated correctly
    }
}