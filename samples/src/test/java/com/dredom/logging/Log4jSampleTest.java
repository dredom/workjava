package com.dredom.logging;

import org.junit.Test;

public class Log4jSampleTest {

    static Log4jSample service = new Log4jSample();

    @Test
    public void logSomething() {
        service.logSomething();
    }
}
