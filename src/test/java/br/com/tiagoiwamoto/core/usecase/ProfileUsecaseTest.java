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
        var dados = ProfileMock.generateDataEntity();
        var dadosDto = ProfileMock.generateDataDto();
        Mockito.when(this.adapter.save(Mockito.any())).thenReturn(dados);
        var resposta = this.usecase.gravarRegistro(dadosDto);

        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getTitle(), resposta.getTitle());
        Assertions.assertEquals(dados.getSubTitle(), resposta.getSubTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getDescription());
        Assertions.assertEquals(dados.getIsActive(), resposta.getIsActive());
        Assertions.assertEquals(dados.getPhone(), resposta.getPhone());
        Assertions.assertEquals(dados.getEmail(), resposta.getEmail());
        Mockito.verify(this.adapter, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void atualizarRegistro() {
        var dados = ProfileMock.generateDataEntity();
        var dadosDto = ProfileMock.generateDataDto();
        Mockito.when(this.adapter.update(Mockito.any())).thenReturn(dados);
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(dados);
        var resposta = this.usecase.atualizarRegistro(dadosDto);

        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getTitle(), resposta.getTitle());
        Assertions.assertEquals(dados.getSubTitle(), resposta.getSubTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getDescription());
        Assertions.assertEquals(dados.getIsActive(), resposta.getIsActive());
        Assertions.assertEquals(dados.getPhone(), resposta.getPhone());
        Assertions.assertEquals(dados.getEmail(), resposta.getEmail());
        Mockito.verify(this.adapter, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    void atualizarRegistroNaoExistente() {
        var dadosDto = ProfileMock.generateDataDto();
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(null);


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.atualizarRegistro(dadosDto)
        );
        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(Mockito.any());
    }

    @Test
    void deletarRegistro() {
        var dados = ProfileMock.generateDataEntity();
        var dadosDto = ProfileMock.generateDataDto();
        Mockito.doNothing().when(this.adapter).delete(Mockito.any());
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(dados);

        this.usecase.deletarRegistro(dadosDto.getUuid());

        Mockito.verify(this.adapter, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void deletarRegistroNaoExistente() {
        var dadosDto = ProfileMock.generateDataDto();
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(null);


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.deletarRegistro(dadosDto.getUuid())
        );
        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(Mockito.any());
    }
}