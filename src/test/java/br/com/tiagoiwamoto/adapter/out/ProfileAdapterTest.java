package br.com.tiagoiwamoto.adapter.out;

import br.com.tiagoiwamoto.core.repository.ProfileRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mock.ProfileMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class ProfileAdapterTest {

    @Inject
    private ProfileAdapter adapter;
    @InjectMock
    private ProfileRepository repository;

    @Test
    void all() {
        var dados = ProfileMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.list()).thenReturn(List.of(dados));
        Mockito.when(this.repository.findAll()).thenReturn(query);

        var resposta = this.adapter.all();
        Assertions.assertEquals(dados.getUuid(), resposta.get(0).getUuid());
        Assertions.assertEquals(dados.getName(), resposta.get(0).getName());
        Assertions.assertEquals(dados.getTitle(), resposta.get(0).getTitle());
        Assertions.assertEquals(dados.getSubTitle(), resposta.get(0).getSubTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.get(0).getDescription());
        Assertions.assertEquals(dados.getIsActive(), resposta.get(0).getIsActive());
        Assertions.assertEquals(dados.getPhone(), resposta.get(0).getPhone());
        Assertions.assertEquals(dados.getEmail(), resposta.get(0).getEmail());
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    @Test
    void recoveryByUuid() {
        var dados = ProfileMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.firstResult()).thenReturn(dados);
        Mockito.when(this.repository.find("uuid", dados.getUuid())).thenReturn(query);

        var resposta = this.adapter.recoveryByUuid(dados.getUuid());
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getTitle(), resposta.getTitle());
        Assertions.assertEquals(dados.getSubTitle(), resposta.getSubTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getDescription());
        Assertions.assertEquals(dados.getIsActive(), resposta.getIsActive());
        Assertions.assertEquals(dados.getPhone(), resposta.getPhone());
        Assertions.assertEquals(dados.getEmail(), resposta.getEmail());
        Mockito.verify(this.repository, Mockito.times(1)).find("uuid", dados.getUuid());
    }

    @Test
    void save() {
        var dados = ProfileMock.generateDataEntity();
        Mockito.doNothing().when(this.repository).persist(dados);

        var resposta = this.adapter.save(dados);
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getTitle(), resposta.getTitle());
        Assertions.assertEquals(dados.getSubTitle(), resposta.getSubTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getDescription());
        Assertions.assertEquals(dados.getIsActive(), resposta.getIsActive());
        Assertions.assertEquals(dados.getPhone(), resposta.getPhone());
        Assertions.assertEquals(dados.getEmail(), resposta.getEmail());
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void saveComErro() {
        var dados = ProfileMock.generateDataEntity();
        Mockito.doThrow(RuntimeException.class).when(this.repository).persist(dados);

        Assertions.assertThrows(
                RuntimeException.class, () -> this.adapter.save(dados)
        );
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void update() {
        var dados = ProfileMock.generateDataEntity();
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.firstResult()).thenReturn(dados);
        Mockito.when(this.repository.find("uuid", dados.getUuid())).thenReturn(query);
        Mockito.doNothing().when(this.repository).persist(dados);

        var resposta = this.adapter.update(dados);
        Assertions.assertEquals(dados.getUuid(), resposta.getUuid());
        Assertions.assertEquals(dados.getName(), resposta.getName());
        Assertions.assertEquals(dados.getTitle(), resposta.getTitle());
        Assertions.assertEquals(dados.getSubTitle(), resposta.getSubTitle());
        Assertions.assertEquals(dados.getDescription(), resposta.getDescription());
        Assertions.assertEquals(dados.getIsActive(), resposta.getIsActive());
        Assertions.assertEquals(dados.getPhone(), resposta.getPhone());
        Assertions.assertEquals(dados.getEmail(), resposta.getEmail());
        Mockito.verify(this.repository, Mockito.times(1)).persist(dados);
    }

    @Test
    void updateComErro() {
        var dados = ProfileMock.generateDataEntity();
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
        var dados = ProfileMock.generateDataEntity();
        Mockito.doNothing().when(this.repository).delete(dados);

        this.adapter.delete(dados);

        Mockito.verify(this.repository, Mockito.times(1)).delete(dados);
    }

    @Test
    void deleteComErro() {
        var dados = ProfileMock.generateDataEntity();
        Mockito.doThrow(RuntimeException.class).when(this.repository).delete(dados);

        Assertions.assertThrows(
                RuntimeException.class, () -> this.adapter.delete(dados)
        );
        Mockito.verify(this.repository, Mockito.times(1)).delete(dados);
    }
}