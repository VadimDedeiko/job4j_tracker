package ru.job4j.stream;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {

    public static double averageScore(Stream<Pupil> stream) {
        return stream
                .flatMap(e -> e.getSubjects()
                        .stream())
                .mapToInt(a -> a.getScore())
                .average()
                .orElse(0.0);
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
                .map(o -> new Tuple(o.getName(), o.getSubjects()
                        .stream()
                        .mapToInt(s -> s.getScore())
                        .average()
                        .orElse(0.0)))
                .collect(Collectors.toList());
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream.flatMap(e -> e.getSubjects().stream())
                .collect(Collectors
                        .groupingBy(
                                s -> s.getName(),
                                LinkedHashMap::new,
                                Collectors.averagingDouble(Subject::getScore)
                        ))
                .entrySet().stream()
                .map(p -> new Tuple(p.getKey(), p.getValue()))
                .collect(Collectors.toList());
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream
                .map(o -> new Tuple(o.getName(), o.getSubjects()
                        .stream()
                        .mapToInt(s -> s.getScore()).sum()))
                .max((o1, o2) -> o1.compareTo(o2))
                .orElse(null);
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream.flatMap(e -> e.getSubjects().stream())
                .collect(
                        Collectors.groupingBy(
                                Subject::getName,
                                LinkedHashMap::new,
                                Collectors.summingDouble(Subject::getScore)
                        ))
                .entrySet()
                .stream()
                .map(p -> new Tuple(p.getKey(), p.getValue()))
                .max((o1, o2) -> o1.compareTo(o2))
                .orElse(null);
    }
}