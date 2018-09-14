package com.it2go.service;

import javax.jws.WebService;

@WebService(endpointInterface = "com.it2go.service.HelloWorld2",
        serviceName = "HelloWorld2" )
public class HelloWorld2Impl implements HelloWorld2 {
    @Override
    public void sayMyName(String name) {
        System.out.println("Hello your name is : " + name);
    }
}
