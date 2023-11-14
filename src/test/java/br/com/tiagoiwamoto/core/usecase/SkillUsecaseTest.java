package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.out.SkillAdapter;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.SkillMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class SkillUsecaseTest {

    @Inject
    private SkillUsecase usecase;

    @InjectMock
    private SkillAdapter adapter;

    @Test
    void listarRegistros() {
        var dados = SkillMock.generateDataEntity();
        Mockito.when(this.adapter.all()).thenReturn(List.of(dados));
        var resposta = this.usecase.listarRegistros();

        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getCategory(), resposta.get(0).getCategory());
        Assertions.assertEquals(dados.getHabilities(), resposta.get(0).getHabilities());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.get(0).getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).all();
    }

    @Test
    void gravarRegistro() {
        var dados = SkillMock.generateDataEntity();
        var dadosDto = SkillMock.generateDataDto();
        Mockito.when(this.adapter.save(Mockito.any())).thenReturn(dados);
        var resposta = this.usecase.gravarRegistro(dadosDto);

        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getCategory(), resposta.getCategory());
        Assertions.assertEquals(dados.getHabilities(), resposta.getHabilities());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void atualizarRegistro() {
        var dados = SkillMock.generateDataEntity();
        var dadosDto = SkillMock.generateDataDto();
        Mockito.when(this.adapter.update(Mockito.any())).thenReturn(dados);
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(dados);
        var resposta = this.usecase.atualizarRegistro(dadosDto);

        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getCategory(), resposta.getCategory());
        Assertions.assertEquals(dados.getHabilities(), resposta.getHabilities());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    void atualizarRegistroNaoExistente() {
        var dadosDto = SkillMock.generateDataDto();
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(null);


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.atualizarRegistro(dadosDto)
        );
        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(Mockito.any());
    }

    @Test
    void deletarRegistro() {
        var dados = SkillMock.generateDataEntity();
        var dadosDto = SkillMock.generateDataDto();
        Mockito.doNothing().when(this.adapter).delete(Mockito.any());
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(dados);

        this.usecase.deletarRegistro(dadosDto.getUuid());

        Mockito.verify(this.adapter, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void deletarRegistroNaoExistente() {
        var dadosDto = SkillMock.generateDataDto();
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(null);


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.deletarRegistro(dadosDto.getUuid())
        );
        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(Mockito.any());
    }
}