package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tracker {
    //    private final Item[] items = new Item[100];
    List<Item> items = new ArrayList<>();
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
//        items[size++] = item;
        items.add(item);
        return item;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (Item item : items) {
            if ((item.getId() == id)) {
                rsl = items.indexOf(item);
                break;
            }
        }
        return rsl;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    public List<Item> findAll() {
//        return Arrays.copyOf(items, size);
        return items;
    }

    public List<Item> findByName(String key) {
//        int count = 0;
//        Item[] copyitems = new Item[size];
        List<Item> copyitems = new ArrayList<>();
        for (Item item : items) {
            if (key.equals(item.getName())) {
                copyitems.add(item);
            }
        }
        return copyitems;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
//            items[index] = item;
            items.set(index, item);
            item.setId(id);
        }
        return rsl;
    }

    public boolean delete(int id) {
        boolean rsl = false;
        int index = indexOf(id);
        if (index != -1) {
            items.remove(index);
        }
        return rsl;
    }
}