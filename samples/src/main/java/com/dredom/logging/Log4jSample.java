package com.dredom.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jSample {
    private Logger log = LogManager.getFormatterLogger(getClass());

    public void logSomething() {
        log.info("Something simple");
        String myField = "HeyBear";
        log.info("Something better [myField=%s]", myField);
    }

}
