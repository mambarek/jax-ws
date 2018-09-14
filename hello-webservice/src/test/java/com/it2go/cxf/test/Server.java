package com.it2go.cxf.test;

import com.it2go.service.HelloWorld2;
import com.it2go.service.HelloWorld2Impl;
import com.it2go.service.HelloWorldImpl;

import javax.xml.ws.Endpoint;

public class Server {

    protected Server() throws Exception {
        // START SNIPPET: publish
        System.out.println("Starting Server");
        HelloWorld2Impl implementor2 = new HelloWorld2Impl();
        String address2 = "http://localhost:9000/helloWorld2";
        Endpoint.publish(address2, implementor2);
        // END SNIPPET: publish

        HelloWorldImpl implementor = new HelloWorldImpl();
        String address = "http://localhost:9000/helloWorld";
        Endpoint.publish(address, implementor);
    }

    public static void main(String args[]) throws Exception {
        new Server();
        System.out.println("Server ready...");

        Thread.sleep(35 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}