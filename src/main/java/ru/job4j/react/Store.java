package ru.job4j.react;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<String> data = List.of("first", "second", "third");

    public void getByReact(Observe<String> observe) throws InterruptedException {
        for (String datum : data) {
            Thread.sleep(1000);
            observe.receive(datum);
        }
    }

    public List<String> get() throws InterruptedException {
        List<String> rsl = new ArrayList<>();
        for (String datum : data) {
            Thread.sleep(1000);
            rsl.add(datum);
        }
        return rsl;
    }

    public static void main(String[] args) {
        var store = new Store();
        List<String> data = null;
        try {
            data = store.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String datum : data) {
            System.out.println(datum);
        }

        try {
            store.getByReact(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}