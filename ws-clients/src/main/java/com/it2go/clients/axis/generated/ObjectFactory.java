
package com.it2go.clients.axis.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.it2go.clients.axis.generated package. 
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

    private final static QName _GetUsersResponseReturn_QNAME = new QName("http://service.it2go.com", "return");
    private final static QName _SayHiToUserUser_QNAME = new QName("http://service.it2go.com", "user");
    private final static QName _SayHiText_QNAME = new QName("http://service.it2go.com", "text");
    private final static QName _UserImplFirstName_QNAME = new QName("http://model.it2go.com/xsd", "firstName");
    private final static QName _UserImplLastName_QNAME = new QName("http://model.it2go.com/xsd", "lastName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.it2go.clients.axis.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Map }
     * 
     */
    public Map createMap() {
        return new Map();
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link SayHiToUser }
     * 
     */
    public SayHiToUser createSayHiToUser() {
        return new SayHiToUser();
    }

    /**
     * Create an instance of {@link UserImpl }
     * 
     */
    public UserImpl createUserImpl() {
        return new UserImpl();
    }

    /**
     * Create an instance of {@link SayHiToUserResponse }
     * 
     */
    public SayHiToUserResponse createSayHiToUserResponse() {
        return new SayHiToUserResponse();
    }

    /**
     * Create an instance of {@link SayHi }
     * 
     */
    public SayHi createSayHi() {
        return new SayHi();
    }

    /**
     * Create an instance of {@link SayHiResponse }
     * 
     */
    public SayHiResponse createSayHiResponse() {
        return new SayHiResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Map }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.it2go.com", name = "return", scope = GetUsersResponse.class)
    public JAXBElement<Map> createGetUsersResponseReturn(Map value) {
        return new JAXBElement<Map>(_GetUsersResponseReturn_QNAME, Map.class, GetUsersResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserImpl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.it2go.com", name = "user", scope = SayHiToUser.class)
    public JAXBElement<UserImpl> createSayHiToUserUser(UserImpl value) {
        return new JAXBElement<UserImpl>(_SayHiToUserUser_QNAME, UserImpl.class, SayHiToUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.it2go.com", name = "return", scope = SayHiToUserResponse.class)
    public JAXBElement<String> createSayHiToUserResponseReturn(String value) {
        return new JAXBElement<String>(_GetUsersResponseReturn_QNAME, String.class, SayHiToUserResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.it2go.com", name = "text", scope = SayHi.class)
    public JAXBElement<String> createSayHiText(String value) {
        return new JAXBElement<String>(_SayHiText_QNAME, String.class, SayHi.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.it2go.com", name = "return", scope = SayHiResponse.class)
    public JAXBElement<String> createSayHiResponseReturn(String value) {
        return new JAXBElement<String>(_GetUsersResponseReturn_QNAME, String.class, SayHiResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.it2go.com/xsd", name = "firstName", scope = UserImpl.class)
    public JAXBElement<String> createUserImplFirstName(String value) {
        return new JAXBElement<String>(_UserImplFirstName_QNAME, String.class, UserImpl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.it2go.com/xsd", name = "lastName", scope = UserImpl.class)
    public JAXBElement<String> createUserImplLastName(String value) {
        return new JAXBElement<String>(_UserImplLastName_QNAME, String.class, UserImpl.class, value);
    }

}
