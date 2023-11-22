package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.core.usecase.CourseCategoryUsecase;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import mock.CourseCategoryMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class CourseCategoryRestTest {

    @Inject
    private CourseCategoryRest rest;
    @InjectMock
    private CourseCategoryUsecase usecase;

    @Test
    @TestSecurity(authorizationEnabled = false)
    void index() {
        var dados = CourseCategoryMock.generateDataDto();
        Mockito.when(this.usecase.listarRegistros()).thenReturn(List.of(dados));
        var resposta = this.rest.index();

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getEntity().get(0).getName());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().get(0).getDescription());
        Mockito.verify(this.usecase, Mockito.times(1)).listarRegistros();
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void indexByUuid() {
        var dados = CourseCategoryMock.generateDataDto();
        Mockito.when(this.usecase.listarRegistroPorUuid(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.indexByUuid(dados.getUuid());

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getEntity().getName());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().getDescription());
        Mockito.verify(this.usecase, Mockito.times(1)).listarRegistroPorUuid(dados.getUuid());
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void create() {
        var dados = CourseCategoryMock.generateDataDto();
        Mockito.when(this.usecase.gravarRegistro(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.create(dados);

        Assertions.assertEquals(201, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getEntity().getName());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().getDescription());
        Mockito.verify(this.usecase, Mockito.times(1)).gravarRegistro(Mockito.any());
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void update() {
        var dados = CourseCategoryMock.generateDataDto();
        Mockito.when(this.usecase.atualizarRegistro(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.update(dados);

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getEntity().getName());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().getDescription());
        Mockito.verify(this.usecase, Mockito.times(1)).atualizarRegistro(Mockito.any());
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void delete() {
        var dados = CourseCategoryMock.generateDataDto();
        Mockito.doNothing().when(this.usecase).deletarRegistro(Mockito.any());
        var resposta = this.rest.delete(dados.getUuid());

        Assertions.assertEquals(204, resposta.getStatus());
        Mockito.verify(this.usecase, Mockito.times(1)).deletarRegistro(Mockito.any());
    }


}