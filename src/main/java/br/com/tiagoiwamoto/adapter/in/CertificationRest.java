package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.adapter.dto.CertificationDTO;
import br.com.tiagoiwamoto.core.usecase.CertificationUsecase;
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

@Path(value = "/v1/api/certifications")
@Authenticated
public class CertificationRest {

    @Inject
    private CertificationUsecase usecase;

    @GET
    public RestResponse<List<CertificationDTO>> index(){
        var dados = this.usecase.listarRegistros();
        return RestResponse.ResponseBuilder.ok(dados, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public RestResponse<CertificationDTO> create(@Valid CertificationDTO dados) throws IOException {
        var resposta = this.usecase.gravarRegistro(dados);
        return RestResponse.ResponseBuilder.create(RestResponse.Status.CREATED, resposta).build();
    }

    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public RestResponse<CertificationDTO> update(@Valid CertificationDTO dados){
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

}
