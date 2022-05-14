package ru.job4j.function;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class SearchFolder {
    public static List<Folder> filter(List<Folder> list, Predicate<Folder> pred) {
        Comparator<String> cmpText = (left, right) -> left.compareTo(right);
        Comparator<String> cmpDescSize = (left, right) -> right.compareTo(left);
        List<Folder> lst = new ArrayList<>();
        for (Folder f : list) {
            if (pred.test(f)) {
                lst.add(f);
            }
        }
        return lst;
    }
}