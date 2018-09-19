package com.it2go.cxf.test;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;

// For CXF 2.4 onwards, the callback handler supplies the password for all cases,
// and the validation is done internally (but can be configured)
public class ClientPasswordCallback implements CallbackHandler {
    public void handle(Callback[] callbacks) {

        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

        if ("mkyong".equals(pc.getIdentifier())) {
            System.out.println(">>> ClientPasswordCallback --> pc.getPassword() " + pc.getPassword());

            pc.setPassword("123456");
        }

        // x500 password for "tomcat" user stored in Certificate
        if ("tomcat".equals(pc.getIdentifier())) {
            pc.setPassword("tomcat");
        }
    }
}
