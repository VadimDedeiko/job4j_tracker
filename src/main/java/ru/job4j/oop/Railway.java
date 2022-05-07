package ru.job4j.oop;

public class Railway implements Vehicle {

    @Override
    public void move() {
        System.out.println("Railway moves by rails");
    }

    @Override
    public void motor() {
        System.out.println("Railway has disel");
    }
}
