package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.core.usecase.CourseUsecase;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.CourseMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@QuarkusTest
class CourseRestTest {

    @Inject
    private CourseRest rest;
    @InjectMock
    private CourseUsecase usecase;

    @Test
    void index() {
        var dados = CourseMock.generateDataDto();
        Mockito.when(this.usecase.listarRegistros()).thenReturn(List.of(dados));
        var resposta = this.rest.index();

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getEntity().get(0).getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.getEntity().get(0).getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.getEntity().get(0).getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.getEntity().get(0).getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.getEntity().get(0).getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.getEntity().get(0).getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.getEntity().get(0).getEndDate());
        Assertions.assertEquals(dados.getCourseCategoryUuid(), resposta.getEntity().get(0).getCourseCategoryUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getEntity().get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getEntity().get(0).getUpdatedAt());
        Mockito.verify(this.usecase, Mockito.times(1)).listarRegistros();
    }

    @Test
    void create() throws IOException {
        var dados = CourseMock.generateDataDto();
        Mockito.when(this.usecase.gravarRegistro(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.create(dados);

        Assertions.assertEquals(201, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getEntity().getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.getEntity().getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.getEntity().getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.getEntity().getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.getEntity().getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.getEntity().getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.getEntity().getEndDate());
        Assertions.assertEquals(dados.getCourseCategoryUuid(), resposta.getEntity().getCourseCategoryUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getEntity().getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getEntity().getUpdatedAt());
        Mockito.verify(this.usecase, Mockito.times(1)).gravarRegistro(Mockito.any());
    }

    @Test
    void update() {
        var dados = CourseMock.generateDataDto();
        Mockito.when(this.usecase.atualizarRegistro(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.update(dados);

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getEntity().getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.getEntity().getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.getEntity().getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.getEntity().getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.getEntity().getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.getEntity().getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.getEntity().getEndDate());
        Assertions.assertEquals(dados.getCourseCategoryUuid(), resposta.getEntity().getCourseCategoryUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getEntity().getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getEntity().getUpdatedAt());
        Mockito.verify(this.usecase, Mockito.times(1)).atualizarRegistro(Mockito.any());
    }

    @Test
    void delete() {
        Mockito.doNothing().when(this.usecase).deletarRegistro(Mockito.any());
        var resposta = this.rest.delete(UUID.randomUUID());

        Assertions.assertEquals(204, resposta.getStatus());
        Mockito.verify(this.usecase, Mockito.times(1)).deletarRegistro(Mockito.any());
    }


}