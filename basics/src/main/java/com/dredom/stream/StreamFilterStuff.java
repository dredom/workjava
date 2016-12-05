/**
 *
 */
package com.dredom.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Stream handling.
 *
 */
public class StreamFilterStuff {

    class Beanie {
        String key;
        String value;
        public Beanie(String key, String value) {
            this.key = key;
            this.value = value;
        }
        public String getKey() {
            return key;
        }
        public String getValue() {
            return value;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        StreamFilterStuff instance = new StreamFilterStuff();
        instance.streamFilterWithNoOutputSimple();
        instance.streamFilterWithNoOutput();
    }

    void streamFilterWithNoOutputSimple() {
        String[] data = new String[] { "one", "two", "three" };
        List<String> list = Arrays.asList(data);
        String filterOn = "zilch";
        String result = list.stream()
                .filter( item -> item.equals(filterOn))
                .findFirst()
                .orElse(null);
        System.out.println("Result is " + result);
    }

    void streamFilterWithNoOutput() {
        Beanie[] beanies = new Beanie[] {
                new Beanie("one", "potato"),
                new Beanie("two", "tomatoe"),
                new Beanie("three", "carrot"),
        };
        List<Beanie> list = Arrays.asList(beanies);
        String filterOn = "zilch";
        String result = list.stream()
                .filter( item -> item.getKey().equals(filterOn))
                .findFirst()
                .map( item -> item.getValue())
                .orElse(null);

        System.out.println("Result is " + result);
    }

}
