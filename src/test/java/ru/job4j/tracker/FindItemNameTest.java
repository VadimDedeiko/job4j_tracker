package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.actions.FindItemName;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;
import ru.job4j.tracker.persistence.MemTracker;
import ru.job4j.tracker.persistence.Store;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindItemNameTest {
    @Test
    public void execute() {

        LocalDateTime created = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Item1"));
        tracker.add(new Item("Item2"));
        FindItemName findItemName = new FindItemName(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("Item1");
        findItemName.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ===" + ln + "Item{id=1, name='Item1', created="
                + created.format(formatter) + "}" + ln));
    }
}