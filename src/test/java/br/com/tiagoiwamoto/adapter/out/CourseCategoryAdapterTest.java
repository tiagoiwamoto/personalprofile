package br.com.tiagoiwamoto.adapter.out;

import br.com.tiagoiwamoto.core.repository.CourseCategoryRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.CourseCategoryMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class CourseCategoryAdapterTest {

    @Inject
    private CourseCategoryAdapter adapter;
    @InjectMock
    private CourseCategoryRepository repository;

    @Test
    void all() {
        var dados = CourseCategoryMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.list()).thenReturn(List.of(dados));
        Mockito.when(this.repository.findAll()).thenReturn(query);

        var resposta = this.adapter.all();
        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.get(0).getName());
        Assertions.assertEquals(dados.getDescription(), resposta.get(0).getDescription());
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    @Test
    void recoveryByUuid() {
        var dados = CourseCategoryMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.firstResult()).thenReturn(dados);
        Mockito.when(this.repository.find("uuid", dados.getUuid())).thenReturn(query);

        var resposta = this.adapter.recoveryByUuid(dados.getUuid());
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getDescription(), resposta.getDescription());
        Mockito.verify(this.repository, Mockito.times(1)).find("uuid", dados.getUuid());
    }

    @Test
    void save() {
        var dados = CourseCategoryMock.generateDataEntity();
        Mockito.doNothing().when(this.repository).persist(dados);

        var resposta = this.adapter.save(dados);
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getDescription(), resposta.getDescription());
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void saveComErro() {
        var dados = CourseCategoryMock.generateDataEntity();
        Mockito.doThrow(RuntimeException.class).when(this.repository).persist(dados);

        Assertions.assertThrows(
                RuntimeException.class, () -> this.adapter.save(dados)
        );
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void update() {
        var dados = CourseCategoryMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.firstResult()).thenReturn(dados);
        Mockito.when(this.repository.find("uuid", dados.getUuid())).thenReturn(query);
        Mockito.doNothing().when(this.repository).persist(dados);

        var resposta = this.adapter.update(dados);
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getDescription(), resposta.getDescription());
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void updateComErro() {
        var dados = CourseCategoryMock.generateDataEntity();
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
        var dados = CourseCategoryMock.generateDataEntity();
        Mockito.doNothing().when(this.repository).delete(dados);

        this.adapter.delete(dados);

        Mockito.verify(this.repository, Mockito.times(1)).delete(dados);
    }

    @Test
    void deleteComErro() {
        var dados = CourseCategoryMock.generateDataEntity();
        Mockito.doThrow(RuntimeException.class).when(this.repository).delete(dados);

        Assertions.assertThrows(
                RuntimeException.class, () -> this.adapter.delete(dados)
        );
        Mockito.verify(this.repository, Mockito.times(1)).delete(dados);
    }
}