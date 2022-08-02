package ru.job4j.tracker;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindItemIdTest {
    @Test
    public void execute() {
        LocalDateTime created = LocalDateTime.now();
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Item"));
        FindItemId findItemId = new FindItemId(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        findItemId.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ===" + ln
                        + "Item{id=1, name='Item', created=" + created.format(FORMATTER) + "}" + ln));
        assertThat(tracker.findAll().get(0).getName(), is("Item"));
    }

}