package com.it2go.services;


//import org.apache.wss4j.common.ext.WSPasswordCallback;

import org.apache.ws.security.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

public class ServerPasswordCallback implements  CallbackHandler {


    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

        if ("mkyong".equals(pc.getIdentifier())) {
            System.out.println(">>> ServerPasswordCallback --> pc.getPassword() " + pc.getPassword());
            pc.setPassword("123456");
        }
    }
}
