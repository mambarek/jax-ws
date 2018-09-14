package com.it2go.cxf.test;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import java.io.IOException;

// For CXF 2.4 onwards, the callback handler supplies the password for all cases,
// and the validation is done internally (but can be configured)
public class ServerPasswordCallback implements  CallbackHandler {


    public void handle(Callback[] callbacks) throws IOException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

        if ("mkyong".equals(pc.getIdentifier())) {
            System.out.println(">>> ServerPasswordCallback --> pc.getPassword() " + pc.getPassword());
            // einfach den richtigen zu vergleichen mit Clientpassword setzen
            // der Verglaich zwischen beide geschieht server intern mit CXF
            pc.setPassword("123456");
            // das war in frühren unter 2.4 versionen nötig
            /*if(!"123456".equals(pc.getPassword()))
                throw new IOException("wrong password");*/
        }
    }
}
