package com.it2go.cxf.test;

import com.it2go.service.HelloWorld2Impl;
import com.it2go.service.HelloWorldImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;


import javax.xml.ws.Endpoint;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AuthServer {

    protected Endpoint createEndPoint() throws Exception {
        // START SNIPPET: publish
        System.out.println("Starting Server");
        HelloWorld2Impl implementor2 = new HelloWorld2Impl();
        String address2 = "http://localhost:9000/helloWorld2";
        Endpoint.publish(address2, implementor2);
        // END SNIPPET: publish

        HelloWorldImpl implementor = new HelloWorldImpl();
        String address = "http://localhost:9000/helloWorld";

        return Endpoint.publish(address, implementor);
    }

    /*
    * Hier kannst du nochmal alles nachlesen
    * https://cwiki.apache.org//confluence/display/CXF20DOC/WS-Security
    * */
    public static void main(String args[]) throws Exception {
        // first specify a cxf bus. without bus you become exception
        SpringBusFactory bf = new SpringBusFactory();
        URL busFile = Server.class.getResource("/wssec.xml");
        Bus bus = bf.createBus(busFile.toString());

        BusFactory.setDefaultBus(bus);

        final AuthServer authServer = new AuthServer();
        final Endpoint endPoint = authServer.createEndPoint();

        // add UserToken
        //addUserTokenInterceptor(endPoint);

        // add X500 Token
        addX500Interceptor(endPoint);

        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);

        bus.shutdown(true);
        System.out.println("Server exiting");
        System.exit(0);
    }

     private static void addUserTokenInterceptor(Endpoint endpoint){

        EndpointImpl impl = (EndpointImpl)endpoint;

        Map<String, Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        // Password type : plain text
        inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        // for hashed password use:
        //inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
        // Callback used to retrieve password for given user.
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
                ServerPasswordCallback.class.getName());

        impl.getInInterceptors().add(new WSS4JInInterceptor(inProps));
    }

    private static void addX500Interceptor(Endpoint endpoint){
        EndpointImpl impl = (EndpointImpl)endpoint;

        Map<String, Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
        inProps.put(WSHandlerConstants.SIG_PROP_FILE,"server_sign.properties");

        impl.getInInterceptors().add(new WSS4JInInterceptor(inProps));
    }
}