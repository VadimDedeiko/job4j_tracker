package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int a) {
        return a - x;
    }

    public int divide(int b) {
        return b / x;
    }

    public int sumAllOperation(int n) {
        return sum(n) + multiply(n) + minus(n) + divide(n);
    }

    public static void main(String[] args) {
        int result = Calculator.sum(10);
        System.out.println(result);
        Calculator calculator = new Calculator();
        sum(5);
        calculator.multiply(10);
        minus(10);
        calculator.divide(6);
        calculator.sumAllOperation(10);

    }
}