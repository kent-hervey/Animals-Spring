package com.example.restservice.utilities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoLogging {
    public static void runDemoLogging() {
        log.info("Demo info");
        log.warn("Demo warn");
        log.error("Demo error");
    }
}
