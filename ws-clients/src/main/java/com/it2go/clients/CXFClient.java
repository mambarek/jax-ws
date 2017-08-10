package com.it2go.clients;

import com.it2go.clients.cxf.generated.HelloWorld;
import com.it2go.clients.cxf.generated.HelloWorld_Service;
import com.it2go.clients.cxf.generated.IntegerUserMap;
import com.it2go.clients.cxf.generated.User;
import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.ws.security.SecurityConstants;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mmbarek on 05.07.2017.
 */
public class CXFClient {

    public static void main(String[] args) {
        //testService(null);
        //testSecuredService(null);

        try {
            //noAuthenticationTest();
//            basicAuthenticationTest();
//            caAuthenticationTest();
//            testService(null);
            testUserNameToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setupFiddlerProxy(){

            //for localhost testing only
            javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                    new javax.net.ssl.HostnameVerifier(){

                        public boolean verify(String hostname,
                                              javax.net.ssl.SSLSession sslSession) {
                            if (hostname.equals("localhost")) {
                                return true;
                            }
                            return false;
                        }
                    });


/*        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyPort", "8888");*/
    }

    public static void testUserNameToken() throws MalformedURLException {
        setupFiddlerProxy();
        //String cxfUrl = "https://bb-4512.leismann.net:8090/cxf/services/WssHelloWorld?wsdl";
        String cxfUrl = "https://localhost:8443/cxf/services/WssHelloWorld?wsdl";
        //String cxfUrl = "https://bb-4512.leismann.net:8090/cxf/services/WssHelloWorld?wsdl";
        URL wsdlLocation = new URL(cxfUrl);
        //URL wsdlLocation = new URL("file:/E:/Dev/Git/test/jax-ws/ws-clients/src/main/resources/helloWorld-cxf-wss.wsdl");

        QName qname = new QName("http://service.it2go.com/", "HelloWorld");
        Service port = Service.create(wsdlLocation, qname);
        HelloWorld helloService = port.getPort(HelloWorld.class);

        //add username and password for container authentication
        BindingProvider bp = (BindingProvider) helloService;
        Client client = ClientProxy.getClient(helloService);
        Endpoint cxfEndpoint = client.getEndpoint();
        HTTPConduit conduit = (HTTPConduit) client.getConduit();

        bp.getRequestContext().put("ws-security.username", "mkyong");
        bp.getRequestContext().put("ws-security.password", "123456");
        //bp.getRequestContext().put("security.username", "mkyong");
        //bp.getRequestContext().put("security.password", "123456");
        //bp.getRequestContext().put(WSHandlerConstants.USER, "mkyong");
        //############################################################################################
        Map<String,Object> outProps = new HashMap<String,Object>();
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        outProps.put(WSHandlerConstants.USER, "mkyong");
        //outProps.put("security.username", "mkyong");
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        //outProps.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "https://bb-4512.leismann.net:8090/cxf/services/WssHelloWorld");
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
                ClientPasswordCallback.class.getName());

        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);

        // Disable CN verification from certificate
        // this is needed for localhost envirement
        TLSClientParameters tlsParams = new TLSClientParameters();
        tlsParams.setDisableCNCheck(true);
        conduit.setTlsClientParameters(tlsParams);


        cxfEndpoint.getOutInterceptors().add(wssOut);

