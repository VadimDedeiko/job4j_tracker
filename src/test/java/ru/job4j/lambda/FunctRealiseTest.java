package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FunctRealiseTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        FunctRealise function = new FunctRealise();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenResults() {
        FunctRealise function = new FunctRealise();
        List<Double> result = function.diapason(5, 8, x -> (x * x) - 3);
        List<Double> expected = Arrays.asList(22D, 33D, 46D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExponentialFunctionThenResults() {
        FunctRealise function = new FunctRealise();
        List<Double> result = function.diapason(5, 8, x -> Math.pow(2, x));
        List<Double> expected = Arrays.asList(32D, 64D, 128D);
        assertThat(result, is(expected));
    }
}