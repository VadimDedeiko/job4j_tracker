package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(new ArrayList<>(
                Arrays.asList("one", "1")));
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(new ArrayList<>(
                Arrays.asList("0")));
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(0));
    }

    @Test
    public void whenMuchValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(new ArrayList<>(
                Arrays.asList("0", "1", "2", "3")));
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(0));
        int select = input.askInt("Enter menu:");
        assertThat(select, is(1));
        int selection = input.askInt("Enter menu:");
        assertThat(selection, is(2));
    }

    @Test
    public void wheninvalidInputNegative() {
        Output out = new StubOutput();
        Input in = new StubInput(new ArrayList<>(
                Arrays.asList("-3")));
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(-3));
    }
}