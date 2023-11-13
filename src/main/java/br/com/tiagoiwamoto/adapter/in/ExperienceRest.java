package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.adapter.dto.ExperienceDTO;
import br.com.tiagoiwamoto.core.usecase.ExperienceUsecase;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path(value = "/v1/api/experiences")
public class ExperienceRest {

    @Inject
    private ExperienceUsecase usecase;

    @GET
    public RestResponse<List<ExperienceDTO>> index(){
        var dados = this.usecase.listarRegistros();
        return RestResponse.ResponseBuilder.ok(dados, MediaType.APPLICATION_JSON).build();
    }

    @POST
    public RestResponse<ExperienceDTO> create(ExperienceDTO dados){
        var resposta = this.usecase.gravarRegistro(dados);
        return RestResponse.ResponseBuilder.create(RestResponse.Status.CREATED, resposta).build();
    }

    @PUT
    public RestResponse<ExperienceDTO> update(ExperienceDTO dados){
        var resposta = this.usecase.atualizarRegistro(dados);
        return RestResponse.ResponseBuilder.ok(resposta).build();
    }

    @DELETE
    public RestResponse delete(ExperienceDTO dados){
        this.usecase.deletarRegistro(dados);
        return RestResponse.ResponseBuilder.noContent().build();
    }

}
