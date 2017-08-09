
package com.it2go.clients.ri.generated;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b14002
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "HelloWorld", targetNamespace = "http://service.it2go.com/", wsdlLocation = "file:/E:/Dev/Git/test/jax-ws/ws-clients/src/main/resources/helloWorld-ri.wsdl")
public class HelloWorld_Service
    extends Service
{

    private final static URL HELLOWORLD_WSDL_LOCATION;
    private final static WebServiceException HELLOWORLD_EXCEPTION;
    private final static QName HELLOWORLD_QNAME = new QName("http://service.it2go.com/", "HelloWorld");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/E:/Dev/Git/test/jax-ws/ws-clients/src/main/resources/helloWorld-ri.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        HELLOWORLD_WSDL_LOCATION = url;
        HELLOWORLD_EXCEPTION = e;
    }

    public HelloWorld_Service() {
        super(__getWsdlLocation(), HELLOWORLD_QNAME);
    }

    public HelloWorld_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), HELLOWORLD_QNAME, features);
    }

    public HelloWorld_Service(URL wsdlLocation) {
        super(wsdlLocation, HELLOWORLD_QNAME);
    }

    public HelloWorld_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, HELLOWORLD_QNAME, features);
    }

    public HelloWorld_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HelloWorld_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns HelloWorld
     */
    @WebEndpoint(name = "HelloWorldImplPort")
    public HelloWorld getHelloWorldImplPort() {
        return super.getPort(new QName("http://service.it2go.com/", "HelloWorldImplPort"), HelloWorld.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HelloWorld
     */
    @WebEndpoint(name = "HelloWorldImplPort")
    public HelloWorld getHelloWorldImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.it2go.com/", "HelloWorldImplPort"), HelloWorld.class, features);
    }

    private static URL __getWsdlLocation() {
        if (HELLOWORLD_EXCEPTION!= null) {
            throw HELLOWORLD_EXCEPTION;
        }
        return HELLOWORLD_WSDL_LOCATION;
    }

}
