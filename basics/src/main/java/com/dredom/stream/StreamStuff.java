package com.dredom.stream;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamStuff {
    static class Person {
        String name;
        int age;
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
        @Override
        public String toString() {
            return "[" + name + ", " + age + "]";
        }
    }

    static List<Person> persons = Arrays.asList(
            new Person("Angela", 22),
            new Person("James", 33),
            new Person("Harry", 55)
    );

    public static void main(String[] args) {
        StreamStuff instance = new StreamStuff();
        instance.forEach();
        instance.builderTest();
        instance.filter();
        instance.filterCompact();
        instance.collector();
    }

    private void forEach() {
        List<String> list = Arrays.asList( "One", "Two", "Three" );
        out.println("Test forEach: " + list);
        Stream<String> stream = list.stream();
        Consumer<? super String> consumer = item -> out.println(item);
        stream.forEach(consumer);

//        long count = stream.count();  // fails
    }

    private void builderTest() {
        final String[] items = { "A", "B", "C" };
        out.println("builderTest: ");
        Stream.Builder<String> bldr = Stream.builder();
        for (String item : items) {
            bldr.accept(item);
        }
        Stream<String> stream = bldr.build();
        Consumer<? super String> consumer = item -> out.println(item);
        stream.forEach(consumer);
    }

    private void filter() {
        final int[] items = { 1, 2, 3 };
        out.println("Test filter: " + Arrays.toString(items));
        IntStream stream = IntStream.of(items);
        IntPredicate predicate = item -> item > 1;
        long count = stream
                .filter(predicate)
                .count();
        out.printf(" Items > 1: %d \n", count);
    }

    private void filterCompact() {
        final int[] items = { 1, 2, 3 };
        long count = IntStream.of(items)
                .filter(i -> i > 1)
                .count();
        out.printf( "Items > 1: %d \n", count);
    }

    /**
     * Create output list from person names.
     */
    void collector() {
        out.println("collector from Persons to List<String>");
        List<String> list = persons.stream()
                .map(Person::getName)
                .collect(Collectors.toList())
                ;
        out.printf(" Collected names: %s \n", list);
    }
}
