package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.adapter.dto.CourseCategoryDTO;
import br.com.tiagoiwamoto.core.usecase.CourseCategoryUsecase;
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

@Path(value = "/v1/api/courses_categories")
@Authenticated
public class CourseCategoryRest {

    @Inject
    private CourseCategoryUsecase usecase;

    @GET
    public RestResponse<List<CourseCategoryDTO>> index(){
        var dados = this.usecase.listarRegistros();
        return RestResponse.ResponseBuilder.ok(dados, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{uuid}")
    public RestResponse<CourseCategoryDTO> indexByUuid(UUID uuid){
        var dados = this.usecase.listarRegistroPorUuid(uuid);
        return RestResponse.ResponseBuilder.ok(dados, MediaType.APPLICATION_JSON).build();
    }

    @POST
    public RestResponse<CourseCategoryDTO> create(CourseCategoryDTO dados){
        var resposta = this.usecase.gravarRegistro(dados);
        return RestResponse.ResponseBuilder.create(RestResponse.Status.CREATED, resposta).build();
    }

    @PUT
    public RestResponse<CourseCategoryDTO> update(CourseCategoryDTO dados){
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
