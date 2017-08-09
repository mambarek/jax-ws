
package com.it2go.clients.cxf.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.it2go.clients.cxf.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SayHiResponse_QNAME = new QName("http://service.it2go.com/", "sayHiResponse");
    private final static QName _SayHiToUserResponse_QNAME = new QName("http://service.it2go.com/", "sayHiToUserResponse");
    private final static QName _GetUsers_QNAME = new QName("http://service.it2go.com/", "getUsers");
    private final static QName _SayHiToUser_QNAME = new QName("http://service.it2go.com/", "sayHiToUser");
    private final static QName _GetUsersResponse_QNAME = new QName("http://service.it2go.com/", "getUsersResponse");
    private final static QName _SayHi_QNAME = new QName("http://service.it2go.com/", "sayHi");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.it2go.clients.cxf.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayHiToUserResponse }
     * 
     */
    public SayHiToUserResponse createSayHiToUserResponse() {
        return new SayHiToUserResponse();
    }

    /**
     * Create an instance of {@link GetUsers }
     * 
     */
    public GetUsers createGetUsers() {
        return new GetUsers();
    }

    /**
     * Create an instance of {@link IdentifiedUser }
     * 
     */
    public IdentifiedUser createIdentifiedUser() {
        return new IdentifiedUser();
    }

    /**
     * Create an instance of {@link SayHiToUser }
     * 
     */
    public SayHiToUser createSayHiToUser() {
        return new SayHiToUser();
    }

    /**
     * Create an instance of {@link SayHiResponse }
     * 
     */
    public SayHiResponse createSayHiResponse() {
        return new SayHiResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link SayHi }
     * 
     */
    public SayHi createSayHi() {
        return new SayHi();
    }

    /**
     * Create an instance of {@link IntegerUserMap }
     * 
     */
    public IntegerUserMap createIntegerUserMap() {
        return new IntegerUserMap();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.it2go.com/", name = "sayHiResponse")
    public JAXBElement<SayHiResponse> createSayHiResponse(SayHiResponse value) {
        return new JAXBElement<SayHiResponse>(_SayHiResponse_QNAME, SayHiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHiToUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.it2go.com/", name = "sayHiToUserResponse")
    public JAXBElement<SayHiToUserResponse> createSayHiToUserResponse(SayHiToUserResponse value) {
        return new JAXBElement<SayHiToUserResponse>(_SayHiToUserResponse_QNAME, SayHiToUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.it2go.com/", name = "getUsers")
    public JAXBElement<GetUsers> createGetUsers(GetUsers value) {
        return new JAXBElement<GetUsers>(_GetUsers_QNAME, GetUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHiToUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.it2go.com/", name = "sayHiToUser")
    public JAXBElement<SayHiToUser> createSayHiToUser(SayHiToUser value) {
        return new JAXBElement<SayHiToUser>(_SayHiToUser_QNAME, SayHiToUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.it2go.com/", name = "getUsersResponse")
    public JAXBElement<GetUsersResponse> createGetUsersResponse(GetUsersResponse value) {
        return new JAXBElement<GetUsersResponse>(_GetUsersResponse_QNAME, GetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.it2go.com/", name = "sayHi")
    public JAXBElement<SayHi> createSayHi(SayHi value) {
        return new JAXBElement<SayHi>(_SayHi_QNAME, SayHi.class, null, value);
    }

}
