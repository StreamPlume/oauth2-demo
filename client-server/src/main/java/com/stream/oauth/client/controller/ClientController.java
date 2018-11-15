package com.stream.oauth.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

/**
 * Created by Stream on 2018/11/12.
 */

@RestController
public class ClientController {

    @Autowired
    private RestOperations restOperations;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test() {
        return "cleint, Hollo world";
    }

    @RequestMapping(value = "/client",method = RequestMethod.GET)
    public String client() {
        return "cleint, Hollo world";
    }

    @RequestMapping("/personInfoStr")
    public String personStr() {

        String personResourceUrl = "http://localhost:9002/user/me";
        String forObject = restOperations.getForObject(personResourceUrl, String.class);

        return forObject.toString();
    }

}
