package de.supercode.dAY29_1_8_2024.superheldenComparator;

import java.util.Comparator;

public class SuperheldenComparator {
    private String name;
    private int powerLevel;
    private int speed;
    private Category category;

    public SuperheldenComparator(String name, int powerLevel, int speed, Category category) {
        this.name = name;
        this.powerLevel = powerLevel;
        this.speed = speed;
        this.category = category;
    }

    /* public static Comparator<SuperheldenComparator> powerlevelComparator = new Comparator<SuperheldenComparator>() {
         @Override
         public int compare(SuperheldenComparator o1, SuperheldenComparator o2) {
             return Integer.compare(o1.getPowerLevel(), o2.getPowerLevel());
         }
     };*/
    /*public static Comparator<SuperheldenComparator> powerlevelComparator =
            ((o1, o2) -> Integer.compare(o1.getPowerLevel(), o2.getPowerLevel()));*/
    public static Comparator<SuperheldenComparator> powerlevelComparator =
            Comparator.comparingInt(SuperheldenComparator::getPowerLevel);

    public static Comparator<SuperheldenComparator> speedComparator =
            Comparator.comparingInt(SuperheldenComparator::getSpeed);
    public static Comparator<SuperheldenComparator> nameComparator =
            Comparator.comparing(SuperheldenComparator::getName, String.CASE_INSENSITIVE_ORDER);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "SuperheldenComparator{" +
                "name='" + name + '\'' +
                ", powerLevel=" + powerLevel +
                ", speed=" + speed +
                ", category=" + category +
                '}';
    }
}
/*
public static Comparator<SuperheldenComparator> powerlevelComparator =
        Comparator.comparingInt(SuperheldenComparator::getPowerLevel);
这个代码使用了Comparator.comparingInt方法，它接收一个ToIntFunction（一个返回int类型的函数）作为参数。在这里，SuperheldenComparator::getPowerLevel是一个方法引用，表示getPowerLevel方法。

这个转换的主要原因是方法引用使代码更简洁和可读。方法引用是Java 8引入的特性，它可以将某些特定形式的Lambda表达式简化成更紧凑的形式。*/

/*
String.CASE_INSENSITIVE_ORDER是一个Comparator<String>，它用于按照字母顺序忽略大小写进行比较。Comparator.comparing方法允许你传递一个函数，该函数用于从对象中提取用于比较的键，同时也允许传递一个自定义的Comparator来比较这些键。

当你使用Comparator.comparing并传递两个参数时，第一个参数是一个从对象中提取比较键的函数，第二个参数是一个用于比较这些键的Comparator。在这种情况下，String.CASE_INSENSITIVE_ORDER作为第二个参数被传递，用于比较从SuperheldenComparator对象中提取的名称字符串。

具体代码如下：

java
Code kopieren
public static Comparator<SuperheldenComparator> nameComparator =
        Comparator.comparing(SuperheldenComparator::getName, String.CASE_INSENSITIVE_ORDER);
在这里：

SuperheldenComparator::getName 是一个方法引用，用于从 SuperheldenComparator 对象中提取名称。
String.CASE_INSENSITIVE_ORDER 是一个 Comparator<String>，它按字母顺序忽略大小写进行比较。
这样，当比较两个 SuperheldenComparator 对象时，比较器会先调用 getName 方法提取名称，然后使用 String.CASE_INSENSITIVE_ORDER 比较这些名称，从而实现忽略大小写的排序。

如果没有提供自定义的Comparator，Comparator.comparing默认会使用键类型的自然顺序（对于字符串来说是按字母顺序区分大小写）。通过传递String.CASE_INSENSITIVE_ORDER，你可以定制比较行为，使其忽略大小写。*/
