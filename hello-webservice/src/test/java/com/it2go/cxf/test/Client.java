package com.it2go.cxf.test;

import com.it2go.model.UserImpl;
import com.it2go.service.HelloWorld;
import com.it2go.service.HelloWorld2;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public final class Client {

    private static final QName SERVICE_NAME
            = new QName("http://service.it2go.com/", "HelloWorld2");
    private static final QName PORT_NAME
            = new QName("http://service.it2go.com/", "HelloWorld2Port");


    public HelloWorld getHelloWolrdService() throws MalformedURLException {
        URL serviceWSDL = new URL("http://localhost:9000/helloWorld?wsdl");
        final QName serviceName = new QName("http://service.it2go.com/", "HelloWorld");
        Service service = Service.create(serviceWSDL, serviceName);
        // Endpoint Address
        //String endpointAddress = "http://localhost:9000/helloWorld";
        // If web service deployed on Tomcat (either standalone or embedded)
        // as described in sample README, endpoint should be changed to:
        // String endpointAddress = "http://localhost:8080/java_first_jaxws/services/hello_world";

        // Add a port to the Service
        //service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

        return service.getPort(HelloWorld.class);

    }

    @Test
    public void testHelloWorld() throws MalformedURLException {

        HelloWorld helloWorld = getHelloWolrdService();

        final org.apache.cxf.endpoint.Client client = ClientProxy.getClient(helloWorld);

        // add UserToken OutputInteceptor
        //addUserTokenOutInterceptor(client);

        //
        addX500OutInterceptor(client);

        helloWorld.sayHi("Ali Mbarek");
    }

    private void addUserTokenOutInterceptor(org.apache.cxf.endpoint.Client client){

        Map<String, Object> outProps = new HashMap<>();
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        // user
        outProps.put(WSHandlerConstants.USER,"mkyoung");
        // Password type : plain text
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        // for hashed password use:
        //inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
        // Callback used to retrieve password for given user.
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
                ClientPasswordCallback.class.getName());

        client.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));
    }

    private void addX500OutInterceptor(org.apache.cxf.endpoint.Client client){
        Map<String, Object> outProps = new HashMap<>();

        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
        //outProps.put(WSHandlerConstants.USER,"tomcat");
        outProps.put(WSHandlerConstants.SIG_PROP_FILE,"client_sign.properties");
        outProps.put(WSHandlerConstants.USER,"tomcat");
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
                ClientPasswordCallback.class.getName());
        client.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));
    }

    @Test
    public void testHelloWolrd_old() throws MalformedURLException {
        URL serviceWSDL = new URL("http://localhost:9000/helloWorld?wsdl");
        final QName serviceName = new QName("http://service.it2go.com/", "HelloWorld");
        Service service = Service.create(serviceWSDL, serviceName);
        // Endpoint Address
        //String endpointAddress = "http://localhost:9000/helloWorld";
        // If web service deployed on Tomcat (either standalone or embedded)
        // as described in sample README, endpoint should be changed to:
        // String endpointAddress = "http://localhost:8080/java_first_jaxws/services/hello_world";

        // Add a port to the Service
        //service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

        HelloWorld hw = service.getPort(HelloWorld.class);
        hw.sayHi("John Doe");

        //UserImpl user = new UserImpl("John","Doe");
        //System.out.println(hw.sayHiToUser(user));

        //say hi to some more users to fill up the map a bit
        //user = new UserImpl("Ali","Mbarek");
        //System.out.println(hw.sayHiToUser(user));

        //user = new UserImpl("Bruce","Lee");
        //System.out.println(hw.sayHiToUser(user));
/*
        System.out.println();
        System.out.println("Users: ");
        Map<Integer, User> users = hw.getUsers();
        for (Map.Entry<Integer, User> e : users.entrySet()) {
            System.out.println("  " + e.getKey() + ": " + e.getValue().getFullName());
        }
        */
    }

    @Test
    public void testHelloWolrd2() throws MalformedURLException {
        URL serviceWSDL = new URL("http://localhost:9000/helloWorld2?wsdl");
        final QName serviceName = new QName("http://service.it2go.com/", "HelloWorld2");
        Service service = Service.create(serviceWSDL, serviceName);
        // Endpoint Address
        //String endpointAddress = "http://localhost:9000/helloWorld";
        // If web service deployed on Tomcat (either standalone or embedded)
        // as described in sample README, endpoint should be changed to:
        // String endpointAddress = "http://localhost:8080/java_first_jaxws/services/hello_world";

        // Add a port to the Service
        //service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

        HelloWorld2 hw = service.getPort(HelloWorld2.class);
        hw.sayMyName("Ali Mbarek");

        //UserImpl user = new UserImpl("John","Doe");
        //System.out.println(hw.sayHiToUser(user));

        //say hi to some more users to fill up the map a bit
        //user = new UserImpl("Ali","Mbarek");
        //System.out.println(hw.sayHiToUser(user));

        //user = new UserImpl("Bruce","Lee");
        //System.out.println(hw.sayHiToUser(user));
/*
        System.out.println();
        System.out.println("Users: ");
        Map<Integer, User> users = hw.getUsers();
        for (Map.Entry<Integer, User> e : users.entrySet()) {
            System.out.println("  " + e.getKey() + ": " + e.getValue().getFullName());
        }
        */
    }

    @Test
    public void testDispatch(){

    }

    @Test
    public void testStream() throws Exception {
   //     JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        // Send data
        URL url = new URL("http://localhost:9000/helloWorld");
        String soapAction =
                "http://com.it2go.services/helloWorld#sayHi";
        String soap =
                " <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hw=\"http://services.it2go.com/\">\n" +
                        " <soapenv:Header/>\n" +
                            " <soapenv:Body>\n" +
                                 " <hw:sayHi>\n" +
                                    " <text>city</text>\n" +
                                 " </hw:sayHi>\n" +
                            " </soapenv:Body>\n" +
                        " </soapenv:Envelope>";

        // from SOAPUI
        String soapMessage = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.it2go.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ser:sayHi>\n" +
                "         <!--Optional:-->\n" +
                "         <text>World</text>\n" +
                "      </ser:sayHi>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        String action = "sayHi";
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-type", "text/xml; charset=utf-8");
       // conn.setRequestProperty("SOAPAction", soapAction);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        try(final OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
            wr.write(soap.toCharArray());
            wr.flush();
        }
       // InputStream resStream = conn.getInputStream();

        /*
        InputStreamReader reader = new InputStreamReader(conn.getInputStream(), "UTF-8");
        BufferedReader in = new BufferedReader(reader);

        String responseString = "";
        String outputString = "";
        //Write the SOAP message response to a String.
        while ((responseString = in.readLine()) != null) {
            outputString = outputString + responseString;
        }

        System.out.println(outputString);
        */


        try (final InputStreamReader responseReader = new InputStreamReader(conn.getInputStream(), "UTF-8")) {

           // final String response = CharStreams.toString(responseReader);
            BufferedReader in = new BufferedReader(responseReader);
            String responseString = "";
            String outputString = "";
            //Write the SOAP message response to a String.
            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }

            System.out.println(outputString);
        }

    }
}
