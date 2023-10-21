package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.adapter.dto.ProfileDTO;
import br.com.tiagoiwamoto.core.usecase.ProfileUsecase;
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

@Path(value = "/v1/api/profiles2")
public class ProfileRest {

    @Inject
    private ProfileUsecase usecase;

    @GET
    public RestResponse<List<ProfileDTO>> index(){
        var dados = this.usecase.listarRegistros();
        return RestResponse.ResponseBuilder.ok(dados, MediaType.APPLICATION_JSON).build();
    }

    @POST
    public RestResponse<ProfileDTO> create(ProfileDTO dados){
        var resposta = this.usecase.gravarRegistro(dados);
        return RestResponse.ResponseBuilder.create(RestResponse.Status.CREATED, resposta).build();
    }

    @PUT
    public RestResponse<ProfileDTO> update(ProfileDTO dados){
        var resposta = this.usecase.atualizarRegistro(dados);
        return RestResponse.ResponseBuilder.ok(resposta).build();
    }

    @DELETE
    public RestResponse delete(ProfileDTO dados){
        this.usecase.deletarRegistro(dados);
        return RestResponse.ResponseBuilder.noContent().build();
    }

}
