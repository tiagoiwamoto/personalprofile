package br.com.tiagoiwamoto.adapter.out;

import br.com.tiagoiwamoto.core.repository.SkillRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.SkillMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class SkillAdapterTest {

    @Inject
    private SkillAdapter adapter;
    @InjectMock
    private SkillRepository repository;

    @Test
    void all() {
        var dados = SkillMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.list()).thenReturn(List.of(dados));
        Mockito.when(this.repository.findAll()).thenReturn(query);

        var resposta = this.adapter.all();
        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getCategory(), resposta.get(0).getCategory());
        Assertions.assertEquals(dados.getHabilities(), resposta.get(0).getHabilities());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.get(0).getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.get(0).getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    @Test
    void recoveryByUuid() {
        var dados = SkillMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.firstResult()).thenReturn(dados);
        Mockito.when(this.repository.find("uuid", dados.getUuid())).thenReturn(query);

        var resposta = this.adapter.recoveryByUuid(dados.getUuid());
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getCategory(), resposta.getCategory());
        Assertions.assertEquals(dados.getHabilities(), resposta.getHabilities());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).find("uuid", dados.getUuid());
    }

    @Test
    void save() {
        var dados = SkillMock.generateDataEntity();
        Mockito.doNothing().when(this.repository).persist(dados);

        var resposta = this.adapter.save(dados);
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getCategory(), resposta.getCategory());
        Assertions.assertEquals(dados.getHabilities(), resposta.getHabilities());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void saveComErro() {
        var dados = SkillMock.generateDataEntity();
        Mockito.doThrow(RuntimeException.class).when(this.repository).persist(dados);

        Assertions.assertThrows(
                RuntimeException.class, () -> this.adapter.save(dados)
        );
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void update() {
        var dados = SkillMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.firstResult()).thenReturn(dados);
        Mockito.when(this.repository.find("uuid", dados.getUuid())).thenReturn(query);
        Mockito.doNothing().when(this.repository).persist(dados);

        var resposta = this.adapter.update(dados);
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getCategory(), resposta.getCategory());
        Assertions.assertEquals(dados.getHabilities(), resposta.getHabilities());
        Assertions.assertEquals(dados.getCreatedAt(), resposta.getCreatedAt());
        Assertions.assertEquals(dados.getUpdatedAt(), resposta.getUpdatedAt());
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void updateComErro() {
        var dados = SkillMock.generateDataEntity();
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
        var dados = SkillMock.generateDataEntity();
        Mockito.doNothing().when(this.repository).delete(dados);

        this.adapter.delete(dados);

        Mockito.verify(this.repository, Mockito.times(1)).delete(dados);
    }

    @Test
    void deleteComErro() {
        var dados = SkillMock.generateDataEntity();
        Mockito.doThrow(RuntimeException.class).when(this.repository).delete(dados);

        Assertions.assertThrows(
                RuntimeException.class, () -> this.adapter.delete(dados)
        );
        Mockito.verify(this.repository, Mockito.times(1)).delete(dados);
    }
}