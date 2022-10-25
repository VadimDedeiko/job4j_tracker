package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.persistence.Store;

public interface UserAction {
    String name();

    boolean execute(Input input, Store tracker);
}