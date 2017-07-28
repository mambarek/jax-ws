package com.it2go.clients;

import com.it2go.clients.ri.generated.HelloWorld;
import com.it2go.clients.ri.generated.HelloWorld_Service;
import com.it2go.clients.ri.generated.IntegerUserMap;
import com.it2go.clients.ri.generated.User;

import java.net.MalformedURLException;
import java.net.URL;

public class RiClient {

    public static void main(String[] args){

        try {
            String riUrl = "http://localhost:8080/ri/services/HelloWorld?wsdl";
            String cxfUrl = "http://localhost:8080/cxf/services/helloWorld?wsdl";
            String axis2fUrl = "http://localhost:8080/axis-ws/services/HelloWorld?wsdl";

            URL wsdlLocation = new URL(riUrl);
            HelloWorld_Service service = new HelloWorld_Service(wsdlLocation);
            String answ = service.getHelloWorldImplPort().sayHi("Du bester Ri Client!!!");
            System.out.println(answ);

            HelloWorld port = service.getHelloWorldImplPort();

            {
                System.out.println("Invoking sayHi...");
                java.lang.String _sayHi_text = "your are my best Ri Client!";
                java.lang.String _sayHi__return = port.sayHi(_sayHi_text);
                System.out.println("sayHi.result=" + _sayHi__return);


            }
            {
                System.out.println("Invoking sayHiToUser...");
                User user= new User();
                user.setFirstName("Ali");
                user.setLastName("Mbarek");
                java.lang.String _sayHiToUser__return = port.sayHiToUser(user);
                System.out.println("sayHiToUser.result=" + _sayHiToUser__return);


            }

            {
                System.out.println("Invoking getUsers...");
                IntegerUserMap map = port.getUsers();
                System.out.println("getUsers.result=" + map);

                map.getEntry().stream().forEach(entry -> System.out.println(entry.getUser().getFirstName() + " " + entry.getUser().getLastName()));

            }

            System.exit(0);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
