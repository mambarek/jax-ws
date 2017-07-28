package com.it2go.cxf.test;

import com.it2go.service.HelloWorldImpl;

import javax.xml.ws.Endpoint;

public class Server {

    protected Server() throws Exception {
        // START SNIPPET: publish
        System.out.println("Starting Server");
        HelloWorldImpl implementor = new HelloWorldImpl();
        String address = "http://localhost:9000/helloWorld";
        Endpoint.publish(address, implementor);
        // END SNIPPET: publish
    }

    public static void main(String args[]) throws Exception {
        new Server();
        System.out.println("Server ready...");

        Thread.sleep(35 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}