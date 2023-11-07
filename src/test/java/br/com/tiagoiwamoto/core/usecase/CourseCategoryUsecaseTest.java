package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.out.CourseCategoryAdapter;
import br.com.tiagoiwamoto.adapter.out.ProfileAdapter;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.CourseCategoryMock;
import mock.ProfileMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class CourseCategoryUsecaseTest {

    @Inject
    private CourseCategoryUsecase usecase;

    @InjectMock
    private CourseCategoryAdapter adapter;

    @Test
    void listarRegistros() {
        var dados = CourseCategoryMock.generateDataEntity();
        Mockito.when(this.adapter.all()).thenReturn(List.of(dados));
        var resposta = this.usecase.listarRegistros();

        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.get(0).getName());
        Assertions.assertEquals(dados.getDescription(), resposta.get(0).getDescription());
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