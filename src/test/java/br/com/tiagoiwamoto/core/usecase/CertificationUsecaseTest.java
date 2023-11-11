package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.out.CertificationAdapter;
import br.com.tiagoiwamoto.adapter.out.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.adapter.out.dto.ImageDTO;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.CertificationMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

@QuarkusTest
class CertificationUsecaseTest {

    @Inject
    private CertificationUsecase usecase;

    @InjectMock
    private CertificationAdapter adapter;

    @InjectMock
    private ImageAndThumbAdapter image;

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
        var dados = CertificationMock.generateDataEntity();
        var dadosDto = CertificationMock.generateDataDto();
        Mockito.when(this.image.storeImage(Mockito.any(), Mockito.any()))
                .thenReturn(new ImageDTO("/tmp/image.png", "/tmp/image_th.png"));
        Mockito.when(this.adapter.save(Mockito.any()))
                .thenReturn(dados);

        var resposta = this.usecase.gravarRegistro(dadosDto);

        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getEarnDate(), resposta.getEarnDate());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.getPathOfImageThumb());
        Assertions.assertEquals(dados.getValidateUrl(), resposta.getValidateUrl());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void atualizarRegistro() {
        var dados = CertificationMock.generateDataEntity();
        var dadosDto = CertificationMock.generateDataDto();
        Mockito.when(this.image.storeImage(Mockito.any(), Mockito.any()))
                .thenReturn(new ImageDTO("/tmp/image.png", "/tmp/image_th.png"));
        Mockito.when(this.adapter.update(Mockito.any()))
                .thenReturn(dados);
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(dados);
        Mockito.when(this.image.validUpdateOfImage(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(new ImageDTO("/tmp/image.png", "/tmp/image_th.png"));

        var resposta = this.usecase.atualizarRegistro(dadosDto);

        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getEarnDate(), resposta.getEarnDate());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.getPathOfImageThumb());
        Assertions.assertEquals(dados.getValidateUrl(), resposta.getValidateUrl());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    void atualizarRegistroNaoExistente() {
        var dadosDto = CertificationMock.generateDataDto();

        Mockito.when(this.adapter.recoveryByUuid(Mockito.any()))
                .thenReturn(null);


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.atualizarRegistro(dadosDto)
        );
        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(Mockito.any());
    }

    @Test
    void deletarRegistro() {
        var dados = CertificationMock.generateDataEntity();
        Mockito.when(this.image.storeImage(Mockito.any(), Mockito.any()))
                .thenReturn(new ImageDTO("/tmp/image.png", "/tmp/image_th.png"));
        Mockito.doNothing().when(this.adapter).delete(Mockito.any());
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(dados);
        Mockito.doNothing().when(this.image).removeFiles(Mockito.any());

        this.usecase.deletarRegistro(Mockito.any(UUID.class));

        Mockito.verify(this.adapter, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void deletarRegistroNaoExistente() {

        Mockito.when(this.adapter.recoveryByUuid(Mockito.any(UUID.class)))
                .thenReturn(null);


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.deletarRegistro(Mockito.any(UUID.class))
        );
        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(Mockito.any());
    }
}