package ru.job4j.oop;

public class Student {

     public void music() {
        System.out.println("Tra tra tra");
    }

    public void song() {
        System.out.println("I believe I can fly");
    }

    public static void main(String[] args) {
         Student petya = new Student();
        System.out.println("Петя сыграй на баяне и три раза пропой");
        petya.song();
        petya.song();
        petya.song();
        petya.music();
        petya.music();
        petya.music();
    }
}