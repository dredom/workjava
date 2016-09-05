package com.dredom.util;

import java.util.ArrayDeque;

/**
 * Use Deque to build a string of path ids for logging method call depth.
 */
public class QueueStuff {

    public static void main(String... parm) {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add(":one");
        q.add(":two");
        System.out.println(q);
        String pop = q.removeLast();
        System.out.println(q);
    }
}
