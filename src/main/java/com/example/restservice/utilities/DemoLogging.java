package com.example.restservice.utilities;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger; //not needed if using @Slf4j
import org.slf4j.LoggerFactory; //not needed if using @Slf4j

//@Slf4j
public class DemoLogging {

    private DemoLogging() {
    }

    private static final Logger log = LoggerFactory.getLogger(DemoLogging.class); //not needed if using @Slf4j
    public static void runDemoLogging() {
        log.info("Demo infoZ");
        log.warn("Demo warnZ");
        log.error("Demo errorZ");
        log.debug("Demo debugZ");
        log.trace("Demo traceZ");
    }
}
