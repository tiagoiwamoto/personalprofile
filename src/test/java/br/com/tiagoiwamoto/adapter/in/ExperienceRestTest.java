package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.core.usecase.ExperienceUsecase;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.ExperienceMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class ExperienceRestTest {

    @Inject
    private ExperienceRest rest;
    @InjectMock
    private ExperienceUsecase usecase;

    @Test
    void index() {
        var dados = ExperienceMock.generateDataDto();
        Mockito.when(this.usecase.listarRegistros()).thenReturn(List.of(dados));
        var resposta = this.rest.index();

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().get(0).getUuid());
        Assertions.assertEquals(dados.getJob(), resposta.getEntity().get(0).getJob());
        Assertions.assertEquals(dados.getCompany(), resposta.getEntity().get(0).getCompany());
        Assertions.assertEquals(dados.getStartDate(), resposta.getEntity().get(0).getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.getEntity().get(0).getEndDate());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().get(0).getDescription());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getEntity().get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getEntity().get(0).getUpdatedAt());
        Mockito.verify(this.usecase, Mockito.times(1)).listarRegistros();
    }

    @Test
    void create() {
        var dados = ExperienceMock.generateDataDto();
        Mockito.when(this.usecase.gravarRegistro(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.create(dados);

        Assertions.assertEquals(201, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getJob(), resposta.getEntity().getJob());
        Assertions.assertEquals(dados.getCompany(), resposta.getEntity().getCompany());
        Assertions.assertEquals(dados.getStartDate(), resposta.getEntity().getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.getEntity().getEndDate());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().getDescription());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getEntity().getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getEntity().getUpdatedAt());
        Mockito.verify(this.usecase, Mockito.times(1)).gravarRegistro(Mockito.any());
    }

    @Test
    void update() {
        var dados = ExperienceMock.generateDataDto();
        Mockito.when(this.usecase.atualizarRegistro(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.update(dados);

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getJob(), resposta.getEntity().getJob());
        Assertions.assertEquals(dados.getCompany(), resposta.getEntity().getCompany());
        Assertions.assertEquals(dados.getStartDate(), resposta.getEntity().getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.getEntity().getEndDate());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().getDescription());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getEntity().getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getEntity().getUpdatedAt());
        Mockito.verify(this.usecase, Mockito.times(1)).atualizarRegistro(Mockito.any());
    }

    @Test
    void delete() {
        var dados = ExperienceMock.generateDataDto();
        Mockito.doNothing().when(this.usecase).deletarRegistro(Mockito.any());
        var resposta = this.rest.delete(dados);

        Assertions.assertEquals(204, resposta.getStatus());
        Mockito.verify(this.usecase, Mockito.times(1)).deletarRegistro(Mockito.any());
    }


}