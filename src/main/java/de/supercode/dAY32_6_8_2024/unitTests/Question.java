package de.supercode.dAY32_6_8_2024.unitTests;
// User
// Quiz - Georgahie Quiz
// questions[]
// Question - description: "Was ist die Hauptstadt von Luxemburg"
// choices[] ={"Luxemburg","Angola","Berlin","NRW",}
// correctChoices[]={"Luxemburg"}
// AnswerTry -chosen[]= {"Luxemburg"}

import java.util.Arrays;
import java.util.List;

public class Question {
    private String description;
    private String[] choices;
    private String[] correctChoices;

    public Question(String description, String[] choices, String[] correctChoices) {
        this.setDescription(description);
        this.setChoices(choices);
        this.setCorrectChoices(correctChoices);
    }

    public boolean answerQuestion(String[] chosen) {
        if (chosen.length != correctChoices.length) {
            return false;
        }
        for (String corrctChoice : correctChoices) {
            if (List.of(chosen).indexOf(corrctChoice) < 0) {
                return false;
            }
        }
        return true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() < 0 || !description.endsWith("?")) {
            throw new IllegalArgumentException("Question description invalid");
        }
        this.description = description;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        if (choices.length < 2) {
            throw new IllegalArgumentException("At least two choices must be provided");
        }
        this.choices = choices;
    }

    public String[] getCorrectChoices() {
        return correctChoices;
    }

    public void setCorrectChoices(String[] correctChoices) {
        if (correctChoices.length < 1 || hasUnknowChoice(this.choices, correctChoices)) {
            throw new IllegalArgumentException("Correct choices must be a subset of choices");
        }
        this.correctChoices = correctChoices;
    }

    private boolean hasUnknowChoice(String[] choices, String[] correctChoices) {
        boolean[] foundMismatch = {false};
        Arrays.stream(correctChoices).forEach(corrctChoice -> {
            if (List.of(choices).indexOf(corrctChoice) < 0) {
                foundMismatch[0] = true;
            }
        });
        boolean result = foundMismatch[0];
        return result;
        // oder:
//        boolean result = Arrays.stream(correctChoices).anyMatch(corrctChoice -> List.of(choices).indexOf(corrctChoice) < 0);
//        return result;

        // oder:
//        for (String corrctChoice : correctChoices) {
//            if (List.of(choices).indexOf(corrctChoice) < 0) {
//                return true;
//            }
//        }
//        return false;

        // anyMatch
      /*  List<String> list = Arrays.asList("apple", "banana", "cherry");
        boolean result = list.stream().anyMatch(s -> s.startsWith("b"));
        System.out.println(result); // 输出 true*/
    }
}
