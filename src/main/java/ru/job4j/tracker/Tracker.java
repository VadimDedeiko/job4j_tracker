package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }

    public Item[] findAll() {
        int size = 0;
        Item[] copyitems = new Item[items.length];
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                copyitems[size] = items[i];
                size++;
            }
        }
        return Arrays.copyOf(copyitems, size);
    }

    public Item[] findByName(String key) {
        int size = 0;
        Item[] copyitems = new Item[items.length];
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                break;
            } else if (key.equals(items[i].getName())) {
                copyitems[i] = items[i];
                size++;
            }
        }
        return Arrays.copyOf(copyitems, size);
    }
}