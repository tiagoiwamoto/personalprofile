package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.core.facade.HomeFacade;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestQuery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Path(value = "/files")
public class ImageRest {

    @Inject
    private HomeFacade facade;

    @GET
    @Produces({"image/png", "image/jpeg", "image/gif"})
    public Response index(@RestQuery String arquivo){
        String path = "files/";
        System.out.println(path);
        File repositoryFile = new File(path.concat(arquivo));
        try {
            return Response.ok(new FileInputStream(repositoryFile)).build();
        } catch (FileNotFoundException e) {
            return Response.status(404).build();
        }
    }

    @GET
    @Path(value = "/metrics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response metrics(){
        var response = this.facade.metrics();
        return Response.ok(response).build();
    }

}
