package net.s4bdigital.api;

/**
 * Created by nataniel.neto on 21/03/2016.
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

//@Path("/mailvalidationservice")
public class MailServiceRest {
    @GET
    @Path("/validate")
    @Produces("application/json")
    public String hello(@QueryParam("email") String name) {
        return null;
    }
}
