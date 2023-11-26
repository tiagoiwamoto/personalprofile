package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.out.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.adapter.out.ScholarityAdapter;
import br.com.tiagoiwamoto.adapter.out.dto.ImageDTO;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.ScholarityMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class ScholarityUsecaseTest {

    @Inject
    private ScholarityUsecase usecase;

    @InjectMock
    private ScholarityAdapter adapter;
    @InjectMock
    private ImageAndThumbAdapter image;

    @Test
    void listarRegistros() {
        var dados = ScholarityMock.generateDataEntity();
        Mockito.when(this.adapter.all()).thenReturn(List.of(dados));
        var resposta = this.usecase.listarRegistros();

        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getDuration(), resposta.get(0).getDuration());
        Assertions.assertEquals(dados.getStartDate(), resposta.get(0).getStartDate());
        Assertions.assertEquals(dados.getDateOfConclusion(), resposta.get(0).getDateOfConclusion());
        Assertions.assertEquals(dados.getSchoolName(), resposta.get(0).getSchoolName());
        Assertions.assertEquals(dados.getCourseName(), resposta.get(0).getCourseName());
        Assertions.assertEquals(dados.getTitleReceivedCourse(), resposta.get(0).getTitleReceivedCourse());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.get(0).getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).all();
    }

    @Test
    void gravarRegistro() {
        var dados = ScholarityMock.generateDataEntity();
        var dadosDto = ScholarityMock.generateDataDto();
        Mockito.when(this.adapter.save(Mockito.any())).thenReturn(dados);
        Mockito.when(this.image.storeImage(Mockito.any(), Mockito.any()))
                .thenReturn(new ImageDTO("/tmp/image.png", "/tmp/image_th.png"));
        var resposta = this.usecase.gravarRegistro(dadosDto);

        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getDuration(), resposta.getDuration());
        Assertions.assertEquals(dados.getStartDate(), resposta.getStartDate());
        Assertions.assertEquals(dados.getDateOfConclusion(), resposta.getDateOfConclusion());
        Assertions.assertEquals(dados.getSchoolName(), resposta.getSchoolName());
        Assertions.assertEquals(dados.getCourseName(), resposta.getCourseName());
        Assertions.assertEquals(dados.getTitleReceivedCourse(), resposta.getTitleReceivedCourse());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void atualizarRegistro() {
        var dados = ScholarityMock.generateDataEntity();
        var dadosDto = ScholarityMock.generateDataDto();
        Mockito.when(this.image.storeImage(Mockito.any(), Mockito.any()))
                .thenReturn(new ImageDTO("/tmp/image.png", "/tmp/image_th.png"));
        Mockito.when(this.image.validUpdateOfImage(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(new ImageDTO("/tmp/image.png", "/tmp/image_th.png"));
        Mockito.when(this.adapter.update(Mockito.any())).thenReturn(dados);
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(dados);
        var resposta = this.usecase.atualizarRegistro(dadosDto);

        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getDuration(), resposta.getDuration());
        Assertions.assertEquals(dados.getStartDate(), resposta.getStartDate());
        Assertions.assertEquals(dados.getDateOfConclusion(), resposta.getDateOfConclusion());
        Assertions.assertEquals(dados.getSchoolName(), resposta.getSchoolName());
        Assertions.assertEquals(dados.getCourseName(), resposta.getCourseName());
        Assertions.assertEquals(dados.getTitleReceivedCourse(), resposta.getTitleReceivedCourse());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    void atualizarRegistroNaoExistente() {
        var dadosDto = ScholarityMock.generateDataDto();
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(null);


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.atualizarRegistro(dadosDto)
        );
        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(Mockito.any());
    }

    @Test
    void deletarRegistro() {
        var dados = ScholarityMock.generateDataEntity();
        var dadosDto = ScholarityMock.generateDataDto();
        Mockito.doNothing().when(this.adapter).delete(Mockito.any());
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(dados);

        this.usecase.deletarRegistro(dadosDto.getUuid());

        Mockito.verify(this.adapter, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void deletarRegistroNaoExistente() {
        var dadosDto = ScholarityMock.generateDataDto();
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(null);


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.deletarRegistro(dadosDto.getUuid())
        );
        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(Mockito.any());
    }
}