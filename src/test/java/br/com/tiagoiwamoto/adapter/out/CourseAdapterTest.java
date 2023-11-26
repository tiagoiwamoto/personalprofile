package br.com.tiagoiwamoto.adapter.out;

import br.com.tiagoiwamoto.core.repository.CourseRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.CourseMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class CourseAdapterTest {

    @Inject
    private CourseAdapter adapter;
    @InjectMock
    private CourseRepository repository;

    @Test
    void all() {
        var dados = CourseMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.list()).thenReturn(List.of(dados));
        Mockito.when(this.repository.findAll()).thenReturn(query);

        var resposta = this.adapter.all();
        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.get(0).getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.get(0).getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.get(0).getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.get(0).getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.get(0).getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.get(0).getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.get(0).getEndDate());
        Assertions.assertEquals(dados.getCourseCategory().getUuid(), resposta.get(0).getCourseCategory().getUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.get(0).getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    @Test
    void allByCategory(){
        var dados = CourseMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.list()).thenReturn(List.of(dados));
        Mockito.when(this.repository.find("courseCategory", dados.getCourseCategory())).thenReturn(query);

        var resposta = this.adapter.allByCategory(dados.getCourseCategory());
        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.get(0).getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.get(0).getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.get(0).getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.get(0).getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.get(0).getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.get(0).getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.get(0).getEndDate());
        Assertions.assertEquals(dados.getCourseCategory().getUuid(), resposta.get(0).getCourseCategory().getUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.get(0).getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1))
                .find("courseCategory", dados.getCourseCategory());
    }

    @Test
    void recoveryByUuid() {
        var dados = CourseMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.firstResult()).thenReturn(dados);
        Mockito.when(this.repository.find("uuid", dados.getUuid())).thenReturn(query);

        var resposta = this.adapter.recoveryByUuid(dados.getUuid());
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.getEndDate());
        Assertions.assertEquals(dados.getCourseCategory().getUuid(), resposta.getCourseCategory().getUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).find("uuid", dados.getUuid());
    }

    @Test
    void save() {
        var dados = CourseMock.generateDataEntity();
        Mockito.doNothing().when(this.repository).persist(dados);

        var resposta = this.adapter.save(dados);
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.getEndDate());
        Assertions.assertEquals(dados.getCourseCategory().getUuid(), resposta.getCourseCategory().getUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void saveComErro() {
        var dados = CourseMock.generateDataEntity();
        Mockito.doThrow(RuntimeException.class).when(this.repository).persist(dados);

        Assertions.assertThrows(
                RuntimeException.class, () -> this.adapter.save(dados)
        );
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void update() {
        var dados = CourseMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.firstResult()).thenReturn(dados);
        Mockito.when(this.repository.find("uuid", dados.getUuid())).thenReturn(query);
        Mockito.doNothing().when(this.repository).persist(dados);

        var resposta = this.adapter.update(dados);
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.getEndDate());
        Assertions.assertEquals(dados.getCourseCategory().getUuid(), resposta.getCourseCategory().getUuid());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void updateComErro() {
        var dados = CourseMock.generateDataEntity();
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
        var dados = CourseMock.generateDataEntity();
        Mockito.doNothing().when(this.repository).delete(dados);

        this.adapter.delete(dados);

        Mockito.verify(this.repository, Mockito.times(1)).delete(dados);
    }

    @Test
    void deleteComErro() {
        var dados = CourseMock.generateDataEntity();
        Mockito.doThrow(RuntimeException.class).when(this.repository).delete(dados);

        Assertions.assertThrows(
                RuntimeException.class, () -> this.adapter.delete(dados)
        );
        Mockito.verify(this.repository, Mockito.times(1)).delete(dados);
    }

    @Test
    void top10() {
        var dados = CourseMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.list()).thenReturn(List.of(dados));
        Mockito.when(this.repository.find("order by endDate desc limit 10")).thenReturn(query);
        var resposta = this.adapter.top10();

        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.get(0).getName());
        Assertions.assertEquals(dados.getPathOfImage(), resposta.get(0).getPathOfImage());
        Assertions.assertEquals(dados.getPathOfImageThumb(), resposta.get(0).getPathOfImageThumb());
        Assertions.assertEquals(dados.getDuration(), resposta.get(0).getDuration());
        Assertions.assertEquals(dados.getSchool(), resposta.get(0).getSchool());
        Assertions.assertEquals(dados.getStartDate(), resposta.get(0).getStartDate());
        Assertions.assertEquals(dados.getEndDate(), resposta.get(0).getEndDate());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.get(0).getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).find("order by endDate desc limit 10");
    }
}