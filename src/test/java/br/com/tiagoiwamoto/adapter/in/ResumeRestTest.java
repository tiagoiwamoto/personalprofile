package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.core.usecase.ResumeUsecase;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import mock.ResumeMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class ResumeRestTest {

    @Inject
    private ResumeRest rest;
    @InjectMock
    private ResumeUsecase usecase;

    @Test
    @TestSecurity(authorizationEnabled = false)
    void index() {
        var dados = ResumeMock.generateDataDto();
        Mockito.when(this.usecase.listarRegistros()).thenReturn(List.of(dados));
        var resposta = this.rest.index();

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().get(0).getUuid());
        Assertions.assertEquals(dados.getType(), resposta.getEntity().get(0).getType());
        Assertions.assertEquals(dados.getLanguage(), resposta.getEntity().get(0).getLanguage());
        Assertions.assertEquals(dados.getEmbed(), resposta.getEntity().get(0).getEmbed());
        Assertions.assertEquals(dados.getTitle(), resposta.getEntity().get(0).getTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().get(0).getDescription());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getEntity().get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getEntity().get(0).getUpdatedAt());
        Mockito.verify(this.usecase, Mockito.times(1)).listarRegistros();
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void create() {
        var dados = ResumeMock.generateDataDto();
        Mockito.when(this.usecase.gravarRegistro(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.create(dados);

        Assertions.assertEquals(201, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getType(), resposta.getEntity().getType());
        Assertions.assertEquals(dados.getLanguage(), resposta.getEntity().getLanguage());
        Assertions.assertEquals(dados.getEmbed(), resposta.getEntity().getEmbed());
        Assertions.assertEquals(dados.getTitle(), resposta.getEntity().getTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().getDescription());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getEntity().getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getEntity().getUpdatedAt());
        Mockito.verify(this.usecase, Mockito.times(1)).gravarRegistro(Mockito.any());
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void update() {
        var dados = ResumeMock.generateDataDto();
        Mockito.when(this.usecase.atualizarRegistro(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.update(dados);

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getType(), resposta.getEntity().getType());
        Assertions.assertEquals(dados.getLanguage(), resposta.getEntity().getLanguage());
        Assertions.assertEquals(dados.getEmbed(), resposta.getEntity().getEmbed());
        Assertions.assertEquals(dados.getTitle(), resposta.getEntity().getTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().getDescription());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getEntity().getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getEntity().getUpdatedAt());
        Mockito.verify(this.usecase, Mockito.times(1)).atualizarRegistro(Mockito.any());
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void delete() {
        var dados = ResumeMock.generateDataDto();
        Mockito.doNothing().when(this.usecase).deletarRegistro(Mockito.any());
        var resposta = this.rest.delete(dados.getUuid());

        Assertions.assertEquals(204, resposta.getStatus());
        Mockito.verify(this.usecase, Mockito.times(1)).deletarRegistro(Mockito.any());
    }


}