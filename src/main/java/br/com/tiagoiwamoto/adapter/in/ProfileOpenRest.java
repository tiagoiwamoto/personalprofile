package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.adapter.dto.OpenProfileResponse;
import br.com.tiagoiwamoto.core.facade.HomeFacade;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

@Path(value = "/metrics")
public class ProfileOpenRest {

    @Inject
    private HomeFacade facade;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<OpenProfileResponse> index(){
        var response = this.facade.metrics();
        return RestResponse.ResponseBuilder.ok(response, MediaType.APPLICATION_JSON).build();
    }
}
