package com.dredom.basics;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class LambdaSamples {

    static class Person {
        String name;
        int age;
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        @Override
        public String toString() {
            return "[" + name + ", " + age + "]";
        }
    }
    static List<Person> persons = Arrays.asList( new Person[] {
            new Person("Angela", 22),
            new Person("James", 33),
            new Person("Harry", 55),
    });

    public static void main(String[] args) {
        LambdaSamples instance = new LambdaSamples();

        instance.beforeLambda();
        instance.withLambdaPredicate();
        instance.withMethodReference();
        instance.withLambdaConsumer();

    }

    // Before lambda.
    void beforeLambda() {
        // Print all older than 30.
        final int age = 30;
        out.println("beforeLambda: > " + age);
        for (Person person : persons) {
            if (person.age > age ) {
                out.println(person.toString());
            }
        }
    }

    void withLambdaPredicate() {
        final int age = 30;
        out.println("withLambda: > " + age);
        // Function
        Function<Person, Boolean> testAge =  p -> p.age > age;
        for (Person person : persons) {
            out.println(person + " > " + age + ": " + testAge.apply(person));
        }
        // Predicate
        Predicate<Person> aged = p -> p.age > age;
        for (Person person : persons) {
            out.println(person + " > " + age + ": " + aged.test(person));
        }
    }

    void withLambdaConsumer() {
        final int age = 30;
        out.println("withLambdaConsumer: > " + age);
        // Predicate
        Predicate<Person> aged = p -> p.age > age;
        Consumer<Person> cons = p -> out.println(p + " > " + age);
        for (Person person : persons) {
            if (aged.test(person)) {
                cons.accept(person);
            }
        }

    }

    /**
     * Method reference:  "::"
     * Can be used in place of lambda expressions as long as they satisfy the requirements of the functional interface.
     */
    void withMethodReference() {
        Person person = persons.get(0);
        out.println("withMethodReference: String::toLowerCase " );
        UnaryOperator<String> lc = String::toLowerCase;
        out.println(lc.apply(person.name));
        UnaryOperator<String> lce =  s -> s.toLowerCase();
        out.println(lce.apply(person.name));
    }
}
