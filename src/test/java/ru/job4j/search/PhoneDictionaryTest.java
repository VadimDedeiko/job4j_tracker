package ru.job4j.search;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var persons = phones.find("Petr");
        assertThat(persons.get(0).getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindNobody() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var persons = phones.find("Vadim");
        assertThat(persons, IsEmptyCollection.empty());
    }
}