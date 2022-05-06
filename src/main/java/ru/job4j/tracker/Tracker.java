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

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
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

    public boolean replace(int id, Item item) {
        boolean rsl = false;
        int index = indexOf(id);
        int n = items[index].getId();
        items[index] = item;
        items[index].setId(n);
        return item.getId() == id;
    }
}
