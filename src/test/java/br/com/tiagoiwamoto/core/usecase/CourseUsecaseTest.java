package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.out.CourseAdapter;
import br.com.tiagoiwamoto.adapter.out.CourseCategoryAdapter;
import br.com.tiagoiwamoto.adapter.out.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.adapter.out.dto.ImageDTO;
import br.com.tiagoiwamoto.core.entity.CourseEntity;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.CourseCategoryMock;
import mock.CourseMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

@QuarkusTest
class CourseUsecaseTest {

    @Inject
    private CourseUsecase usecase;

    @InjectMock
    private CourseAdapter adapter;
    @InjectMock
    private CourseCategoryAdapter courseCategoryAdapter;
    @InjectMock
    private ImageAndThumbAdapter image;

    @Test
    void listarRegistros() {
        var dados = CourseMock.generateDataEntity();
        Mockito.when(this.adapter.all()).thenReturn(List.of(dados));
        var resposta = this.usecase.listarRegistros();

        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.get(0).getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.get(0).getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.get(0).getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.get(0).getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.get(0).getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.get(0).getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.get(0).getEndDate());
        Assertions.assertEquals(dados.getCourseCategory().getUuid(), resposta.get(0).getCourseCategoryUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.get(0).getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).all();
    }

    @Test
    void listarRegistrosPorCategoria() {
        var dados = CourseMock.generateDataEntity();
        var categoryDados = CourseCategoryMock.generateDataEntity();
        Mockito.when(this.adapter.allByCategory(Mockito.any())).thenReturn(List.of(dados));
        Mockito.when(this.courseCategoryAdapter.recoveryByUuid(Mockito.any())).thenReturn(categoryDados);
        var resposta = this.usecase.listarRegistrosPorCategoria(dados.getUuid());

        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.get(0).getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.get(0).getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.get(0).getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.get(0).getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.get(0).getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.get(0).getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.get(0).getEndDate());
        Assertions.assertEquals(dados.getCourseCategory().getUuid(), resposta.get(0).getCourseCategoryUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.get(0).getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).allByCategory(Mockito.any());
    }

    @Test
    void listarRegistrosPorCategoriaNaoExistente() {
        var dados = CourseMock.generateDataEntity();
        Mockito.when(this.courseCategoryAdapter.recoveryByUuid(dados.getUuid())).thenReturn(null);


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.listarRegistrosPorCategoria(dados.getUuid())
        );
//        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(dados.getUuid());
    }

    @Test
    void gravarRegistro() {
        var dados = CourseMock.generateDataEntity();
        var dadosDto = CourseMock.generateDataDto();
        Mockito.when(this.image.storeImage(Mockito.any(), Mockito.any()))
                .thenReturn(new ImageDTO("/tmp/image.png", "/tmp/image_th.png"));
        Mockito.when(this.adapter.save(Mockito.any()))
                .thenReturn(dados);

        var resposta = this.usecase.gravarRegistro(dadosDto);

        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.getEndDate());
        Assertions.assertEquals(dados.getCourseCategory().getUuid(), resposta.getCourseCategoryUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void atualizarRegistro() {
        var dados = CourseMock.generateDataEntity();
        var dadosDto = CourseMock.generateDataDto();
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
        Assertions.assertEquals(dados.getPathOfImage(), resposta.getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.getEndDate());
        Assertions.assertEquals(dados.getCourseCategory().getUuid(), resposta.getCourseCategoryUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    void atualizarRegistroNaoExistente() {
        var dadosDto = CourseMock.generateDataDto();

        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(Mockito.nullable(CourseEntity.class));


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.atualizarRegistro(dadosDto)
        );
        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(Mockito.any());
    }

    @Test
    void deletarRegistro() {
        var dados = CourseMock.generateDataEntity();
        Mockito.when(this.image.storeImage(Mockito.any(), Mockito.any()))
                .thenReturn(new ImageDTO("/tmp/image.png", "/tmp/image_th.png"));
        Mockito.doNothing().when(this.adapter).delete(Mockito.any());
        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(dados);
        Mockito.doNothing().when(this.image).removeFiles(Mockito.any());

        this.usecase.deletarRegistro(dados.getUuid());

        Mockito.verify(this.adapter, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void deletarRegistroNaoExistente() {

        Mockito.when(this.adapter.recoveryByUuid(Mockito.any())).thenReturn(null);


        Assertions.assertThrows(
                RuntimeException.class, () -> this.usecase.deletarRegistro(Mockito.any(UUID.class))
        );
        Mockito.verify(this.adapter, Mockito.times(1)).recoveryByUuid(Mockito.any());
    }

    @Test
    void top10() {
        var dados = CourseMock.generateDataEntity();
        Mockito.when(this.adapter.top10()).thenReturn(List.of(dados));
        var resposta = this.usecase.top10();

        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.get(0).getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.get(0).getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.get(0).getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.get(0).getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.get(0).getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.get(0).getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.get(0).getEndDate());
        Assertions.assertEquals(dados.getCourseCategory().getUuid(), resposta.get(0).getCourseCategoryUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.get(0).getUpdatedAt());
        Mockito.verify(this.adapter, Mockito.times(1)).top10();
    }

}