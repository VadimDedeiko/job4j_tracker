package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {

    public static List<Address> collect(List<Profile> profiles) {
        List<Address> adress = new ArrayList<>();
        adress = profiles.stream()
                .map(s -> s.getAddress())
                .collect(Collectors.toList());
        return adress;
    }
}