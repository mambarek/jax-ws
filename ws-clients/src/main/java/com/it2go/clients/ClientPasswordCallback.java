package com.it2go.clients;

//import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.apache.ws.security.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

public class ClientPasswordCallback implements CallbackHandler {
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

        if ("mkyong".equals(pc.getIdentifier())) {
            System.out.println(">>> ClientPasswordCallback --> pc.getPassword() " + pc.getPassword());
            pc.setPassword("123456");
        }

    }
}
