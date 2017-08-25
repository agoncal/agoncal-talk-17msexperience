package org.agoncal.talk.msexperience.demo01.numberapi.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class IsbnResource {

    @RequestMapping(method = GET, path = "/api/isbn")
    public String generateIsbnNumber() {
        return "ISBN-" + Math.random();
    }
}
