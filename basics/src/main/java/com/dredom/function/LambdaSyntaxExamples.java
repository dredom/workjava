/**
 *
 */
package com.dredom.function;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import static java.lang.System.out;

/**
 * Note that a lambda expression looks a lot like a method declaration;
 * you can consider lambda expressions as anonymous methods—methods without a name.
 *
 * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 *
 */
public class LambdaSyntaxExamples {

    public class Person {
        String name;
        int age;
        public Person(String name, int age) {
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
            return "Person [name=" + name + ", age=" + age + "]";
        }

    }

    public static void main(String[] args) {
        LambdaSyntaxExamples instance = new LambdaSyntaxExamples();
        instance.doExample();
    }

    private void doExample() {
        Person person = new Person("Joe", 23);
        BiFunction<Person, Integer, Person> func1 = (Person p, Integer age) -> {
            if (p.getAge() > age) {
                return p;
            }
            return null;
        };
        out.println(" func1 " + func1.apply(person, 19));
        out.println(" func1 " + func1.apply(person, 55));

        // Omit type in function parameters
        BiFunction<Person, Integer, Person> func2 = ( p, age) ->  p.getAge() > age ? p : null;
        out.println(" func2 " + func2.apply(person, 15));

        // Function with single parameter does not require ()
        Predicate<Person> pred1 = (Person p) -> p.age > 22;
        out.println(" pred1 " + pred1.test(person));
        Predicate<Person> pred3 = ( p ) -> p.age > 22;
        Predicate<Person> pred2 = p -> p.age > 22;
        out.println(" pred2 " + pred2.test(person));

        // If you specify a single expression, then the Java runtime evaluates the expression and then returns its value.
        // Alternatively, you can use a return statement:

    }

}
