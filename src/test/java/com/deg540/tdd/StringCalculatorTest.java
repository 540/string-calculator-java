package com.deg540.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    private StringCalculator stringCalculator;

    @BeforeEach
    public void init() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void adds_empty_string() throws Exception {
        int result = stringCalculator.add("");

        assertEquals(0, result);
    }

    @Test
    public void adds_string_with_single_value() throws Exception {
        int result = stringCalculator.add("1");

        assertEquals(1, result);
    }

    @Test
    public void adds_string_with_two_values() throws Exception {
        int result = stringCalculator.add("1,2");

        assertEquals(3, result);
    }

    @Test
    public void adds_string_with_multiple_values() throws Exception {
        int result = stringCalculator.add("1,2,6,10");

        assertEquals(19, result);
    }

    @Test
    public void adds_string_with_line_jump_delimiter() throws Exception {
        int result = stringCalculator.add("1\n2");

        assertEquals(3, result);
    }

    @Test
    public void adds_string_with_custom_delimiter() throws Exception {
        assertEquals(3, stringCalculator.add("//;\n1;2"));
    }

    @Test
    public void negative_numbers_are_not_accepted() {
        Exception result = Assertions.assertThrowsExactly(Exception.class, () -> stringCalculator.add("1,-2"));
        assertEquals("Negative numbers not allowed", result.getMessage());
    }

    @Test
    public void does_not_add_numbers_larger_than_1000() throws Exception {
        assertEquals(1000, stringCalculator.add("1000,1001"));
    }

    @Test
    public void adds_string_with_custom_delimiter_with_variable_length() throws Exception {
        assertEquals(6, stringCalculator.add("//[;;;]\n1;;;2;;;3"));
    }
}

