package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.adapter.dto.ProjectDTO;
import br.com.tiagoiwamoto.core.usecase.ProjectUsecase;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;
import java.util.UUID;

@Path(value = "/v1/api/projects")
@Authenticated
public class ProjectRest {

    @Inject
    private ProjectUsecase usecase;

    @GET
    public RestResponse<List<ProjectDTO>> index(){
        var dados = this.usecase.listarRegistros();
        return RestResponse.ResponseBuilder.ok(dados, MediaType.APPLICATION_JSON).build();
    }

    @POST
    public RestResponse<ProjectDTO> create(ProjectDTO dados){
        var resposta = this.usecase.gravarRegistro(dados);
        return RestResponse.ResponseBuilder.create(RestResponse.Status.CREATED, resposta).build();
    }

    @PUT
    public RestResponse<ProjectDTO> update(ProjectDTO dados){
        var resposta = this.usecase.atualizarRegistro(dados);
        return RestResponse.ResponseBuilder.ok(resposta).build();
    }

    @DELETE
    @Path("/{uuid}")
    public RestResponse delete(UUID uuid){
        this.usecase.deletarRegistro(uuid);
        return RestResponse.ResponseBuilder.noContent().build();
    }

}
