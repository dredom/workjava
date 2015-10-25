package com.dredom.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamStuff {

    public static void main(String[] args) {
        StreamStuff instance = new StreamStuff();
        instance.basicTest();
        instance.builderTest();
        instance.filter();
    }

    private void basicTest() {
        List<String> list = Arrays.asList(new String[] {
                "One", "Two", "Three"
        });

        Stream<String> stream = list.stream();
        Consumer<? super String> consumer = item -> System.out.println(item);
        stream.forEach(consumer);

//        long count = stream.count();  // fails
    }

    private void builderTest() {
        final String[] items = new String[] {
                "One", "Two", "Three"
        };

        Stream.Builder<String> bldr = Stream.builder();
        for (String item : items) {
            bldr.accept(item);
        }
        Stream<String> stream = bldr.build();
        Consumer<? super String> consumer = item -> System.out.println(item);
        stream.forEach(consumer);
    }

    private void filter() {
        final int[] items = new int[] { 1, 2, 3 };
        IntStream stream = IntStream.of(items);
        IntPredicate predicate = item -> item > 1;
        long count = stream
                .filter(predicate)
                .count();
        System.out.printf("Items > 1: %d \n", count);
    }
}