        String answ = helloService.sayHi("CXF Client!!!");
        System.out.println(answ);
    }

    public static void testService(String[] args) {
        setupFiddlerProxy();
        try {
            //String cxfUrl = "http://localhost:8080/cxf/services/helloWorld?wsdl";
            String cxfUrl = "https://bb-4512.leismann.net:8090/cxf/services/WssHelloWorld?wsdl";

            URL wsdlLocation = new URL(cxfUrl);
            HelloWorld_Service service = new HelloWorld_Service(wsdlLocation);

            Map<String,Object> outProps = new HashMap<String,Object>();

            outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
            outProps.put(WSHandlerConstants.USER, "mkyong");
            outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
            outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
                    ClientPasswordCallback.class.getName());

            //outProps.put("ws-security.username", "mkyong");
            //outProps.put("ws-security.password", "123456");
            //outProps.put(SecurityConstants.PASSWORD, "123456");
            WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);

            HelloWorld port = service.getHelloWorld();
            Client client = ClientProxy.getClient(port);
            Endpoint cxfEndpoint = client.getEndpoint();

            cxfEndpoint.getOutInterceptors().add(wssOut);

            String answ = port.sayHi("CXF Client!!!");
            System.out.println(answ);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    protected static SSLSocketFactory getSslSocketFactory() throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        //SSLContext sc = SSLContext.getInstance("SSLv3");
        SSLContext sc = SSLContext.getInstance("TLS");

        //Set up the truststore for CXF
        KeyStore trustStore = KeyStore.getInstance("JKS");
        String trustpass = "changeit";
        // File truststoreFile = new File("client.keystore");
        // trustStore.load(new FileInputStream(truststoreFile), trustpass.toCharArray());
        // URL truststoreUrl = Thread.currentThread().getContextClassLoader().getResource("C:/Program Files/Java/jdk1.8.0_131/jre/lib/security/cacerts");
        //trustStore.load(truststoreUrl.openStream(), trustpass.toCharArray());
        String caStorePath = "C:/Program Files/Java/jdk1.8.0_131/jre/lib/security/cacerts";
        //caStorePath = "C:/Dev/Certs/bb-4512.jks";
        trustStore.load(new FileInputStream(caStorePath), trustpass.toCharArray());
        TrustManagerFactory trustFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustFactory.init(trustStore);

        //Set up the keystore for CXF
        KeyStore keyStore = KeyStore.getInstance("JKS");
        String keypass = "changeit";
        // File keystoreFile = new File("client.keystore");
        // keyStore.load(new FileInputStream(keystoreFile),  keypass.toCharArray());
        // URL keystoreUrl = Thread.currentThread().getContextClassLoader().getResource("C:/Program Files/Java/jdk1.8.0_131/jre/lib/security/cacerts");
        keyStore.load(new FileInputStream(caStorePath), keypass.toCharArray());
        KeyManagerFactory keyFactory =
                KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyFactory.init(keyStore, trustpass.toCharArray());


        sc.init(keyFactory.getKeyManagers(), trustFactory.getTrustManagers(), new SecureRandom());

        return sc.getSocketFactory();
    }

    protected static void noAuthenticationTest() throws MalformedURLException {
        String WS_URL = "helloWorld-autho-cxf.wsdl";
        QName qname = new QName("http://service.it2go.com/", "HelloWorld");
        URL wsdlLocation = new URL("file:/C:/Dev/learning/webservices/jaxws/ws-clients/src/main/resources/helloWorld-autho-cxf.wsdl");

        Service port = Service.create(wsdlLocation, qname);
        HelloWorld helloService = port.getPort(HelloWorld.class);

        String answ = helloService.sayHi("Du bester CXF Client!!!");
        System.out.println(answ);
    }

    protected static void basicAuthenticationTest() throws MalformedURLException {
        setupFiddlerProxy();
        String WS_URL = "helloWorld-autho-cxf.wsdl";
        QName qname = new QName("http://service.it2go.com/", "HelloWorld");
        URL wsdlLocation = new URL("file:/C:/Dev/learning/webservices/jaxws/ws-clients/src/main/resources/helloWorld-autho-cxf.wsdl");

        Service port = Service.create(wsdlLocation, qname);
        HelloWorld helloService = port.getPort(HelloWorld.class);

        //add username and password for container authentication
        BindingProvider bp = (BindingProvider) helloService;
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "mkyong");
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "123456");

        String answ = helloService.sayHi("Du bester CXF Client!!!");
        System.out.println(answ);
    }

    protected static void caAuthenticationTest() throws MalformedURLException {

        QName qname = new QName("http://service.it2go.com/", "HelloWorld");
        URL wsdlLocation = new URL("file:/C:/Dev/learning/webservices/jaxws/ws-clients/src/main/resources/helloWorld-autho-cxf.wsdl");

        Service port = Service.create(wsdlLocation, qname);
        HelloWorld helloService = port.getPort(HelloWorld.class);

        Client client = ClientProxy.getClient(helloService);
        HTTPConduit conduit = (HTTPConduit) client.getConduit();

        BindingProvider bp = (BindingProvider) helloService;

        try {
            SSLSocketFactory factory = getSslSocketFactory();
            //bp.getRequestContext().put(JAXWSProperties.SSL_SOCKET_FACTORY, factory);
            //prepareConduit(conduit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String answ = helloService.sayHi("Du bester CXF Client!!!");
        System.out.println(answ);
    }

    protected static void prepareConduit(HTTPConduit httpConduit) throws Exception {
        TLSClientParameters tlsParams = new TLSClientParameters();
        tlsParams.setDisableCNCheck(true);
        //Set up the truststore for CXF
        KeyStore trustStore = KeyStore.getInstance("JKS");
        String trustpass = "changeit";
        // File truststoreFile = new File("client.keystore");
        // trustStore.load(new FileInputStream(truststoreFile), trustpass.toCharArray());
        // URL truststoreUrl = Thread.currentThread().getContextClassLoader().getResource("C:/Program Files/Java/jdk1.8.0_131/jre/lib/security/cacerts");
        //trustStore.load(truststoreUrl.openStream(), trustpass.toCharArray());
        String caStorePath = "C:/Program Files/Java/jdk1.8.0_131/jre/lib/security/cacerts";
        caStorePath = "C:/Dev/Certs/bb-4512.jks";
        trustStore.load(new FileInputStream(caStorePath), trustpass.toCharArray());
        TrustManagerFactory trustFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustFactory.init(trustStore);
        TrustManager[] tm = trustFactory.getTrustManagers();
        tlsParams.setTrustManagers(tm);

        //Set up the keystore for CXF
        KeyStore keyStore = KeyStore.getInstance("JKS");
        String keypass = "changeit";
        // File keystoreFile = new File("client.keystore");
        // keyStore.load(new FileInputStream(keystoreFile),  keypass.toCharArray());
        // URL keystoreUrl = Thread.currentThread().getContextClassLoader().getResource("C:/Program Files/Java/jdk1.8.0_131/jre/lib/security/cacerts");
        keyStore.load(new FileInputStream(caStorePath), keypass.toCharArray());
        KeyManagerFactory keyFactory =
                KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyFactory.init(keyStore, trustpass.toCharArray());
        KeyManager[] km = keyFactory.getKeyManagers();
        tlsParams.setKeyManagers(km);

        tlsParams.setCertAlias("tomcat");
        FiltersType filter = new FiltersType();
        filter.getInclude().add(".*_EXPORT_.*");
        filter.getInclude().add(".*_EXPORT1024_.*");
        filter.getInclude().add(".*_WITH_DES_.*");
        filter.getInclude().add(".*_WITH_NULL_.*");
        filter.getExclude().add(".*_DH_anon_.*");
        tlsParams.setCipherSuitesFilter(filter); //set all the needed include and exclude filters.

        httpConduit.setTlsClientParameters(tlsParams);
    }
}
