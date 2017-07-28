package com.it2go.service;

import com.it2go.model.User;
import com.it2go.model.UserImpl;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.LinkedHashMap;
import java.util.Map;

@WebService(endpointInterface = "com.it2go.service.HelloWorld",
        serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {
    private Map<Integer, User> users = new LinkedHashMap<>();

    public HelloWorldImpl() {
    }

    @Override
    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }

    @Override
    public String sayHiToUser(UserImpl user) {
        System.out.println("sayHiToUser called");
        users.put(users.size() + 1, user);
        return "Hello "  + user.getFullName();
    }

    @Override
    public Map<Integer, User> getUsers() {
        System.out.println("getUsers called");
        return users;
    }

}
// END SNIPPET: service
