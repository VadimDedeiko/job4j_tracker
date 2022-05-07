package ru.job4j.oop;

public class Bus implements Vehicle {
    @Override
    public void move() {
        System.out.println("Bus drives on roads");
    }

    @Override
    public void motor() {
        System.out.println("Bus has reciprocating motor");
    }
}
