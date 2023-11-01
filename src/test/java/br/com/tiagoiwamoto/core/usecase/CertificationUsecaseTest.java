package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.out.CertificationAdapter;
import br.com.tiagoiwamoto.adapter.out.ProfileAdapter;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.CertificationMock;
import mock.ProfileMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class CertificationUsecaseTest {

    @Inject
    private CertificationUsecase usecase;

    @InjectMock
    private CertificationAdapter adapter;

    @Test
    void listarRegistros() {
        var dados = CertificationMock.generateDataEntity();
        Mockito.when(this.adapter.all()).thenReturn(List.of(dados));
        var resposta = this.usecase.listarRegistros();

        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.get(0).getName());
        Assertions.assertEquals(dados.getEarnDate(), resposta.get(0).getEarnDate());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.get(0).getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.get(0).getPathOfImageThumb());
        Assertions.assertEquals(dados.getValidateUrl(), resposta.get(0).getValidateUrl());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.get(0).getUpdatedAt());
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