package com.it2go.clients.cxf.generated;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.5.11
 * 2017-08-09T17:20:27.883+02:00
 * Generated source version: 2.5.11
 * 
 */
@WebService(targetNamespace = "http://service.it2go.com/", name = "HelloWorld")
@XmlSeeAlso({ObjectFactory.class})
public interface HelloWorld {

    @WebMethod
    @RequestWrapper(localName = "getUsers", targetNamespace = "http://service.it2go.com/", className = "com.it2go.clients.cxf.generated.GetUsers")
    @ResponseWrapper(localName = "getUsersResponse", targetNamespace = "http://service.it2go.com/", className = "com.it2go.clients.cxf.generated.GetUsersResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.it2go.clients.cxf.generated.IntegerUserMap getUsers();

    @WebMethod
    @RequestWrapper(localName = "sayHi", targetNamespace = "http://service.it2go.com/", className = "com.it2go.clients.cxf.generated.SayHi")
    @ResponseWrapper(localName = "sayHiResponse", targetNamespace = "http://service.it2go.com/", className = "com.it2go.clients.cxf.generated.SayHiResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String sayHi(
        @WebParam(name = "text", targetNamespace = "")
        java.lang.String text
    );

    @WebMethod
    @RequestWrapper(localName = "sayHiToUser", targetNamespace = "http://service.it2go.com/", className = "com.it2go.clients.cxf.generated.SayHiToUser")
    @ResponseWrapper(localName = "sayHiToUserResponse", targetNamespace = "http://service.it2go.com/", className = "com.it2go.clients.cxf.generated.SayHiToUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String sayHiToUser(
        @WebParam(name = "arg0", targetNamespace = "")
        com.it2go.clients.cxf.generated.User arg0
    );
}
