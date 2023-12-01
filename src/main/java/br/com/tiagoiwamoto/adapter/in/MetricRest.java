package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.core.facade.HomeFacade;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(value = "/metrics")
@Authenticated
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
