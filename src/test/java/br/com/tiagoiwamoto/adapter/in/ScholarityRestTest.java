package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.core.usecase.ScholarityUsecase;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import mock.ScholarityMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class ScholarityRestTest {

    @Inject
    private ScholarityRest rest;
    @InjectMock
    private ScholarityUsecase usecase;

    @Test
    @TestSecurity(authorizationEnabled = false)
    void index() {
        var dados = ScholarityMock.generateDataDto();
        Mockito.when(this.usecase.listarRegistros()).thenReturn(List.of(dados));
        var resposta = this.rest.index();

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().get(0).getUuid());
        Assertions.assertEquals(dados.getDuration(), resposta.getEntity().get(0).getDuration());
        Assertions.assertEquals(dados.getStartDate(), resposta.getEntity().get(0).getStartDate());
        Assertions.assertEquals(dados.getDateOfConclusion(), resposta.getEntity().get(0).getDateOfConclusion());
        Assertions.assertEquals(dados.getSchoolName(), resposta.getEntity().get(0).getSchoolName());
        Assertions.assertEquals(dados.getCourseName(), resposta.getEntity().get(0).getCourseName());
        Assertions.assertEquals(dados.getTitleReceivedCourse(), resposta.getEntity().get(0).getTitleReceivedCourse());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getEntity().get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getEntity().get(0).getUpdatedAt());
        Mockito.verify(this.usecase, Mockito.times(1)).listarRegistros();
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void create() {
        var dados = ScholarityMock.generateDataDto();
        Mockito.when(this.usecase.gravarRegistro(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.create(dados);

        Assertions.assertEquals(201, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getDuration(), resposta.getEntity().getDuration());
        Assertions.assertEquals(dados.getStartDate(), resposta.getEntity().getStartDate());
        Assertions.assertEquals(dados.getDateOfConclusion(), resposta.getEntity().getDateOfConclusion());
        Assertions.assertEquals(dados.getSchoolName(), resposta.getEntity().getSchoolName());
        Assertions.assertEquals(dados.getCourseName(), resposta.getEntity().getCourseName());
        Assertions.assertEquals(dados.getTitleReceivedCourse(), resposta.getEntity().getTitleReceivedCourse());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getEntity().getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getEntity().getUpdatedAt());
        Mockito.verify(this.usecase, Mockito.times(1)).gravarRegistro(Mockito.any());
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void update() {
        var dados = ScholarityMock.generateDataDto();
        Mockito.when(this.usecase.atualizarRegistro(Mockito.any())).thenReturn(dados);
        var resposta = this.rest.update(dados);

        Assertions.assertEquals(200, resposta.getStatus());
        Assertions.assertEquals(dados.getUuid(), resposta.getEntity().getUuid());
        Assertions.assertEquals(dados.getDuration(), resposta.getEntity().getDuration());
        Assertions.assertEquals(dados.getStartDate(), resposta.getEntity().getStartDate());
        Assertions.assertEquals(dados.getDateOfConclusion(), resposta.getEntity().getDateOfConclusion());
        Assertions.assertEquals(dados.getSchoolName(), resposta.getEntity().getSchoolName());
        Assertions.assertEquals(dados.getCourseName(), resposta.getEntity().getCourseName());
        Assertions.assertEquals(dados.getTitleReceivedCourse(), resposta.getEntity().getTitleReceivedCourse());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getEntity().getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getEntity().getUpdatedAt());
        Mockito.verify(this.usecase, Mockito.times(1)).atualizarRegistro(Mockito.any());
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void delete() {
        var dados = ScholarityMock.generateDataDto();
        Mockito.doNothing().when(this.usecase).deletarRegistro(Mockito.any());
        var resposta = this.rest.delete(dados.getUuid());

        Assertions.assertEquals(204, resposta.getStatus());
        Mockito.verify(this.usecase, Mockito.times(1)).deletarRegistro(Mockito.any());
    }


}