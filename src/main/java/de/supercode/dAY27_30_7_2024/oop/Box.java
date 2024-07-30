package de.supercode.dAY27_30_7_2024.oop;

import java.util.ArrayList;
import java.util.List;

public class Box<E> {
    private E iteam;

    public Box(E iteam) {
        this.setIteam(iteam);
    }

    public E getIteam() {
        return iteam;
    }

    public void setIteam(E iteam) {
        this.iteam = iteam;
    }

    @Override
    public String toString() {
        return "Box{" +
                "iteam=" + iteam +
                '}';
    }
}
