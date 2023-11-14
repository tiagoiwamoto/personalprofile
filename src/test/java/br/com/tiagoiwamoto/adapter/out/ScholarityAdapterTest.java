package br.com.tiagoiwamoto.adapter.out;

import br.com.tiagoiwamoto.core.repository.ScholarityRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.ScholarityMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class ScholarityAdapterTest {

    @Inject
    private ScholarityAdapter adapter;
    @InjectMock
    private ScholarityRepository repository;

    @Test
    void all() {
        var dados = ScholarityMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.list()).thenReturn(List.of(dados));
        Mockito.when(this.repository.findAll()).thenReturn(query);

        var resposta = this.adapter.all();
        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getDuration(), resposta.get(0).getDuration());
        Assertions.assertEquals(dados.getStartDate(), resposta.get(0).getStartDate());
        Assertions.assertEquals(dados.getDateOfConclusion(), resposta.get(0).getDateOfConclusion());
        Assertions.assertEquals(dados.getSchoolName(), resposta.get(0).getSchoolName());
        Assertions.assertEquals(dados.getCourseName(), resposta.get(0).getCourseName());
        Assertions.assertEquals(dados.getTitleReceivedCourse(), resposta.get(0).getTitleReceivedCourse());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.get(0).getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    @Test
    void recoveryByUuid() {
        var dados = ScholarityMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.firstResult()).thenReturn(dados);
        Mockito.when(this.repository.find("uuid", dados.getUuid())).thenReturn(query);

        var resposta = this.adapter.recoveryByUuid(dados.getUuid());
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getDuration(), resposta.getDuration());
        Assertions.assertEquals(dados.getStartDate(), resposta.getStartDate());
        Assertions.assertEquals(dados.getDateOfConclusion(), resposta.getDateOfConclusion());
        Assertions.assertEquals(dados.getSchoolName(), resposta.getSchoolName());
        Assertions.assertEquals(dados.getCourseName(), resposta.getCourseName());
        Assertions.assertEquals(dados.getTitleReceivedCourse(), resposta.getTitleReceivedCourse());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).find("uuid", dados.getUuid());
    }

    @Test
    void save() {
        var dados = ScholarityMock.generateDataEntity();
        Mockito.doNothing().when(this.repository).persist(dados);

        var resposta = this.adapter.save(dados);
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getDuration(), resposta.getDuration());
        Assertions.assertEquals(dados.getStartDate(), resposta.getStartDate());
        Assertions.assertEquals(dados.getDateOfConclusion(), resposta.getDateOfConclusion());
        Assertions.assertEquals(dados.getSchoolName(), resposta.getSchoolName());
        Assertions.assertEquals(dados.getCourseName(), resposta.getCourseName());
        Assertions.assertEquals(dados.getTitleReceivedCourse(), resposta.getTitleReceivedCourse());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void saveComErro() {
        var dados = ScholarityMock.generateDataEntity();
        Mockito.doThrow(RuntimeException.class).when(this.repository).persist(dados);

        Assertions.assertThrows(
                RuntimeException.class, () -> this.adapter.save(dados)
        );
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void update() {
        var dados = ScholarityMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.firstResult()).thenReturn(dados);
        Mockito.when(this.repository.find("uuid", dados.getUuid())).thenReturn(query);
        Mockito.doNothing().when(this.repository).persist(dados);

        var resposta = this.adapter.update(dados);
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getDuration(), resposta.getDuration());
        Assertions.assertEquals(dados.getStartDate(), resposta.getStartDate());
        Assertions.assertEquals(dados.getDateOfConclusion(), resposta.getDateOfConclusion());
        Assertions.assertEquals(dados.getSchoolName(), resposta.getSchoolName());
        Assertions.assertEquals(dados.getCourseName(), resposta.getCourseName());
        Assertions.assertEquals(dados.getTitleReceivedCourse(), resposta.getTitleReceivedCourse());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void updateComErro() {
        var dados = ScholarityMock.generateDataEntity();
        Mockito.doThrow(RuntimeException.class).when(this.repository).persist(dados);
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.firstResult()).thenReturn(dados);
        Mockito.when(this.repository.find("uuid", dados.getUuid())).thenReturn(query);

        Assertions.assertThrows(
                RuntimeException.class, () -> this.adapter.update(dados)
        );
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void delete() {
        var dados = ScholarityMock.generateDataEntity();
        Mockito.doNothing().when(this.repository).delete(dados);

        this.adapter.delete(dados);

        Mockito.verify(this.repository, Mockito.times(1)).delete(dados);
    }

    @Test
    void deleteComErro() {
        var dados = ScholarityMock.generateDataEntity();
        Mockito.doThrow(RuntimeException.class).when(this.repository).delete(dados);

        Assertions.assertThrows(
                RuntimeException.class, () -> this.adapter.delete(dados)
        );
        Mockito.verify(this.repository, Mockito.times(1)).delete(dados);
    }
}