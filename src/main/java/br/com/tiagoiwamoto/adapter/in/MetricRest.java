package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.core.facade.HomeFacade;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(value = "/metrics")
@PermitAll
public class MetricRest {

    @Inject
    private HomeFacade facade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(){
        var response = this.facade.metrics();
        return Response.ok(response).build();
    }
}
