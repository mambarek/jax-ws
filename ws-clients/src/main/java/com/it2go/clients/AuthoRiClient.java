package com.it2go.clients;

import com.it2go.clients.ri.generated.HelloWorld;
import com.it2go.clients.ri.generated.IntegerUserMap;
import com.it2go.clients.ri.generated.User;
//import com.sun.xml.ws.developer.JAXWSProperties;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
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
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class AuthoRiClient {

    public static void main(String[] args){

        try {

            //can't parse wsdl "http://localhost:8080/WebServiceExample/user.wsdl" directly
            //save it as local file, and parse it
            String WS_URL = "helloWorld-autho-ri.wsdl";
            QName qname = new QName("http://service.it2go.com/", "HelloWorld");
            URL wsdlLocation = new URL("file:/C:/Dev/learning/webservices/jaxws/ws-clients/src/main/resources/helloWorld-autho-ri.wsdl");

            Service port = Service.create(wsdlLocation, qname);
            HelloWorld helloService = port.getPort(HelloWorld.class);
            //add username and password for container authentication
            BindingProvider bp = (BindingProvider) helloService;
            //bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "mkyong");
            //bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "123456");

            try {
                SSLSocketFactory factory = getSslSocketFactory();
               // bp.getRequestContext().put(JAXWSProperties.SSL_SOCKET_FACTORY, factory);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (UnrecoverableKeyException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

            String answ = helloService.sayHi("Du bester Ri Client!!!");
            System.out.println(answ);

            {
                System.out.println("Invoking sayHi...");
                String _sayHi_text = "your are my best Ri Client!";
                String _sayHi__return = helloService.sayHi(_sayHi_text);
                System.out.println("sayHi.result=" + _sayHi__return);


            }
            {
                System.out.println("Invoking sayHiToUser...");
                User user= new User();
                user.setFirstName("Ali");
                user.setLastName("Mbarek");
                String _sayHiToUser__return = helloService.sayHiToUser(user);
                System.out.println("sayHiToUser.result=" + _sayHiToUser__return);


            }

            {
                System.out.println("Invoking getUsers...");
                IntegerUserMap map = helloService.getUsers();
                System.out.println("getUsers.result=" + map);

                map.getEntry().stream().forEach(entry -> System.out.println(entry.getUser().getFirstName() + " " + entry.getUser().getLastName()));

            }

            System.exit(0);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected static SSLSocketFactory getSslSocketFactory() throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        //SSLContext sc = SSLContext.getInstance("SSLv3");
        SSLContext sc = SSLContext.getInstance("TLS");
        KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        //String jksPath = "c:/dev/certs/bb-4512.jks";
        String jksPath = "C:/Program Files/Java/jdk1.8.0_131/jre/lib/security/cacerts";
        //String jksPath = "C:/Dev/Certs/cacerts";
        keyStore.load(new FileInputStream(jksPath), "changeit".toCharArray());

        factory.init(keyStore, "changeit".toCharArray());

        sc.init(factory.getKeyManagers(), null, null);

        return sc.getSocketFactory();
    }
}
