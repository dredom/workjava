package com.dredom.stream;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class StreamParallelStuff {

    public static void main(String[] args) {
        StreamParallelStuff instance = new StreamParallelStuff();
        instance.basicTest();
    }

    private void basicTest() {
        List<String> list = Arrays.asList(new String[] {
                "One", "To", "Three", "Fo"
        });
        System.out.println("Test forEach: " + list);
        Stream<String> stream = list.parallelStream();
        DoStuff stuffer = new DoStuff();
        Consumer<String> consumer = in -> {
            try {
                String go = stuffer.stuffIt(in);
                out.println(go);
            } catch (Exception e) {
                throw new RuntimeException("Oops", e);
            }
        };
        stream.forEach(consumer);
    }



    /**
     * Use this in a lambda.
     */
    static class DoStuff {
        public String stuffIt(String in) throws Exception {
            if (in.length() < 3) {
                throw new Exception("too small");
            }
            String out = String.format("Form:[%s]", in);
            return out;
        }
    }
}
