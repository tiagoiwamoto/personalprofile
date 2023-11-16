package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.out.ResumeAdapter;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.ResumeMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class ResumeUsecaseTest {

    @Inject
    private ResumeUsecase usecase;

    @InjectMock
    private ResumeAdapter adapter;

    @Test
    void listarRegistros() {
        var dados = ResumeMock.generateDataEntity();
        Mockito.when(this.adapter.all()).thenReturn(List.of(dados));
        var resposta = this.usecase.listarRegistros();

        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getType(), resposta.get(0).getType());
        Assertions.assertEquals(dados.getLanguage(), resposta.get(0).getLanguage());
        Assertions.assertEquals(dados.getEmbed(), resposta.get(0).getEmbed());
        Assertions.assertEquals(dados.getTitle(), resposta.get(0).getTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.get(0).getDescription());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.get(0).getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).all();
    }

    @Test
    void gravarRegistro() {
        var dados = ResumeMock.generateDataEntity();
        var dadosDto = ResumeMock.generateDataDto();
        Mockito.when(this.adapter.save(Mockito.any())).thenReturn(dados);
        var resposta = this.usecase.gravarRegistro(dadosDto);

        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getType(), resposta.getType());
        Assertions.assertEquals(dados.getLanguage(), resposta.getLanguage());
        Assertions.assertEquals(dados.getEmbed(), resposta.getEmbed());
        Assertions.assertEquals(dados.getTitle(), resposta.getTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getDescription());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void atualizarRegistro() {
        var dados = ResumeMock.generateDataEntity();
        var dadosDto = ResumeMock.generateDataDto();
        Mockito.when(this.adapter.update(Mockito.any())).thenReturn(dados);
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(dados);
        var resposta = this.usecase.atualizarRegistro(dadosDto);

        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getType(), resposta.getType());
        Assertions.assertEquals(dados.getLanguage(), resposta.getLanguage());
        Assertions.assertEquals(dados.getEmbed(), resposta.getEmbed());
        Assertions.assertEquals(dados.getTitle(), resposta.getTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getDescription());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    void atualizarRegistroNaoExistente() {
        var dadosDto = ResumeMock.generateDataDto();
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(null);


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.atualizarRegistro(dadosDto)
        );
        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(Mockito.any());
    }

    @Test
    void deletarRegistro() {
        var dados = ResumeMock.generateDataEntity();
        var dadosDto = ResumeMock.generateDataDto();
        Mockito.doNothing().when(this.adapter).delete(Mockito.any());
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(dados);

        this.usecase.deletarRegistro(dadosDto.getUuid());

        Mockito.verify(this.adapter, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void deletarRegistroNaoExistente() {
        var dadosDto = ResumeMock.generateDataDto();
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(null);


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.deletarRegistro(dadosDto.getUuid())
        );
        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(Mockito.any());
    }
}