package mock;

import br.com.tiagoiwamoto.adapter.dto.ExperienceDTO;
import br.com.tiagoiwamoto.core.entity.ExperienceEntity;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class ExperienceMock {

    public static ExperienceDTO generateDataDto(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new ExperienceDTO();
        data.setUuid(UUID.randomUUID());
        data.setJob(faker.job().title());
        data.setCompany(faker.company().name());
        data.setStartDate(LocalDate.now());
        data.setEndDate(LocalDate.now());
        data.setDescription(faker.lorem().sentence(255));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

    public static ExperienceEntity generateDataEntity(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new ExperienceEntity();
        data.setUuid(UUID.randomUUID());
        data.setJob(faker.job().title());
        data.setCompany(faker.company().name());
        data.setStartDate(LocalDate.now());
        data.setEndDate(LocalDate.now());
        data.setDescription(faker.lorem().sentence(255));
        data.setId(faker.number().numberBetween(1L, 9999));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

}
