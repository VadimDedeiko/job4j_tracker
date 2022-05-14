package ru.job4j.tracker;

import org.junit.Test;

import javax.swing.plaf.basic.BasicDirectoryModel;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void compareToAscending() {
        List<Item> items = Arrays.asList(new Item("Sergey"),
                new Item("Anton"), new Item("Vadim"));
        Collections.sort(items, new ItemAscByName());
        List<Item> expected = Arrays.asList(new Item("Vadim"), new Item("Sergey"),
                new Item("Anton"));
        assertEquals(expected, items);
    }

    @Test
    public void compareToDescenting() {
        List<Item> items = Arrays.asList(new Item("Sergey"),
                new Item("Anton"), new Item("Vadim"));
        Collections.sort(items, Collections.reverseOrder());
        List<Item> expected = Arrays.asList(new Item("Vadim"), new Item("Sergey"),
                new Item("Anton"));
        assertEquals(expected, items);
    }
}