package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     *
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public ArrayList<Person> find(String key) {
        Predicate<Person> combineName = (s) -> (s.getName().contains(key));
        Predicate<Person> combinePhone = (s) -> (s.getPhone().contains(key));
        Predicate<Person> combineSurname = (s) -> (s.getSurname().contains(key));
        Predicate<Person> combineAddress = (s) -> (s.getAddress().contains(key));
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combineName.or(combinePhone).or(combineSurname).or(combineAddress).test(person)) {
                result.add(person);
            }
        }
        return result;

    }
}
