package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.out.ProfileAdapter;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.ProfileMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class ProfileUsecaseTest {

    @Inject
    private ProfileUsecase usecase;

    @InjectMock
    private ProfileAdapter adapter;

    @Test
    void listarRegistros() {
        var dados = ProfileMock.generateDataEntity();
        Mockito.when(this.adapter.all()).thenReturn(List.of(dados));
        var resposta = this.usecase.listarRegistros();

        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.get(0).getName());
        Assertions.assertEquals(dados.getTitle(), resposta.get(0).getTitle());
        Assertions.assertEquals(dados.getSubTitle(), resposta.get(0).getSubTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.get(0).getDescription());
        Assertions.assertEquals(dados.getIsActive(), resposta.get(0).getIsActive());
        Assertions.assertEquals(dados.getPhone(), resposta.get(0).getPhone());
        Assertions.assertEquals(dados.getEmail(), resposta.get(0).getEmail());
        Mockito.verify(this.adapter, Mockito.times(1)).all();
    }

    @Test
    void gravarRegistro() {
    }

    @Test
    void atualizarRegistro() {
    }

    @Test
    void deletarRegistro() {
    }
}