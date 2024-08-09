package de.supercode.dAY32_6_8_2024.unitTests;

import jdk.jshell.Snippet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Quiz {
    private List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }


    public int askQuestions(User user) {
        Scanner scanner = new Scanner(System.in);
        int correctAnswers = 0;


        // 将 questions 列表转换为一个 Stream，然后通过 mapToInt 方法转换为一个 IntStream
        correctAnswers = questions.stream()
                .mapToInt(question -> { // 对每个问题 question 进行操作，并返回一个 int 值
                    // 打印问题的描述
                    System.out.println(question.getDescription());
                    // 获取该问题的选项
                    String[] choices = question.getChoices();
                    // 打印选项
                    IntStream.range(0, choices.length).forEach(i -> System.out.println((i + 1) + ": " + choices[i]));
                    // 提示用户选择答案
                    System.out.println("Wählen Sie Ihre Antworten: ");
                    String input = scanner.nextLine();
                    // 解析用户输入的答案
                    String[] chosenChoices = parseInput(input, choices);
                    // 判断答案是否正确
                    if (question.answerQuestion(chosenChoices)) {
                        System.out.println("Correct ");
                        user.incrementScore(); // 增加用户得分
                        return 1; // 返回 1 表示该问题回答正确
                    } else {
                        System.out.println("False ");
                        return 0; // 返回 0 表示该问题回答错误
                    }
                })
                .sum(); // 将所有问题的得分累加起来，得到正确回答的总数
        return correctAnswers; // 返回正确回答的总数
    }



  /*  public int askQuesions(User user) {
        int correctAnswers = 0;
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            System.out.println(question.getDescription());
            String[] choices = question.getChoices();
            for (int i = 0; i < choices.length; i++) {
                System.out.println((i + 1) + ": " + choices[i]);
            }
            System.out.println("wählen Sie Ihre Antworten: ");
            String input = scanner.nextLine();
            String[] chosenChoices = parseInput(input, choices);
            if (question.answerQuestion(chosenChoices)) {
                System.out.println("Correct ");
                user.incrementsScore();
                correctAnswers++;
            } else {
                System.out.println("False ");
            }
        }
        return correctAnswers;
    }*/

    //    Stream API 将输入字符串转换为选项数组的。
    private String[] parseInput(String input, String[] choices) {
        // 将输入的字符串用逗号分隔成数组，然后将其转化为流
        return Arrays.stream(input.split(","))
                // 去掉每个分隔字符串的前后空格
                .map(String::trim)
                // 将每个字符串转化为整数，并减去 1 以转换为 0 基索引
                .mapToInt(index -> {
                    try {
                        // 尝试将字符串转换为整数，并减去 1
                        return Integer.parseInt(index) - 1;
                    } catch (NumberFormatException e) {
                        // 如果转换失败，捕获异常并打印错误信息，返回 -1 作为无效索引
                        System.out.println(e.getMessage());
                        return -1;
                    }
                })
                // 过滤掉无效的索引值（小于 0 或超出范围）
                .filter(index -> index >= 0 && index < choices.length)
                // 将有效的索引值映射到对应的选项
                .mapToObj(index -> choices[index])
                // 将结果收集到一个字符串数组中
                .toArray(String[]::new);
    }


/*
    private String[] parseInput(String input, String[] choices) {
        String[] indices = input.split(",");
        List<String> chonsenChoices = new ArrayList<>();
        for (String index : indices) {
            try {
                int index2 = Integer.parseInt(index.trim()) - 1;
                if (index2 >= 0 && index2 < choices.length) {
                    chonsenChoices.add(choices[index2]);
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println(numberFormatException.getMessage());
            }
        }
        return chonsenChoices.toArray(new String[0]);
    }*/
}

/*
.mapToObj(index -> choices[index]) 和 .toArray(String[]::new) 是 Java Stream API 中的两个关键操作，它们的作用是将索引映射到具体的选项，并将结果收集到一个数组中。我们通过逐步分析这两行代码来理解它们的具体功能。

1. .mapToObj(index -> choices[index])
作用：
将 IntStream 中的每个整数索引映射到 choices 数组中的对应元素，并生成一个 Stream<String>。
详细解释：
mapToObj 是 IntStream 的方法，它接受一个 IntFunction<R>，将每个整数元素映射为一个对象类型（这里是 String）。
index -> choices[index] 是一个 lambda 表达式，将每个整数索引映射到 choices 数组中的对应选项。
示例：
假设 choices 数组是 {"A", "B", "C"}，IntStream 是 [0, 1, 2]。

java
Code kopieren
IntStream.of(0, 1, 2)  // 生成一个 IntStream
    .mapToObj(index -> choices[index])  // 将每个索引映射到 choices 中对应的元素
    .forEach(System.out::println);  // 输出每个元素
输出：

css
Code kopieren
A
B
C
2. .toArray(String[]::new)
作用：
将 Stream<String> 中的元素收集到一个 String 数组中。
详细解释：
toArray 是 Stream 的终端操作，它将流中的元素收集到一个数组中。
String[]::new 是一个数组构造器引用，指定生成 String 类型的数组。
示例：
假设 Stream<String> 是 {"A", "B", "C"}。

java
Code kopieren
Stream.of("A", "B", "C")  // 生成一个 Stream<String>
    .toArray(String[]::new);  // 将流中的元素收集到一个 String 数组中
结果是一个 String 数组 {"A", "B", "C"}。

综合示例
结合上述两个操作，我们可以看出它们在 parseInput 方法中的具体功能：

java
Code kopieren
private String[] parseInput(String input, String[] choices) {
    return Arrays.stream(input.split(","))
                 .map(String::trim)
                 .mapToInt(index -> {
                     try {
                         return Integer.parseInt(index) - 1;
                     } catch (NumberFormatException e) {
                         System.out.println(e.getMessage());
                         return -1;
                     }
                 })
                 .filter(index -> index >= 0 && index < choices.length)
                 .mapToObj(index -> choices[index])  // 将有效的索引映射到对应的选项
                 .toArray(String[]::new);  // 将结果收集到一个 String 数组中
}
解释：
将输入字符串分割并修整：

Arrays.stream(input.split(",")).map(String::trim)：将输入字符串按逗号分割，去除每个子串的前后空格。
将字符串转换为索引并过滤无效索引：

.mapToInt(index -> { ... })：将字符串转换为整数索引。
.filter(index -> index >= 0 && index < choices.length)：过滤掉无效的索引。
将索引映射到选项并收集结果：

.mapToObj(index -> choices[index])：将有效的索引映射到对应的选项。
.toArray(String[]::new)：将结果收集到一个 String 数组中。
通过这些操作，parseInput 方法将用户的输入解析并转换为有效的选项数组。*/
