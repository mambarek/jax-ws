package com.it2go.service;

import com.it2go.model.IntegerUserMapAdapter;
import com.it2go.model.User;
import com.it2go.model.UserImpl;
import org.apache.cxf.annotations.Policy;
//import org.apache.cxf.annotations.Policy;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

// START SNIPPET: service

@WebService
@Policy(placement = Policy.Placement.BINDING, uri = "classpath:/UsernameToken.policy")
public interface HelloWorld {

    String sayHi(@WebParam(name = "text") String text);


    /* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
     * support interfaces directly.  Special XmlAdapter classes need to
     * be written to handle them
     */
    String sayHiToUser(UserImpl user);


    /* Map passing
     * JAXB also does not support Maps.  It handles Lists great, but Maps are
     * not supported directly.  They also require use of a XmlAdapter to map
     * the maps into beans that JAXB can use.
     */
    @XmlJavaTypeAdapter(IntegerUserMapAdapter.class)
    Map<Integer, User> getUsers();
}
// END SNIPPET: service