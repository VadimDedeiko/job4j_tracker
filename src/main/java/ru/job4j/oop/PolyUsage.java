package ru.job4j.oop;

public class PolyUsage {
    public static void main(String[] args) {
        Vehicle airplane = new Airplane();
        Vehicle airplane2 = new Airplane();
        Vehicle bus = new Bus();
        Vehicle bus2 = new Bus();
        Vehicle railway = new Railway();
        Vehicle railway2 = new Railway();
        Vehicle[] vehicle = new Vehicle[]{airplane, airplane2, bus, bus2, railway, railway2};
        for (Vehicle v : vehicle) {
            v.motor();
            v.move();
        }
    }
}