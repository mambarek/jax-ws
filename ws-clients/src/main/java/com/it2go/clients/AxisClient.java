package com.it2go.clients;

import com.it2go.clients.axis.generated.HelloWorld;
import com.it2go.clients.axis.generated.HelloWorldPortType;
import com.it2go.clients.axis.generated.Map;
import com.it2go.clients.axis.generated.ObjectFactory;
import com.it2go.clients.axis.generated.UserImpl;

import javax.xml.bind.JAXBElement;
import java.net.MalformedURLException;
import java.net.URL;

public class AxisClient {

    public static void main(String[] args){

        try {
            String riUrl = "http://localhost:8080/ri/services/HelloWorld?wsdl";
            String cxfUrl = "http://localhost:8080/cxf/services/helloWorld?wsdl";
            String axis2fUrl = "http://localhost:8080/axis-ws/services/HelloWorld?wsdl";

            URL wsdlLocation = new URL(axis2fUrl);
            HelloWorld service = new HelloWorld(wsdlLocation);
            HelloWorldPortType port = service.getHelloWorldHttpSoap12Endpoint();


            {
                System.out.println("Invoking sayHi...");
                String answ = port.sayHi("Du bester Axis Client!!!");
                System.out.println("sayHi.result = " + answ);


            }

            {
                System.out.println("Invoking sayHiToUser...");
                UserImpl user= new UserImpl();
                JAXBElement<String> fName = new ObjectFactory().createUserImplFirstName("Ali");
                user.setFirstName(fName);

                JAXBElement<String> lName = new ObjectFactory().createUserImplLastName("Mbarek");

                user.setLastName(lName);
                String _sayHiToUser__return = port.sayHiToUser(user);
                System.out.println("sayHiToUser.result = " + _sayHiToUser__return);


            }
/*
            {
                System.out.println("Invoking getUsers...");
                Map map = port.getUsers().getReturn().getValue();
                System.out.println("getUsers.result=" + map);

              //  map.getEntry().stream().forEach(entry -> System.out.println(entry.getUser().getFirstName() + " " + entry.getUser().getLastName()));

            }
*/
            System.exit(0);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
