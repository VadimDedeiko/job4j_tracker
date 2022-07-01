package ru.job4j.stream;

import java.util.stream.Stream;

public class ЕxeCard {
    public static void main(String[] args) {
        Stream.of(Value.values())
                .flatMap(value1 -> Stream.of(Suit.values())
                        .map(suit1 -> new Card(suit1, value1)))
                .forEach(System.out::println);
    }
}
