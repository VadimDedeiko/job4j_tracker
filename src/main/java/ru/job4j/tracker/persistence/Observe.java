package ru.job4j.tracker.persistence;

public interface Observe<T> {
    void receive(T model);
}