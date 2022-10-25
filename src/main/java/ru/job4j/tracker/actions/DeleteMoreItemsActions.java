package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.persistence.Store;

public class DeleteMoreItemsActions implements UserAction {
    private final Output out;

    public DeleteMoreItemsActions(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete many items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete item ===");
        int start = input.askInt("Enter numbers Items for deleting -> ");
            for (int i = 1; i <= start; i++) {
                tracker.delete(i);
            }
        return true;
    }
}
