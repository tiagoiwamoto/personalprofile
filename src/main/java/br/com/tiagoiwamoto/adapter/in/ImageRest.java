package br.com.tiagoiwamoto.adapter.in;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestQuery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Path(value = "/files")
public class ImageRest {

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

}
