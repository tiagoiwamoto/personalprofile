package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.core.usecase.ProfileUsecase;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.ProfileMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class ProfileRestTest {

    @Inject
    private ProfileRest rest;
    @InjectMock
    private ProfileUsecase usecase;

    @Test
    void index() {
        var dados = ProfileMock.generateDataDto();
        Mockito.when(this.usecase.listarRegistros()).thenReturn(List.of(dados));
        var resposta = this.rest.index();

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getEntity().get(0).getName());
        Assertions.assertEquals(dados.getTitle(), resposta.getEntity().get(0).getTitle());
        Assertions.assertEquals(dados.getSubTitle(), resposta.getEntity().get(0).getSubTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().get(0).getDescription());
        Assertions.assertEquals(dados.getIsActive(), resposta.getEntity().get(0).getIsActive());
        Assertions.assertEquals(dados.getPhone(), resposta.getEntity().get(0).getPhone());
        Assertions.assertEquals(dados.getEmail(), resposta.getEntity().get(0).getEmail());
        Mockito.verify(this.usecase, Mockito.times(1)).listarRegistros();
    }

    @Test
    void create() {
        var dados = ProfileMock.generateDataDto();
        Mockito.when(this.usecase.gravarRegistro(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.create(dados);

        Assertions.assertEquals(201, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getEntity().getName());
        Assertions.assertEquals(dados.getTitle(), resposta.getEntity().getTitle());
        Assertions.assertEquals(dados.getSubTitle(), resposta.getEntity().getSubTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().getDescription());
        Assertions.assertEquals(dados.getIsActive(), resposta.getEntity().getIsActive());
        Assertions.assertEquals(dados.getPhone(), resposta.getEntity().getPhone());
        Assertions.assertEquals(dados.getEmail(), resposta.getEntity().getEmail());
        Mockito.verify(this.usecase, Mockito.times(1)).gravarRegistro(Mockito.any());
    }

    @Test
    void update() {
        var dados = ProfileMock.generateDataDto();
        Mockito.when(this.usecase.atualizarRegistro(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.update(dados);

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getEntity().getName());
        Assertions.assertEquals(dados.getTitle(), resposta.getEntity().getTitle());
        Assertions.assertEquals(dados.getSubTitle(), resposta.getEntity().getSubTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getEntity().getDescription());
        Assertions.assertEquals(dados.getIsActive(), resposta.getEntity().getIsActive());
        Assertions.assertEquals(dados.getPhone(), resposta.getEntity().getPhone());
        Assertions.assertEquals(dados.getEmail(), resposta.getEntity().getEmail());
        Mockito.verify(this.usecase, Mockito.times(1)).atualizarRegistro(Mockito.any());
    }

    @Test
    void delete() {
        var dados = ProfileMock.generateDataDto();
        Mockito.doNothing().when(this.usecase).deletarRegistro(Mockito.any());
        var resposta = this.rest.delete(dados);

        Assertions.assertEquals(204, resposta.getStatus());
        Mockito.verify(this.usecase, Mockito.times(1)).deletarRegistro(Mockito.any());
    }


}