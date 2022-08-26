package ru.job4j.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;
@Builder(builderMethodName = "of")
@ToString
public class Permission {
    private int id;
    private String name;
    @Singular("permissions")
    private List<String> permissions;
    private List<String> withoutSingular;
}