package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] firstString = o1.split("/");
        String[] secondString = o2.split("/");
        int num = secondString[0].compareTo(firstString[0]);
        if (num != 0) {
            return num;
        }
        return o1.compareTo(o2);
    }
}