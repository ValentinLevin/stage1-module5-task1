package com.epam.mjc;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InterfaceCreator {

    public Predicate<List<String>> isValuesStartWithUpperCase() {
        return values -> {
            for (String value : values) {
                if (value == null || value.isEmpty() || value.charAt(0) < 'A' || value.charAt(0) > 'Z')
                    return false;
            }

            return true;
        };
    }

    public Consumer<List<Integer>> addEvenValuesAtTheEnd() {
        return values -> {
            int valueCount = values.size();
            for (int i = 0; i < valueCount; i++) {
                int value = values.get(i);
                if (value % 2 == 0) {
                    values.add(value);
                }
            }
        };
    }

    public Supplier<List<String>> filterCollection(List<String> values) {
        return () -> {
            Predicate<String> isStartsWithUpperCase = value -> value != null && !value.isEmpty() && value.charAt(0) >= 'A' && value.charAt(0) <= 'Z';
            Predicate<String> isLineEndsWithDot = value -> value != null && !value.isEmpty() && value.charAt(value.length() - 1) == '.';
            Predicate<String> isLineHasAtLeast3Words = value -> value != null && !value.isEmpty() && value.split("\\W").length > 3;

            return values.stream()
                    .filter(
                            isStartsWithUpperCase
                                    .and(isLineEndsWithDot)
                                    .and(isLineHasAtLeast3Words)
                    )
                    .collect(Collectors.toList());
        };
    }

    public Function<List<String>, Map<String, Integer>> stringSize() {
        return list -> {
            Map<String, Integer> map = new HashMap<>();
            for (String value: list) {
                map.put(value, value.length());
            }
            return map;
        };
    }

    public BiFunction<List<Integer>, List<Integer>, List<Integer>> concatList() {
        return (list1, list2) -> {
            List<Integer> result = new ArrayList<>();
            result.addAll(list1);
            result.addAll(list2);
            return result;
        };
    }
}
