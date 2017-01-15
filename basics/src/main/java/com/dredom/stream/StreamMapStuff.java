package com.dredom.stream;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Streams with lambdas and Functions doing mapping stuff.
 * @author andre
 *
 */
public class StreamMapStuff {
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
        StreamMapStuff instance = new StreamMapStuff();
        instance.forEach();
        instance.collector();
        instance.collectorFunction();
    }

    private void forEach() {
        out.println("Test forEach: " + persons);
        persons.forEach(person -> {
            out.println(person.getName());
        });
        Consumer<? super Person> consumer = item -> out.println(item);
        persons.forEach(consumer);

//        long count = stream.count();  // fails
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

    void collectorFunction() {
        out.println("collectorFunction()");
        Function<Person, String> mapper = prsn ->  {
            return prsn.getName();
        };
        List<String> list = persons.stream()
                .map(mapper)
                .collect(Collectors.toList());
        out.print(list.toString());
        List<String> list2 = persons.stream()
                .map(prsn -> {
                    return prsn.getName();
                })
                .collect(Collectors.toList());
        out.println(list2.toString());
    }
}
