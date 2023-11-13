package mock;

import br.com.tiagoiwamoto.adapter.dto.ProjectDTO;
import br.com.tiagoiwamoto.core.entity.ProjectEntity;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class ProjectMock {

    public static ProjectDTO generateDataDto(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new ProjectDTO();
        data.setUuid(UUID.randomUUID());
        data.setName(faker.name().fullName());
        data.setUrl(faker.internet().url());
        data.setDescription(faker.lorem().sentence(255));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

    public static ProjectEntity generateDataEntity(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new ProjectEntity();
        data.setUuid(UUID.randomUUID());
        data.setName(faker.name().fullName());
        data.setUrl(faker.internet().url());
        data.setDescription(faker.lorem().sentence(255));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        data.setId(faker.number().numberBetween(1L, 9999));
        return data;
    }

}
