package com.it2go.clients;

import com.it2go.clients.cxf.generated.HelloWorld;
import com.it2go.clients.cxf.generated.HelloWorld_Service;
import com.it2go.clients.cxf.generated.IntegerUserMap;
import com.it2go.clients.cxf.generated.User;
import com.sun.xml.internal.ws.developer.JAXWSProperties;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
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
            caAuthenticationTest();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void testService(String[] args) {

        try {
            String cxfUrl = "http://localhost:8080/cxf/services/helloWorld?wsdl";

            URL wsdlLocation = new URL(cxfUrl);
            HelloWorld_Service service = new HelloWorld_Service(wsdlLocation);
            String answ = service.getHelloWorld().sayHi("Du bester CXF Client!!!");
            System.out.println(answ);

            HelloWorld port = service.getHelloWorld();

            {
                System.out.println("Invoking sayHi...");
                java.lang.String _sayHi_text = "your are my best Client!";
                java.lang.String _sayHi__return = port.sayHi(_sayHi_text);
                System.out.println("sayHi.result=" + _sayHi__return);


            }
            {
                System.out.println("Invoking sayHiToUser...");
                User user = new User();
                user.setFirstName("Ali");
                user.setLastName("Mbarek");
                java.lang.String _sayHiToUser__return = port.sayHiToUser(user);
                System.out.println("sayHiToUser.result=" + _sayHiToUser__return);


            }

            {
                System.out.println("Invoking getUsers...");
                IntegerUserMap _getUsers__return = port.getUsers();
                System.out.println("getUsers.result=" + _getUsers__return);

                _getUsers__return.getEntry().stream().forEach(entry -> System.out.println(entry.getUser().getFirstName() + " " + entry.getUser().getLastName()));

            }

            System.exit(0);

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
        keyStore.load(new FileInputStream(caStorePath),  keypass.toCharArray());
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
        keyStore.load(new FileInputStream(caStorePath),  keypass.toCharArray());
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
