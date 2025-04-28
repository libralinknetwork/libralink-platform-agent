package io.libralink.platform.agent.suite.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    private static final Logger LOG = LoggerFactory.getLogger(StatusController.class);

    @GetMapping(value = "/status")
    public String getStatus() {

        return "OK";
    }
}
