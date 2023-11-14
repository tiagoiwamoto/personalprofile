package mock;

import br.com.tiagoiwamoto.adapter.dto.ResumeDTO;
import br.com.tiagoiwamoto.core.entity.ResumeEntity;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class ResumeMock {

    public static ResumeDTO generateDataDto(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new ResumeDTO();
        data.setUuid(UUID.randomUUID());
        data.setTitle(faker.funnyName().name());
        data.setEmbed(faker.lorem().sentence(255));
        data.setType(faker.file().mimeType());
        data.setLanguage(faker.nation().language());
        data.setDescription(faker.lorem().sentence(255));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

    public static ResumeEntity generateDataEntity(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new ResumeEntity();
        data.setUuid(UUID.randomUUID());
        data.setTitle(faker.funnyName().name());
        data.setEmbed(faker.lorem().sentence(255));
        data.setType(faker.file().mimeType());
        data.setLanguage(faker.nation().language());
        data.setDescription(faker.lorem().sentence(255));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        data.setId(faker.number().numberBetween(1L, 9999));
        return data;
    }

}
