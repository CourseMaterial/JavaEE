package be.rubus.security.workshop.jwt;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 */
@Path("/protected/secure")
@Singleton
public class SecureEnd {

    @Inject
    private UserToken userToken;

    @GET
    public String test() {
        return userToken.getSubject();
    }

}
