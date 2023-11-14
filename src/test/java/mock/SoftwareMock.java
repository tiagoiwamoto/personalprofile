package mock;

import br.com.tiagoiwamoto.adapter.dto.SoftwareDTO;
import br.com.tiagoiwamoto.core.entity.SoftwareEntity;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class SoftwareMock {

    public static SoftwareDTO generateDataDto(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new SoftwareDTO();
        data.setUuid(UUID.randomUUID());
        data.setUrl(faker.internet().url());
        data.setUrlMirror(faker.internet().url());
        data.setName(faker.funnyName().name());
        data.setDescription(faker.lorem().sentence(255));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

    public static SoftwareEntity generateDataEntity(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new SoftwareEntity();
        data.setUuid(UUID.randomUUID());
        data.setUrl(faker.internet().url());
        data.setUrlMirror(faker.internet().url());
        data.setName(faker.funnyName().name());
        data.setDescription(faker.lorem().sentence(255));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        data.setId(faker.number().numberBetween(1L, 9999));
        return data;
    }

}
