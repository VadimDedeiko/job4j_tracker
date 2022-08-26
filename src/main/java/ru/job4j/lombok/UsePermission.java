package ru.job4j.lombok;

import java.util.List;

public class UsePermission {
    public static void main(String[] args) {
        Permission permission = Permission.of()
                .id(1)
                .name("Permission")
                .permissions("list_element1")
                .permissions("list_element2")
                .permissions("list_element3")
                .permissions("list_element4")
                .withoutSingular(List.of("without1", "without2"))
                .build();
        System.out.println(permission);
    }
}
