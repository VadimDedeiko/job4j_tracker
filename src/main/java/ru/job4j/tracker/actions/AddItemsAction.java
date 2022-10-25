package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.persistence.Store;

public class AddItemsAction implements UserAction {
    private final Output out;

    public AddItemsAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add many items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Create Many Items ===");
        int num = input.askInt("Enter numbers Items -> ");
        for (int i = 1; i <= num; i++) {
            String name = String.format("name%d", i);
            Item item = new Item(name);
            tracker.add(item);
        }
        return true;
    }
}
