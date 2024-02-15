package com.digital.moviesapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This file just makes sure that if you load http://localhost:8080 directly, we'll show you something.
 *
 * It also has a quick example of logging, which I'm sure you'll need sooner or later!
 */
@RestController
public class IndexController {

    // Get an instance of the logger which you can use later.
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String index() {
        // .info() will print a normal message to the logs. There are other options too, like .error()
        logger.info("Loaded the index URL.");

        return "Yep, your server is running :)";
    }
}