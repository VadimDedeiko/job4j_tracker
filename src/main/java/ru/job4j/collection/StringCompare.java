package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rsl = 0;
        char[] leftChars = left.toCharArray();
        char[] rightChars = right.toCharArray();
        int length = Math.min(leftChars.length, rightChars.length);
        for (int i = 0; i < length; i++) {
            rsl = Character.compare(leftChars[i], rightChars[i]);
        }
        return rsl != 0 ? rsl : Integer.compare(leftChars.length, rightChars.length);
    }
}