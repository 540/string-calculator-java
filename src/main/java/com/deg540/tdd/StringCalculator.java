package com.deg540.tdd;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringCalculator {
    public int add(String numbers) throws Exception {
        if (numbers.isEmpty()) {
            return 0;
        }

        String defaultDelimiter = "[,\n]";
        Matcher match = Pattern.compile("//(.)\n(.*)").matcher(numbers);
        if(match.matches()){
            defaultDelimiter = match.group(1);
            numbers = match.group(2);
        }

        match = Pattern.compile("//\\[(.*)\\]\n(.*)").matcher(numbers);
        if(match.matches()){
            defaultDelimiter = match.group(1);
            numbers = match.group(2);
        }

        List<Integer> realNumbers = Arrays.stream(numbers.split(defaultDelimiter))
                .map(Integer::parseInt)
                .toList();

        realNumbers = realNumbers.stream().filter(number -> number <= 1000).toList();

        if (realNumbers.stream().anyMatch(number -> number < 0)){
            throw new Exception("Negative numbers not allowed");
        }

        return realNumbers.stream().reduce(Integer::sum).orElseThrow();
    }
}
