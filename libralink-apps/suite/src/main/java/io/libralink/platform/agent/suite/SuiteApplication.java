package io.libralink.platform.agent.suite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneOffset;
import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = "io.libralink.platform.agent")
public class SuiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuiteApplication.class, args);
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
    }
}
