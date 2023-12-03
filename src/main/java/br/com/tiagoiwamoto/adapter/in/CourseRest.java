package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.adapter.dto.CourseDTO;
import br.com.tiagoiwamoto.adapter.dto.CourseMetricDTO;
import br.com.tiagoiwamoto.core.usecase.CourseUsecase;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Path(value = "/v1/api/courses")
@Authenticated
public class CourseRest {

    @Inject
    private CourseUsecase usecase;

    @GET
    public RestResponse<List<CourseDTO>> index(){
        var dados = this.usecase.listarRegistros();
        return RestResponse.ResponseBuilder.ok(dados, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/category/{uuid}")
    public RestResponse<List<CourseDTO>> index(UUID uuid){
        var dados = this.usecase.listarRegistrosPorCategoria(uuid);
        return RestResponse.ResponseBuilder.ok(dados, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public RestResponse<CourseDTO> create(@Valid CourseDTO dados) throws IOException {
        var resposta = this.usecase.gravarRegistro(dados);
        return RestResponse.ResponseBuilder.create(RestResponse.Status.CREATED, resposta).build();
    }

    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public RestResponse<CourseDTO> update(@Valid CourseDTO dados){
        var resposta = this.usecase.atualizarRegistro(dados);
        return RestResponse.ResponseBuilder.ok(resposta).build();
    }

    @DELETE
    @Path("/{uuid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse delete(UUID uuid){
        this.usecase.deletarRegistro(uuid);
        return RestResponse.ResponseBuilder.noContent().build();
    }

    @GET
    @Path("/metrics/last10courses")
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<List<CourseDTO>> last10courses(){
        var response = this.usecase.top10();
        return RestResponse.ResponseBuilder.ok(response).build();
    }

    @GET
    @Path("/metrics/bycategories")
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<List<CourseMetricDTO>> metrics(){
        var response = this.usecase.getTotalOfCoursesByCategory();
        return RestResponse.ResponseBuilder.ok(response).build();
    }

}
