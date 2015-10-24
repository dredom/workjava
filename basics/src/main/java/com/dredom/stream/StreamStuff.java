package com.dredom.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class StreamStuff {

    public static void main(String[] args) {
        StreamStuff instance = new StreamStuff();
        instance.basicTest();
    }

    private void basicTest() {
        List<String> list = Arrays.asList(new String[] {
                "One", "Two", "Three"
        });

        Stream<String> stream = list.stream();
        Consumer<? super String> consumer = item -> System.out.println(item);
        stream.forEach(consumer);
    }
}
